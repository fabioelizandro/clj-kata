(ns database
  (:require [next.jdbc :as jdbc]))

(defn create-tables [connection]
  (jdbc/execute! connection
                 ["
create table if not exists products (
  id serial primary key,
  name text
)"]))

(defn insert [connection]
  (jdbc/execute! connection ["insert into products(name) values ('PS5')"]))

(defn truncate-tables [connection]
  (jdbc/execute! connection ["truncate table products"]))

(defn query [connection]
  (jdbc/plan connection ["select * from products"]))

(defn -main [args]
  (with-open [connection (jdbc/get-connection (jdbc/get-datasource (System/getenv "DB_URL")))]
    (do
      (create-tables connection)
      (truncate-tables connection)
      (insert connection)
      (run! println
            (into []
                  (map :name)
                  (query connection))))))
