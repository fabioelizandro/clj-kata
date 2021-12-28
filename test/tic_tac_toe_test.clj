(ns tic-tac-toe-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe :refer :all]))

;TOOD: How to encapsulate the game state?

(deftest returns-an-new-game
  (is (= [nil nil nil nil nil nil nil nil nil] (new-game))))

(deftest change-game-state-for-player-X
  (is (= [nil nil nil nil nil nil nil nil "X"] (player-x-move (new-game) 9)))
  (is (= [nil nil nil nil nil nil nil "X" nil] (player-x-move (new-game) 8)))
  (is (= [nil nil nil nil nil nil "X" nil nil] (player-x-move (new-game) 7)))
  (is (= [nil nil nil nil nil "X" nil nil nil] (player-x-move (new-game) 6)))
  (is (= ["X" nil nil nil nil nil nil nil nil] (player-x-move (new-game) 1))))

(deftest change-game-state-for-player-O
  (is (= [nil nil nil nil nil nil nil nil "O"] (player-o-move (new-game) 9)))
  (is (= [nil nil nil nil nil nil nil "O" nil] (player-o-move (new-game) 8)))
  (is (= [nil nil nil nil nil nil "O" nil nil] (player-o-move (new-game) 7)))
  (is (= [nil nil nil nil nil "O" nil nil nil] (player-o-move (new-game) 6)))
  (is (= ["O" nil nil nil nil nil nil nil nil] (player-o-move (new-game) 1))))

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
      (display-game ["X" nil "O" nil "X" "O" nil nil "X"]))))