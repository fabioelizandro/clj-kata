(ns web-server
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]))

(defroutes app
  (GET "/" [] "<h1>Hello World</h1>")
  (route/not-found "<h1>Page not found</h1>"))

(defn create-server [port]
  (server/run-server app {:port port}))

(defn -main [args]
  (create-server 3000))
