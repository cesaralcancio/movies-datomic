(ns movies-datomic.datomic.dev-config
  (:require [datomic.client.api :as d]))

; https://docs.datomic.com/client-api/datomic.client.api.html#var-client
(def client (d/client {:server-type :dev-local
                       :system      "dev"
                       :storage-dir "/Users/cesar.alcancio/personal/datomic/storage"}))

(d/create-database client {:db-name "movies"})

(defn delete-database []
  (d/delete-database client {:db-name "movies"}))

(defn list-databases []
  (d/list-databases client {}))

(def conn (d/connect client {:db-name "movies"}))

(def movie-schema
  [{:db/ident       :movie/id
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
    :db/doc         "The year the movie was released in theaters"}
   {:db/ident       :movie/created-at
    :db/valueType   :db.type/instant
    :db/cardinality :db.cardinality/one
    :db/doc         "Instant the record is created"}])

(d/transact conn {:tx-data movie-schema})
