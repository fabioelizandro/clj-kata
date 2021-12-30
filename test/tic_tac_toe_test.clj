(ns tic-tac-toe-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe :refer :all]))

(deftest change-game-state-for-player-X
  (is (= {"X" #{9} "O" #{}} (player-move (new-game) 9 "X")))
  (is (= {"X" #{8} "O" #{}} (player-move (new-game) 8 "X")))
  (is (= {"X" #{7} "O" #{}} (player-move (new-game) 7 "X"))))

(deftest change-game-state-for-player-O
  (is (= {"X" #{} "O" #{9}} (player-move (new-game) 9 "O")))
  (is (= {"X" #{} "O" #{8}} (player-move (new-game) 8 "O")))
  (is (= {"X" #{} "O" #{7}} (player-move (new-game) 7 "O"))))

(deftest does-change-game-state-for-invalid-moves
  (is (= {"X" #{} "O" #{}} (player-move (new-game) 10 "X")))
  (is (= {"X" #{} "O" #{}} (player-move (new-game) 10 "O")))
  (is (= {"X" #{} "O" #{}} (player-move (new-game) 0 "X")))
  (is (= {"X" #{} "O" #{}} (player-move (new-game) 0 "O"))))

(deftest does-change-game-state-when-cell-is-already-taken
  (is (= {"X" #{1} "O" #{}} (player-move {"X" #{1} "O" #{}} 1 "X")))
  (is (= {"X" #{} "O" #{1}} (player-move {"X" #{} "O" #{1}} 1 "X")))
  (is (= {"X" #{1} "O" #{}} (player-move {"X" #{1} "O" #{}} 1 "O")))
  (is (= {"X" #{} "O" #{1}} (player-move {"X" #{} "O" #{1}} 1 "O"))))

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
      (display-game (player-move (new-game) 9 "X"))))
  (is
   (= "+---+---+---+
| X | 2 | O |
+---+---+---+
| 4 | X | O |
+---+---+---+
| 7 | 8 | X |
+---+---+---+"
      (display-game {"X" #{1 5 9} "O" #{3 6}}))))

