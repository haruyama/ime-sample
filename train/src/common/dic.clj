(ns common.dic)

(require '[clojure.contrib.string :as string])

(def ht (new java.util.HashMap))


(defn- add [r w]
  (if (.containsKey ht r)
    (.add (.get ht r) w)
    (do
      (let [s (new java.util.HashSet)]
        (.add s w)
        (.put ht r s)))))

(defn f [s]
  (if (.containsKey ht s)
    (.get ht s)
    java.util.Collections/EMPTY_SET))

(defn init [filename]
  (with-open [fr (new java.io.FileReader filename)
              br (new java.io.BufferedReader fr)]
    (doseq [line (take-while (comp not nil?) (repeatedly #(.readLine br)))]
      (let [row (string/split #"\t" line)]
        (add (str (first row)) (str (nth row 1)))))))