(ns movies-datomic.controllers.movies
  (:require [movies-datomic.datomic.movies :as datomic.movies]
            [movies-datomic.datomic.config :as config]
            [datomic.client.api :as d]
            [movies-datomic.datomic.movies-data :as data]))

(datomic.movies/create-one! config/conn data/shutter-island)

(datomic.movies/create-many! config/conn data/harry-potter-movies-with-ns)

(datomic.movies/find-all! (d/db config/conn))

(datomic.movies/find-by-name! (d/db config/conn) (:title data/harry-potter-philosophers-stone))

(datomic.movies/find-by-id! (d/db config/conn) (:id data/harry-potter-philosophers-stone))

(datomic.movies/pull-by-id! (d/db config/conn) (:id data/harry-potter-philosophers-stone))

(datomic.movies/delete! config/conn (:id data/harry-potter-philosophers-stone))
