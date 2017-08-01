(ns cljsatd.gen-resolver
  (:require [clojure.string :as string]
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
    (let [fields (mapv parsed-atd->clj fields)]
      {:name   top-name
       :fields fields})))

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
                                                                                             :else         (str "Lazy.(force " prefix (upper-case-first next-type) ")"))
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
                                  :else                      (str "(non_null Lazy.(force " prefix (upper-case-first type) "))"))
        let-field-name          (str prefix (upper-case-first object-name) (upper-case-first field-name) "Field")]
    {:let-name let-field-name
     :def      (str let-field-name " = Schema.(makeField \"" field-name "\" \"\" " type-str " 
             (
                fun (ctx: Common.schemaContext) (" object-name ": " module-name "." object-name ") =>
                  " module-name ".(" object-name "." field-name ")
              ))")}))

(defn top-level-def->resolver [prefix all-top-levels top-level-def]
  (let [module-name           "Youtube_j" 
        {:keys [name fields]} top-level-def
        top-name              (str prefix (upper-case-first name))
        reason-fields         (mapv (partial field-def->resolver prefix module-name name all-top-levels) fields)
        fields-defs           (string/join "\n and " (map :def reason-fields))
        fields-str            (string/join ",\n" (map :let-name reason-fields))]
    (str ;;fields-defs
         top-name " =
  lazy
   Schema.(
      obj
        \"" top-name "\"
        fields::(
          fun " top-name "Type => 
        [
" fields-str "
        ]))")))
