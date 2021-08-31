(ns movies-datomic.common.util
  (:import (java.util UUID)))

(defn uuid []
  "Generate an UUID value"
  (UUID/randomUUID))

(defn map->nsmap
  "Add namespace to all the key given a map"
  [m n]
  (reduce-kv
    (fn [acc k v]
      (let [new-kw (if (and (keyword? k)
                            (not (qualified-keyword? k)))
                     (keyword (str n) (name k))
                     k)]
        (assoc acc new-kw v)))
    {} m))
