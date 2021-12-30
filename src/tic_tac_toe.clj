(ns tic-tac-toe
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn new-game []
  (hash-map "X" #{} "O" #{}))

(defn- player-move [game cell-number player]
  (cond
    (> cell-number 9)                                         game
    (< cell-number 1)                                         game
    (contains? (set/union (game "X") (game "O")) cell-number) game
    :else                                                     (assoc game player (conj (game player) cell-number))))

(defn player-x-move [game cell-number]
  (player-move game cell-number "X"))

(defn player-o-move [game cell-number]
  (player-move game cell-number "O"))

(defn- display-cell [game cell-number]
  (str " "
       (cond
         (contains? (game "X") cell-number) "X"
         (contains? (game "O") cell-number) "O"
         :else                              (str cell-number))
       " "))

(defn display-game [game]
  (str "+---+---+---+\n"
       "|" (str/join "|" (map #(display-cell game %) (range 1 4))) "|\n"
       "+---+---+---+\n"
       "|" (str/join "|" (map #(display-cell game %) (range 4 7))) "|\n"
       "+---+---+---+\n"
       "|" (str/join "|" (map #(display-cell game %) (range 7 10))) "|\n"
       "+---+---+---+"))

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

(defn- next-player [game]
  (cond
    (odd? (count (concat (game "X") (game "O")))) "O"
    :else                                         "X"))

(defn- run-player-read-line-question [game]
  (str "Player " (next-player game) ""))

(defn- run-player-input [game cell-number]
  (cond
    (= (next-player game) "X") (player-x-move game cell-number)
    :else                      (player-o-move game cell-number)))

(defn- run [game]
  (case (game-result game)
    "T"         (println "Tie.")
    "X"         (println "Player X wins.")
    "O"         (println "Player O wins.")
    "P"         (do
                  (println (display-game game))
                  (println (run-player-read-line-question game))
                  (flush)
                  (run (run-player-input game (Integer/parseInt (read-line)))))))

(defn -main [args]
  (run (new-game)))
