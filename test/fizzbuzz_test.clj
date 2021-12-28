(ns fizzbuzz-test
  (:require [clojure.test :refer :all] [fizzbuzz :refer :all]))

(deftest regular-numbers
  (testing "returns the number when is not a multiple of 3 or 5"
    (is (= "1" (fizzbuzz 1)))
    (is (= "2" (fizzbuzz 2)))
    (is (= "4" (fizzbuzz 4)))
    (is (= "7" (fizzbuzz 7)))
    (is (= "8" (fizzbuzz 8)))))

(deftest numbers-multiple-of-three
  (testing "returns Fizz when the given number is multiple of three"
    (is (= "Fizz" (fizzbuzz 3)))
    (is (= "Fizz" (fizzbuzz 6)))
    (is (= "Fizz" (fizzbuzz 9)))
    (is (= "Fizz" (fizzbuzz 12)))))

(deftest numbers-multiple-of-five
  (testing "returns Buzz when the given number is multiple of five"
    (is (= "Buzz" (fizzbuzz 5)))
    (is (= "Buzz" (fizzbuzz 10)))
    (is (= "Buzz" (fizzbuzz 20)))
    (is (= "Buzz" (fizzbuzz 25)))))

(deftest numbers-multiple-of-five-and-three
  (testing "returns FizzBuzz when the given number is multiple of five and three"
    (is (= "FizzBuzz" (fizzbuzz 15)))
    (is (= "FizzBuzz" (fizzbuzz 30)))
    (is (= "FizzBuzz" (fizzbuzz 45)))
    (is (= "FizzBuzz" (fizzbuzz 60)))))