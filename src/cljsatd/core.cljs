(ns cljsatd.core
  (:require [cljsatd.example :as example]
            [clojure.pprint :as pprint]
            [clojure.string :as string]
            [instaparse.core :as insta]
            [camel-snake-kebab.core :as csk]
            [clojure.pprint :as pprint]
            [cljsatd.gen-resolver :as gen-resolver]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn clojurify [str]
  (js->clj (.parse js/JSON str)))

;;(println example/atd-string)

(def ds
  (.parse js/JSON example/json-string))

(def dss
  (js->clj ds))

(defn traverse [path obj]
  (let [path   (or path [])
        kind   (cond
                 (boolean? obj) :boolean
                 (string? obj)  :string
                 (int? obj)     :int
                 (nil? obj)     :nullable
                 (map? obj)     :object
                 (coll? obj)    :collection
                 :else          :unknown) 
        result (case kind
                 :boolean    {path :bool}
                 :string     {path :string}
                 :int        {path :int}
                 :nullable   {path :option}
                 :object     {path (->> (map (fn [[k next-obj]]
                                               (traverse (conj path k) next-obj))
                                             (seq obj))
                                        (reduce (fn helper [acc nxt]
                                                  (merge acc nxt)) {}))}
                 :collection {path (map-indexed
                                     (fn [idx next-obj]
                                        (traverse (conj path idx) next-obj)) obj)})]
    result))

(defn path->name [path kind]
  (case kind
    :object  (csk/->camelCase (string/join "_" (map str (filter string? path))))
    (take-last 1 (filter string? path))))

;; L ← Empty list that will contain the sorted nodes
;; while there are unmarked nodes do
;;     select an unmarked node n
;;     visit(n) 
;; function visit(node n)
;;     if n has a permanent mark then return
;;     if n has a temporary mark then stop (not a DAG)
;;     if n is not marked (i.e. has not been visited yet) then
;;         mark n temporarily
;;         for each node m with an edge from n to m do
;;             visit(m)
;;         mark n permanently
;;         add n to head of L

(defn sort-deps [object-list]
  (let [final               (atom [])
        permanently-marked  (atom #{})
        temporarily-marked  (atom #{})
        permanently-mark!   #(do
                               (swap! permanently-marked conj %)
                               (swap! temporarily-marked disj %))
        temporarily-mark!   #(do
                               (swap! temporarily-marked conj %))
        permanently-marked? (fn [node] (get @permanently-marked node))
        temporarily-marked? (fn [node] (get @temporarily-marked node))
        marked?             (fn [node] (or (permanently-marked? node)
                                          (temporarily-marked? node)))
        visit               (fn visit [node]
                              (let [[type-name fields] node]
                                (println "Visit: " type-name)
                                (cond
                                  (permanently-marked? node) nil
                                  (temporarily-marked? node) nil ;;(assert false (str "Cyclical deps: " type-name " Node: " (pr-str node)))
                                  :else                      (let [field-types        (distinct (mapcat second fields))
                                                                   edge-names         (remove #{:option :collection :bool :int :string} field-types)
                                                                   edges              (filter (fn [[k v]]
                                                                                                (get (set edge-names) k))
                                                                                              object-list)]
                                                               (js/console.log "-> Node & edge-names: " type-name (pr-str edge-names)
                                                                               (pr-str edges))
                                                               (temporarily-mark! node)
                                                               (doseq [edge edges]
                                                                 (visit edge))
                                                               (js/console.log "<- Node: " type-name)
                                                               (permanently-mark! node)
                                                               ;;(js/console.log "Finished: " (pr-str node))
                                                               (swap! final (fn [ov] (vec (concat ov [node]))))))))]
    (doseq [node object-list]
      (visit node))
    @final))

(defn atd-gen [top-level-name obj]
  (let [annotated  (traverse nil obj)
        acc        (atom {})
        top-helper (fn top-helper [sq]
                     (doseq [[path kind] sq]
                       (cond
                         (map? kind)  (do
                                        (top-helper (seq kind))
                                        (swap! acc #(update-in % [(path->name (drop-last 1 path) :object) (last path)]
                                                               (fnil conj #{})
                                                               (path->name path :object))))
                         (coll? kind) (do
                                        (doseq [ks kind]
                                          (top-helper (map (fn [[p* v]]
                                                             (let [p (filter string? p*)]
                                                               [p v]))
                                                           (seq ks))))
                                        (swap! acc #(update-in % [(path->name (drop-last 1 path) :object) (last path)]
                                                               (fnil conj #{})
                                                               :collection)))
                         :else        (let [k* [(path->name (drop-last 1 path) :object) (last path)]
                                            k  (if (empty? (first k*))
                                                 [top-level-name (last path)]
                                                 k*)]
                                        (swap! acc #(update-in % k (fnil conj #{}) kind))))))]
    (top-helper annotated)
    (seq @acc)
    (let [sorted (sort-deps @acc)]
      (string/join "\n"
                   (mapv (fn mid-helper [[type-name fields]]
                           (let [fields-strs (map (fn bottom-helper [[field kind]]
                                                    (let [collection?    (when (:collection kind)
                                                                           "list ")
                                                          nullable?      (when (:option kind)
                                                                           "option ")
                                                          primitive-kind (cond
                                                                           (:int kind)             "int"
                                                                           (:bool kind)            "bool"
                                                                           (:string kind)          "string"
                                                                           (= kind #{:option})     "unknown"
                                                                           (= kind #{:collection}) "unknown"
                                                                           (:unknown kind)         "unknown"
                                                                           :else                   (let [h (first (filter string? kind))]
                                                                                                     (path->name [h] :object)))
                                                          result         (str "  " (when nullable?
                                                                                     "?")
                                                                              field ": " collection? nullable? primitive-kind ";")]
                                                      result))
                                                  fields)
                                 nxt-string  (string/join "\n" fields-strs)]
                             (str "type " type-name " = {\n" nxt-string "\n}\n")))
                         sorted)))))

(defn set-atd-results! [value]
  (set! (.-value (js/document.getElementById "atd-results")) value))

(defn set-resolver-results! [value]
  (set! (.-value (js/document.getElementById "resolver-results")) value))

(defn handle-ui-event [event]
  (let [top-level (.-value (js/document.getElementById "topLevel"))
        source    (.-value (js/document.getElementById "source"))
        _ (println "src: " source)
        text      (atd-gen top-level (clojurify source))] 
    (set-atd-results! text)))

(aset js/window "handleUi"
      (fn ui-handler [event]
        (js/console.log "doing it")
        (handle-ui-event event)))

;;;;******************************************************************************
;;  Instaparse
;;;;******************************************************************************

(def parse-atd
  (insta/parser
    "defs = TOP-TYPE*
     COMMENT = '(*' #'[a-zA-Z0-9 ]*' '*)'
     <WHITESPACE> = <#'\\s|\n'+>
     NL = (<WHITESPACE> | COMMENT)
     <SIMPLE-TOP-TYPE> = FIELD-TYPE <';'?>
     <OBJECT-TOP-TYPE> = <'{'> <NL*> (COMPOUND-FIELD | FIELD)+ <NL*> <'}'>
     TOP-TYPE = <'type '> TOP-NAME <NL*> <'='> <NL*> (OBJECT-TOP-TYPE | SIMPLE-TOP-TYPE) <NL*>
     TOP-NAME = #'\\w+'

     <LIST-TYPE> = 'list'
     <OPTION-TYPE> = 'option'
     FN-TYPE = LIST-TYPE | OPTION-TYPE
     PARAMETERIZED-TYPE = (BASE-TYPE <NL*> FN-TYPE)
     BASE-TYPE = #'[\\w]+'
     FIELD-TYPE= <'('> <NL*> PARAMETERIZED-TYPE <NL*> <')'> <NL*> FN-TYPE | PARAMETERIZED-TYPE | BASE-TYPE

     COMPOUND-FIELD = <NL*> FIELD-NAME <#'\\s*: '> FIELD-TYPE <NL*> <#';?'> <NL*>

     FIELD = FIELD-NAME <NL*> <#':'> <NL*> BASE-TYPE <#';?'> <NL*>
     FIELD-NAME-BASE = #'[\\w]+'
     FIELD-NAME-WITH-DEFAULT = <#'\\~'> FIELD-NAME-BASE
     FIELD-NAME-OPTIONAL = <#'\\?'> FIELD-NAME-BASE
     FIELD-NAME = <NL*> ( FIELD-NAME-BASE | FIELD-NAME-WITH-DEFAULT | FIELD-NAME-OPTIONAL ) <NL*> (OCAML-ATTRS | JSON-ATTRS)*

     OCAML-ATTR-NAME = #'\\w+'
     OCAML-ATTR-VALUE = #'.'+
     OCAML-ATTR = OCAML-ATTR-NAME <'='> OCAML-ATTR-NAME
     OCAML-ATTRS = <'<ocaml '> #'.'+ <'>'>
     JSON-ATTRS = <'<json '> #'.'+ <'>'>"))

(def parsed-atd
  ;;(parse-atd example/test-atd-string)
  (parse-atd example/youtube-atd-string)
  )

;; (pprint/pprint parsed-atd)
;;(println (gen-resolver/gen-resolver parsed-atd))
(set-resolver-results! (string/join "\n" (mapv (partial gen-resolver/top-level-def->resolver "yt" parsed-atd) (gen-resolver/parsed-atd->clj parsed-atd))))
#_(mapv (partial gen-resolver/top-level-def->resolver "yt" parsed-atd) (gen-resolver/parsed-atd->clj parsed-atd))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
