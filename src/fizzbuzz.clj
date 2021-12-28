(ns fizzbuzz)

(defn fizzbuzz[n] 
  (cond
    (and (= 0 (mod n 3)) (= 0 (mod n 5))) "FizzBuzz"
    (= 0 (mod n 3)) "Fizz"
    (= 0 (mod n 5)) "Buzz"
    :else (str n)))

(defn -main [args] 
  (run! println (map fizzbuzz (range 1 60))))
