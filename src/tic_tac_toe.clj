(ns tic-tac-toe
  (:require [clojure.string :as str]))

(defn new-game []
  (vec (repeat 9 nil)))

(defn- player-move [player game cell-number]
  (assoc game (- cell-number 1) player))

(defn player-x-move [game cell-number]
  (player-move "X" game cell-number))

(defn player-o-move [game cell-number]
  (player-move "O" game cell-number))

(defn- display-cell [game cell-number]
  (str  " " (or (get game (- cell-number 1)) (str cell-number)) " "))

(defn display-game [game]
  (str "+---+---+---+\n"
       "|"(str/join "|" (map #(display-cell game %) (range 1 4)))"|\n"
       "+---+---+---+\n"
       "|"(str/join "|" (map #(display-cell game %) (range 4 7)))"|\n"
       "+---+---+---+\n"
       "|"(str/join "|" (map #(display-cell game %) (range 7 10)))"|\n"
       "+---+---+---+"))

;TODO: Keep game state and read user input
(defn -main [args]
  (print (display-game (new-game))))