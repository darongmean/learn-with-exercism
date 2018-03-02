(ns say
  (:require [clojure.string :as string]))


(def count-in-word
  {0  "zero"
   1  "one"
   2  "two"
   3  "three"
   4  "four"
   5  "five"
   6  "six"
   7  "seven"
   8  "eight"
   9  "nine"
   10 "ten"
   11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"
   20 "twenty"
   30 "thirty"
   40 "forty"
   50 "fifty"
   60 "sixty"
   70 "seventy"
   80 "eighty"
   90 "ninety"})


(defn- count-in-word-loop-recur [number]
  (loop [n number
         result []]
    (cond
      (<= n 20)
      (conj result (count-in-word n))

      (zero? (mod n 10))
      (conj result (count-in-word n))

      :else
      (recur
        (mod n 10)
        (conj result (count-in-word (- n (mod n 10))))))))


(defn- number- [n]
  (string/join "-" (count-in-word-loop-recur n)))


(defn number [n]
  (if (and (< -1 n) (< n 1e12))
    (number- n)
    (throw (IllegalArgumentException.))))
