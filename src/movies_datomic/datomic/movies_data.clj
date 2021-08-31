(ns movies-datomic.datomic.movies-data
  (:require [movies-datomic.common.util :as util]))

(def shutter-island
  {:id           (util/uuid)
   :title        "Shutter Island"
   :genre        "Thriller/Mystery"
   :release-year 2010})

(def harry-potter-philosophers-stone
  {:id           (util/uuid)
   :title        "Harry Potter and the Philosopher's Stone"
   :genre        "Fantasy/Adventure"
   :release-year 2001})

(def harry-potter-chamber-of-secrets
  {:id           (util/uuid)
   :title        "Harry Potter and the Chamber of Secrets"
   :genre        "Fantasy/Adventure"
   :release-year 2002})

(def harry-potter-prisoner-of-azkaban
  {:id           (util/uuid)
   :title        "Harry Potter and the Prisoner of Azkaban"
   :genre        "Fantasy/Adventure"
   :release-year 2004})

(def harry-potter-goblet-of-fire
  {:id           (util/uuid)
   :title        "Harry Potter and the Goblet of Fire"
   :genre        "Fantasy/Adventure"
   :release-year 2005})

(def harry-potter-order-of-the-phoenix
  {:id           (util/uuid)
   :title        "Harry Potter and the Order of the Phoenix"
   :genre        "Fantasy/Adventure"
   :release-year 2007})

(def harry-potter-half-blood-prince
  {:id           (util/uuid)
   :title        "Harry Potter and the Half-Blood Prince"
   :genre        "Fantasy/Adventure"
   :release-year 2009})

(def harry-potter-deathly-hallows-p1
  {:id           (util/uuid)
   :title        "Harry Potter and the Deathly Hallows – Part 1"
   :genre        "Fantasy/Adventure"
   :release-year 2010})

(def harry-potter-deathly-hallows-p2
  {:id           (util/uuid)
   :title        "Harry Potter and the Deathly Hallows – Part 2"
   :genre        "Fantasy/Adventure"
   :release-year 2010})

(def harry-potter-movies
  [harry-potter-philosophers-stone harry-potter-chamber-of-secrets
   harry-potter-prisoner-of-azkaban harry-potter-goblet-of-fire
   harry-potter-order-of-the-phoenix harry-potter-half-blood-prince
   harry-potter-deathly-hallows-p1 harry-potter-deathly-hallows-p2])

(def harry-potter-movies-with-ns (map #(util/map->nsmap % 'movie) harry-potter-movies))
