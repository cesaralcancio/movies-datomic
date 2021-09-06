(ns movies-datomic.controllers.movies
  (:require [movies-datomic.datomic.movies :as datomic.movies]
            [movies-datomic.datomic.dev-config :as config]
            [datomic.client.api :as d]
            [movies-datomic.datomic.movies-data :as data]))

; Shutter Island

(datomic.movies/upsert-one! config/conn data/shutter-island)

(datomic.movies/pull-by-id! (d/db config/conn) (:id data/shutter-island))

(datomic.movies/upsert-one! config/conn (assoc data/shutter-island :title "Shutter Island Edited"))

(datomic.movies/pull-by-id! (d/db config/conn) (:id data/shutter-island))

(datomic.movies/retract-one! config/conn (:id data/shutter-island))

(datomic.movies/upsert-one-map! config/conn data/shutter-island)

(datomic.movies/pull-by-id! (d/db config/conn) (:id data/shutter-island))

(datomic.movies/retract-one! config/conn (:id data/shutter-island))

; Harry Potter

(datomic.movies/upsert-many! config/conn data/harry-potter-movies-with-ns)

(datomic.movies/find-all! (d/db config/conn))

(datomic.movies/find-by-name! (d/db config/conn) (:title data/harry-potter-philosophers-stone))

(datomic.movies/find-by-id! (d/db config/conn) (:id data/harry-potter-philosophers-stone))

(datomic.movies/pull-by-id! (d/db config/conn) (:id data/harry-potter-philosophers-stone))

(datomic.movies/retract-one! config/conn (:id data/harry-potter-philosophers-stone))
