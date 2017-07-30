(ns cljsatd.core
  (:require [cljsatd.example :as example]
            [clojure.string :as string]
            [camel-snake-kebab.core :as csk]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn clojurify [str]
  (js->clj (.parse js/JSON str)))

(println example/json-string)

(def ds
  (.parse js/JSON example/json-string))

(js/console.log (aget ds 0))

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

(defn handle-ui-event [event]
  (let [top-level (.-value (js/document.getElementById "topLevel"))
        source    (.-value (js/document.getElementById "source"))
        _ (println "src: " source)
        results   (js/document.getElementById "results")
        text      (atd-gen top-level (clojurify source))] 
    (set! (.-innerText results) text)))

(aset js/window "handleUi"
      (fn ui-handler [event]
        (js/console.log "doing it")
        (handle-ui-event event)))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
