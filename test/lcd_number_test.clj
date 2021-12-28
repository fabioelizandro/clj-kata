(ns lcd-number-test 
  (:require [clojure.test :refer :all] [lcd-number :refer :all]))

;; This will only work with three digits

(deftest returns-the-number-one
  (is (= (str "   \n"
              "  |\n"
              "  |") (lcd-number 1))))

(deftest returns-the-number-two
  (is (= (str " _ \n"
              " _|\n"
              "|_ ") (lcd-number 2))))


(deftest returns-the-number-three
  (is (= (str " _ \n"
              " _|\n"
              " _|") (lcd-number 3))))

(deftest returns-a-sequence-of-numbers
  (is (= (str "    _  _ \n"
              "  | _| _|\n"
              "  ||_  _|") (lcd-number 123))))