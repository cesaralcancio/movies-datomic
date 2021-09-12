(ns movies-datomic.controllers.movies-harry-potter
  (:require [movies-datomic.datomic.movies :as datomic.movies]
            [movies-datomic.datomic.dev-config :as config]
            [datomic.client.api :as d])
  (:import (java.util UUID Date)))

(def instant (new Date))

(def harry-potter-philosophers-stone
  {:id           (UUID/randomUUID)
   :title        "Harry Potter and the Philosopher's Stone"
   :genre        "Fantasy/Adventure"
   :release-year 2001
   :created-at   instant})

(datomic.movies/upsert-one! config/conn harry-potter-philosophers-stone)

(d/q '[:find ?id ?title ?genre ?release-year ?created-at
       :where
       [?e :movie/id ?id]
       [?e :movie/title ?title]
       [?e :movie/genre ?genre]
       [?e :movie/release-year ?release-year]
       [?e :movie/created-at ?created-at]]
     (d/db config/conn))
