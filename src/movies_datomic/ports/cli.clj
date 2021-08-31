(ns movies-datomic.ports.cli)

(def example-of-json
  {:id           "uuid"
   :title        "Movie Title"
   :genre        "Genre"
   :release-year 1991})

(def post-command
  (str "post " example-of-json "\n"))

(def list-command
  "list-all \n")

(def list-by-id-command
  "list-by-id uuid \n")

(def delete-by-id-command
  "delete-by-id uuid \n")

(def initial-message
  (str "Run one of the following commands:\n"
       post-command list-command list-by-id-command delete-by-id-command))


(defn run-post
  []
  (println "run-post"))

(defn run-app!
  []
  (println initial-message)
  (let [command (read-line)]
    (cond
      (clojure.string/includes? command "post") run-post)))
