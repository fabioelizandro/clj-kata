(ns lcd-number
  (:require [clojure.string :as str]))

(def ^:private lcd-digits
  {"1" (vector "   "
               "  |"
               "  |")
   "2" (vector " _ "
               " _|"
               "|_ ")
   "3" (vector " _ "
               " _|"
               " _|")})

(defn- number-to-lcd-digits [n]
  (map lcd-digits (str/split (str n) #"")))

(defn- concat-lcd-digits [digits digit]
  "
    It appends one lcd digit to the end of o list of digits
    Give:
      digits: [
        '    _ '
        '  | _|'
        '  ||_ '
      ]
      digit: [
        ' _ '
        ' _|'
        ' _|'
      ]

    Returns:
    [
        '    _  _ '
        '  | _| _|'
        '  ||_  _|'
    ]
  "
  (vector
   (str (get digits 0) (get digit 0))
   (str (get digits 1) (get digit 1))
   (str (get digits 2) (get digit 2))))

(defn lcd-number [n]
  (str/join "\n"
            (reduce concat-lcd-digits ["" "" ""] (number-to-lcd-digits n))))

(defn -main [args]
  (run! println (map lcd-number [1 2 3 123 321])))