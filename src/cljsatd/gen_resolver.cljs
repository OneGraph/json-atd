(ns cljsatd.gen-resolver
  (:require [camel-snake-kebab.core :as csk]
            [clojure.string :as string]
            [cljsatd.utils :as utils :refer [ffilter]]))

(defn upper-case-first [s]
  (str (-> s
           (.charAt 0)
           (.toUpperCase))
       (string/join (drop 1 s))))

(defmulti parsed-atd->clj
  (fn [definition]
    (first definition)))

(defmethod parsed-atd->clj :FIELD-NAME-BASE [definition]
  (let [[_ field-name] definition]
    {:name field-name}))

(defmethod parsed-atd->clj :FIELD-NAME-OPTIONAL [definition]
  (let [[_ nxt] definition]
    (parsed-atd->clj nxt)))

(defmethod parsed-atd->clj :FIELD-NAME [definition]
  (let [[_ nxt] definition]
    (parsed-atd->clj nxt)))

(defmethod parsed-atd->clj :FIELD-NAME-WITH-DEFAULT [definition]
  (let [[_ nxt] definition]
    (parsed-atd->clj nxt)))

(defmethod parsed-atd->clj :BASE-TYPE [definition]
  (let [[_ nxt] definition]
    nxt))

(defmethod parsed-atd->clj :FN-TYPE [definition]
  (let [[_ nxt] definition]
    nxt))

(defmethod parsed-atd->clj :PARAMETERIZED-TYPE [definition]
  (let [[_ & nxts] definition]
    (reduce (fn [acc nxt]
              (conj acc (parsed-atd->clj nxt))) [] nxts)))

(defmethod parsed-atd->clj :FIELD-TYPE [definition]
  (let [[_ & nxt] definition]
    {:type (vec (flatten (mapv parsed-atd->clj nxt)))}))

(defmethod parsed-atd->clj :COMPOUND-FIELD [definition]
  (let [[_ & nxt] definition]
    (into {} (mapv parsed-atd->clj nxt))))

(defmethod parsed-atd->clj :TOP-TYPE [definition]
  (let [[_ [_ top-name] & fields] definition]
    (let [fields                 (mapv parsed-atd->clj fields)
          ;;top-level-simple-type? is like `type timelineResult = list tweet`, (no sub fields)
          top-level-simple-type? (and (= 1 (count fields))
                                      (every? (comp nil? :name) fields))]
      {:name   top-name
       :fields (if top-level-simple-type?
                 (update-in fields [0] merge {:name    top-name
                                              :simple? top-level-simple-type?})
                 fields)})))

(defmethod parsed-atd->clj :defs [definition]
  (let [top-types (drop 1 definition)]
    (mapv parsed-atd->clj top-types)))

(defn gen-resolver [parsed-atd]
   (parsed-atd->clj parsed-atd))

(def primitive-types
  #{"option" "bool" "int" "float" "list" "string"})

(defn field-def->resolver [prefix module-name object-name all-top-levels field-def]
  (let [{:keys [name type]} field-def
        resolver-name       (str prefix (upper-case-first name))
        field-name          name
        nullable?           (get (set type) "option")
        type-str            (cond (coll? type)
                                  (let [type         (reverse type)
                                        [_ type-str] (reduce (fn [acc next-type]
                                                               (let [[prev-state next-acc] acc
                                                                     non-null-str          (if (not (:nullable? prev-state))
                                                                                             "non_null "
                                                                                             "")
                                                                     is-option?            (= next-type "option")
                                                                     is-list?              (= next-type "list")
                                                                     is-primitive?         (get primitive-types next-type)
                                                                     is-constructor?       is-list?
                                                                     next-state            {:nullable?    is-option?
                                                                                            :constructor? is-constructor?}
                                                                     next-type-str         (cond
                                                                                             is-option?    ""
                                                                                             is-list?      (str "(" next-type)
                                                                                             is-primitive? next-type
                                                                                             :else         (str "(" prefix (upper-case-first next-type) ")"))
                                                                     full-next-acc         (str next-acc "("
                                                                                                non-null-str
                                                                                                next-type-str
                                                                                                (when-not is-constructor?
                                                                                                  ")")
                                                                                                (when (:constructor? prev-state)
                                                                                                  "))"))]
                                                                 (if is-option?
                                                                   [next-state next-acc]
                                                                   [next-state full-next-acc])))
                                                             [{:nullable?    false
                                                               :constructor? false} ""] type)]
                                    type-str)
                                  (get primitive-types type) (str "(non_null " type ")")
                                  :else                      (str "(non_null " prefix (upper-case-first type) ")"))
        let-field-name          (csk/->camelCase (str prefix "_" object-name "_" field-name "_field"))]
    ;;(println "field def: " field-def)
    {:let-name let-field-name
     :def      (str "let " let-field-name " = Schema.(makeField \"" (csk/->camelCase field-name) "\" \"\" " type-str " 
             (
                fun (ctx: Common.schemaContext) (" object-name ": " module-name "." object-name ") =>
                  " module-name ".(" object-name (when-not (:simple? field-def)
                                                   (str "." field-name)) ")
              ));")}))

(defn top-level-def->resolver [prefix json-module-name all-top-levels top-level-def]
  (let [module-name           json-module-name
        {:keys [name fields]} top-level-def
        top-name              (str prefix (upper-case-first name))
        reason-fields         (mapv (partial field-def->resolver prefix module-name name all-top-levels) fields)
        fields-defs           (string/join "\n" (map :def reason-fields))
        fields-str            (string/join ",\n" (map :let-name reason-fields))]
    (str fields-defs
      "\nlet " top-name " =
   Schema.(
      obj
        \"" top-name "\"
        fields::(
          fun " top-name "Type => 
        [
" fields-str "
        ]));")))
