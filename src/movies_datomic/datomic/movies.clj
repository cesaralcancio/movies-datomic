(ns movies-datomic.datomic.movies
  (:require [datomic.client.api :as d]))

(defn upsert-one!
  "Update or insert one record"
  [conn {:keys [id title genre release-year created-at]}]
  (-> (d/transact conn {:tx-data [[:db/add "temporary-new-db-id" :movie/id id]
                                  [:db/add "temporary-new-db-id" :movie/title title]
                                  [:db/add "temporary-new-db-id" :movie/genre genre]
                                  [:db/add "temporary-new-db-id" :movie/release-year release-year]
                                  [:db/add "temporary-new-db-id" :movie/created-at created-at]]})
      :tx-data))

(defn upsert-one-map!
  "Upsert or insert one record using map"
  [conn {:keys [id title genre release-year created-at]}]
  (-> (d/transact conn {:tx-data [{:movie/id           id
                                   :movie/title        title
                                   :movie/genre        genre
                                   :movie/release-year release-year
                                   :movie/created-at   created-at}]})
      :tx-data))

(defn upsert-many!
  "Update or insert "
  [conn movies]
  (-> (d/transact conn {:tx-data movies}) :tx-data))

(defn retract-one!
  "Retract all the fields based on the :movie/id"
  [conn id]
  (try
    (-> (d/transact conn {:tx-data
                          [[:db/retract [:movie/id id] :movie/id]
                           [:db/retract [:movie/id id] :movie/title]
                           [:db/retract [:movie/id id] :movie/genre]
                           [:db/retract [:movie/id id] :movie/release-year]
                           [:db/retract [:movie/id id] :movie/created-at]]})
        :tx-data)
    (catch Exception e nil)))

(defn find-all!
  [db]
  (d/q '[:find ?id ?title ?genre ?release-year ?created-at
         :keys id title genre release-year created-at
         :where
         [?e :movie/id ?id]
         [?e :movie/title ?title]
         [?e :movie/genre ?genre]
         [?e :movie/release-year ?release-year]
         [?e :movie/created-at ?created-at]]
       db))

(defn find-by-name!
  [db name]
  (-> (d/q '[:find ?id ?title ?genre ?release-year ?created-at
             :keys id title genre release-year created-at
             :in $ ?title
             :where
             [?e :movie/id ?id]
             [?e :movie/title ?title]
             [?e :movie/genre ?genre]
             [?e :movie/release-year ?release-year]
             [?e :movie/created-at ?created-at]]
           db name)
      first))

(defn find-by-id!
  "Will return only entities that have all the fields specified in the where
  That is, if they don't have :movie/title they will be ignored"
  [db id]
  (-> (d/q '[:find ?id ?title ?genre ?release-year ?created-at
             :keys id title genre release-year created-at
             :in $ ?id
             :where
             [?e :movie/id ?id]
             [?e :movie/title ?title]
             [?e :movie/genre ?genre]
             [?e :movie/release-year ?release-year]
             [?e :movie/created-at ?created-at]]
           db id)
      first))

(defn pull-by-id!
  "Will return all the entity that has :movie/id even if they don't have :movie/title"
  [db id]
  (-> (d/q '[:find (pull ?e [*])
             :in $ ?id
             :where
             [?e :movie/id ?id]]
           db id)
      first first))
