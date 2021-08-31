(ns movies-datomic.datomic.movies
  (:require [datomic.client.api :as d]))

(defn create-one!
  "Create a new movie"
  [conn {:keys [id title genre release-year]}]
  (d/transact conn {:tx-data [{:movie/id           id
                               :movie/title        title
                               :movie/genre        genre
                               :movie/release-year release-year}]}))

(defn create-many!
  "Create a list of movie"
  [conn movies]
  (d/transact conn {:tx-data movies}))

(defn delete!
  "Retract all the fields based on the :movie/id"
  [conn id]
  (d/transact conn {:tx-data
                    [[:db/retract [:movie/id id] :movie/id]
                     [:db/retract [:movie/id id] :movie/title]
                     [:db/retract [:movie/id id] :movie/genre]
                     [:db/retract [:movie/id id] :movie/release-year]]}))

(defn find-all!
  [db]
  (d/q '[:find ?id ?title ?genre ?release-year
         :keys id title genre release-year
         :where
         [?e :movie/id ?id]
         [?e :movie/title ?title]
         [?e :movie/genre ?genre]
         [?e :movie/release-year ?release-year]]
       db))

(defn find-by-name!
  [db name]
  (d/q '[:find ?id ?title ?genre ?release-year
         :keys id title genre release-year
         :in $ ?title
         :where
         [?e :movie/id ?id]
         [?e :movie/title ?title]
         [?e :movie/genre ?genre]
         [?e :movie/release-year ?release-year]]
       db name))

(defn find-by-id!
  "Will return only entities that have all the fields specified in the where
  That is, if they don't have :movie/title they will be ignored"
  [db id]
  (d/q '[:find ?id ?title ?genre ?release-year
         :keys id title genre release-year
         :in $ ?id
         :where
         [?e :movie/id ?id]
         [?e :movie/title ?title]
         [?e :movie/genre ?genre]
         [?e :movie/release-year ?release-year]]
       db id))

(defn pull-by-id!
  "Will return all the entity that has :movie/id even if they don't have :movie/title"
  [db id]
  (d/q '[:find (pull ?e [*])
         :in $ ?id
         :where
         [?e :movie/id ?id]]
       db id))
