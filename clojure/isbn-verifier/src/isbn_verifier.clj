(ns isbn-verifier)


(defn parse-isbn [isbn]
  (->> isbn
    (re-seq #"(\d)-?(\d)(\d)(\d)-?(\d)(\d)(\d)(\d)(\d)-?([\d|X]+)")
    (first)
    (rest)))


(defn valid-formula? [digits]
  (let [mod-11     #(mod % 11)
        factors    (map inc (range))
        int-digits (map #(Integer/parseInt %) digits)]
    (->>
      (map * int-digits factors)
      (reduce +)
      (mod-11)
      (zero?))))


(defn replace-x [digits]
  (-> digits
    (butlast)
    (concat ["10"])))


(defn isbn? [isbn]
  (let [digits      (parse-isbn isbn)
        too-short?  #(not= 10 (count %))
        too-long?   #(not= 1 (count (last %)))
        end-with-x? #(= "X" (last %))]
    (cond
      (too-short? digits) false
      (too-long? digits) false
      (end-with-x? digits) (valid-formula? (replace-x digits))
      :else (valid-formula? digits))))
