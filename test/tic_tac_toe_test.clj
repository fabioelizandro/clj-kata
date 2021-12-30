(ns tic-tac-toe-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe :refer :all]))

(deftest change-game-state-for-player-X
  (is (= {9 "X"} (player-x-move {} 9)))
  (is (= {8 "X"} (player-x-move {} 8)))
  (is (= {7 "X"} (player-x-move {} 7))))

(deftest change-game-state-for-player-O
  (is (= {9 "O"} (player-o-move {} 9)))
  (is (= {8 "O"} (player-o-move {} 8)))
  (is (= {7 "O"} (player-o-move {} 7))))

(deftest converts-game-state-to-player-set
  (is (= {"X" #{1 2 3} "O" #{}} (player-moves {1 "X" 2 "X" 3 "X"})))
  (is (= {"X" #{3 5 9} "O" #{}} (player-moves {3 "X" 5 "X" 9 "X"})))
  (is (= {"X" #{} "O" #{1 2 3}} (player-moves {1 "O" 2 "O" 3 "O"})))
  (is (= {"X" #{2 4} "O" #{1 3}} (player-moves {1 "O" 2 "X" 3 "O" 4 "X"}))))

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

(deftest transforms-board-into-string
  (is
   (= "+---+---+---+
| 1 | 2 | 3 |
+---+---+---+
| 4 | 5 | 6 |
+---+---+---+
| 7 | 8 | 9 |
+---+---+---+"
      (display-game {})))
  (is
   (= "+---+---+---+
| 1 | 2 | 3 |
+---+---+---+
| 4 | 5 | 6 |
+---+---+---+
| 7 | 8 | X |
+---+---+---+"
      (display-game (player-x-move {} 9))))
  (is
   (= "+---+---+---+
| X | 2 | O |
+---+---+---+
| 4 | X | O |
+---+---+---+
| 7 | 8 | X |
+---+---+---+"
      (display-game
       {1 "X"
        5 "X"
        9 "X"
        3 "O"
        6 "O"}))))
