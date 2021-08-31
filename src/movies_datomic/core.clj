(ns movies-datomic.core
  (:require [movies-datomic.ports.cli :as cli]))

(defn -main [] (cli/run-app!))
