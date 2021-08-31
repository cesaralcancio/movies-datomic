(ns movies-datomic.datomic.config
  (:require [datomic.client.api :as d]))

(def client (d/client {:server-type :dev-local
                       :system      "dev"}))

(d/create-database client {:db-name "movies"})

(defn delete-database []
  (d/delete-database client {:db-name "movies"}))

(defn list-databases []
  (d/list-databases client {}))

(def conn (d/connect client {:db-name "movies"}))

(def movie-schema [{:db/ident       :movie/id
                    :db/unique      :db.unique/identity
                    :db/valueType   :db.type/uuid
                    :db/cardinality :db.cardinality/one
                    :db/doc         "The id of the movie"}
                   {:db/ident       :movie/title
                    :db/valueType   :db.type/string
                    :db/cardinality :db.cardinality/one
                    :db/doc         "The title of the movie"}
                   {:db/ident       :movie/genre
                    :db/valueType   :db.type/string
                    :db/cardinality :db.cardinality/one
                    :db/doc         "The genre of the movie"}
                   {:db/ident       :movie/release-year
                    :db/valueType   :db.type/long
                    :db/cardinality :db.cardinality/one
                    :db/doc         "The year the movie was released in theaters"}])

(d/transact conn {:tx-data movie-schema})
