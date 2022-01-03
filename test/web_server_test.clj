(ns web-server-test
  (:require [clojure.test :refer :all]
            [web-server :refer :all]
            [ring.mock.request :as mock]))

(deftest app-routes
  (testing "GET / - returns hellow world"
           (let [response (app (mock/request :get "/"))]
             (is (= "<h1>Hello World</h1>" (response :body)))
             (is (= 200 (response :status)))))
  (testing "returns 404 when page is not found"
           (let [response (app (mock/request :get "/not-found"))]
             (is (= "<h1>Page not found</h1>" (response :body)))
             (is (= 404 (response :status))))))
