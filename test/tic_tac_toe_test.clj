(ns tic-tac-toe-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe :refer :all]))

(deftest change-game-state-for-player-X
  (is (= {"X" #{9} "O" #{}} (player-x-move (new-game) 9)))
  (is (= {"X" #{8} "O" #{}} (player-x-move (new-game) 8)))
  (is (= {"X" #{7} "O" #{}} (player-x-move (new-game) 7))))

(deftest change-game-state-for-player-O
  (is (= {"X" #{} "O" #{9}} (player-o-move (new-game) 9)))
  (is (= {"X" #{} "O" #{8}} (player-o-move (new-game) 8)))
  (is (= {"X" #{} "O" #{7}} (player-o-move (new-game) 7))))

(deftest does-change-game-state-for-invalid-moves
  (is (= {"X" #{} "O" #{}} (player-x-move (new-game) 10)))
  (is (= {"X" #{} "O" #{}} (player-o-move (new-game) 10)))
  (is (= {"X" #{} "O" #{}} (player-x-move (new-game) 0)))
  (is (= {"X" #{} "O" #{}} (player-o-move (new-game) 0))))

(deftest does-change-game-state-when-cell-is-already-taken
  (is (= {"X" #{1} "O" #{}} (player-x-move {"X" #{1} "O" #{}} 1)))
  (is (= {"X" #{} "O" #{1}} (player-x-move {"X" #{} "O" #{1}} 1)))
  (is (= {"X" #{1} "O" #{}} (player-o-move {"X" #{1} "O" #{}} 1)))
  (is (= {"X" #{} "O" #{1}} (player-o-move {"X" #{} "O" #{1}} 1))))

(deftest returns-game-winner-for-horizontal-matches
  (is (= "X" (game-result {"X" #{1 2 3} "O" #{}})))
  (is (= "O" (game-result {"X" #{} "O" #{1 2 3}})))
  (is (= "X" (game-result {"X" #{4 5 6} "O" #{}})))
  (is (= "O" (game-result {"X" #{} "O" #{4 5 6}})))
  (is (= "X" (game-result {"X" #{7 8 9} "O" #{}})))
  (is (= "O" (game-result {"X" #{} "O" #{7 8 9}}))))

(deftest returns-game-winner-for-vertical-matches
  (is (= "X" (game-result {"X" #{1 4 7} "O" #{}})))
  (is (= "O" (game-result {"X" #{} "O" #{1 4 7}})))
  (is (= "X" (game-result {"X" #{2 5 8} "O" #{}})))
  (is (= "O" (game-result {"X" #{} "O" #{2 5 8}})))
  (is (= "X" (game-result {"X" #{3 6 9} "O" #{}})))
  (is (= "O" (game-result {"X" #{} "O" #{3 6 9}}))))

(deftest returns-game-winner-for-diagonal-matches
  (is (= "X" (game-result {"X" #{1 5 9} "O" #{}})))
  (is (= "O" (game-result {"X" #{} "O" #{1 5 9}})))
  (is (= "X" (game-result {"X" #{3 5 7} "O" #{}})))
  (is (= "O" (game-result {"X" #{} "O" #{3 5 7}}))))

(deftest returns-game-in-progress
  (is (= "P" (game-result {"X" #{} "O" #{}})))
  (is (= "P" (game-result {"X" #{1 2} "O" #{3 4}}))))

(deftest returns-game-tie
  (is (= "T" (game-result {"X" #{1 2 6 7 8} "O" #{3 4 5 9}})))
  (is (= "T" (game-result {"X" #{3 4 5 9} "O" #{1 2 6 7 8}}))))

(deftest transforms-board-into-string
  (is
   (= "+---+---+---+
| 1 | 2 | 3 |
+---+---+---+
| 4 | 5 | 6 |
+---+---+---+
| 7 | 8 | 9 |
+---+---+---+"
      (display-game (new-game))))
  (is
   (= "+---+---+---+
| 1 | 2 | 3 |
+---+---+---+
| 4 | 5 | 6 |
+---+---+---+
| 7 | 8 | X |
+---+---+---+"
      (display-game (player-x-move (new-game) 9))))
  (is
   (= "+---+---+---+
| X | 2 | O |
+---+---+---+
| 4 | X | O |
+---+---+---+
| 7 | 8 | X |
+---+---+---+"
      (display-game {"X" #{1 5 9} "O" #{3 6}}))))

