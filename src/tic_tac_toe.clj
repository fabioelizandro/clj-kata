(ns tic-tac-toe
  (:require [clojure.string :as str]))

(defn player-x-move [game cell-number]
  (assoc game cell-number "X"))

(defn player-o-move [game cell-number]
  (assoc game cell-number "O"))

(defn- display-cell [game cell-number]
  (str " " (or (get game cell-number) (str cell-number)) " "))

(defn display-game [game]
  (str "+---+---+---+\n"
       "|" (str/join "|" (map #(display-cell game %) (range 1 4))) "|\n"
       "+---+---+---+\n"
       "|" (str/join "|" (map #(display-cell game %) (range 4 7))) "|\n"
       "+---+---+---+\n"
       "|" (str/join "|" (map #(display-cell game %) (range 7 10))) "|\n"
       "+---+---+---+"))

(defn player-moves [game]
  (reduce
   (fn [acc [cell player]]
     (assoc acc player (conj (acc player) cell)))
   {"X" #{} "O" #{}} game))

(def ^:private winner-sets
  #{; Horizontal sets
    #{1 2 3}
    #{4 5 6}
    #{7 8 9}
    ; Vertical sets
    #{1 4 7}
    #{2 5 8}
    #{3 6 9}
    ; Diagonal sets
    #{1 5 9}
    #{3 5 7}})

(defn game-result [players-set]
  (cond
    (contains? winner-sets (players-set "X"))                  (str "X")
    (contains? winner-sets (players-set "O"))                  (str "O")
    (= 9 (count (concat (players-set "O") (players-set "X")))) (str "T")
    :else                                                      "P"))

(defn- run-player-read-line-question [move]
  (cond
    (odd? move) "Player X:"
    :else       "Player O:"))

(defn- run-player-input [game cell-number move]
  (cond
    (odd? move) (player-x-move game cell-number)
    :else       (player-o-move game cell-number)))

(defn- run [game move]
  (case (game-result (player-moves game))
    "T"         (println "Tie.")
    "X"         (println "Player X wins.")
    "O"         (println "Player O wins.")
    "P"         (do
                  (println (display-game game))
                  (println (run-player-read-line-question move))
                  (flush)
                  (run (run-player-input game (Integer/parseInt (read-line)) move) (+ move 1)))))

(defn -main [args]
  (run {} 1))
