(ns ime.test (:gen-class))

(require '[ime.common.dic :as dic])
(require '[ime.common.graph :as graph])
(require '[ime.common.decoder :as decoder])
(require '[clojure.string :as string])

(defn- read_weight_map [filename dic]
  (with-open [
              fr (java.io.FileReader. filename)
              br (java.io.BufferedReader. fr)
              ]
    (let [
          w (atom (hash-map))
          ]
      (doseq [line (take-while (comp not nil?) (repeatedly #(.readLine br)))]
        (let [
              [f s] (string/split (string/trimr line) #"\t\t" 2)
              ]
          (swap! w assoc f (read-string s))
          (let [
                b (string/split f #"\t")
                ]
            (if (and (= (count b) 2)
                     (= (get (first b) 0) \S)
                     (= (get (second b) 0) \R)
                     )
              (let [
                    w (subs (first b) 1)
                    r (subs (second b) 1)
                    ]
                ((dic :add) r w))))))
      @w
      )
    )
  )

(defn- read_dic [filename, dic]
  (with-open [
              fr (java.io.FileReader. filename)
              br (java.io.BufferedReader. fr)
              ]
    (doseq [line (take-while (comp not nil?) (repeatedly #(.readLine br)))]
      (let [
            [r w pos] (string/split line #"\t" 3)
            ]
        ((dic :add) r w)))))

(defn -main [& args]
  (let [dic (dic/read_dic "juman.dic")
        weight (read_weight_map "mk.model" dic)
        decoder (decoder/make [(fn [n] (str "S" (n :word))) (fn [n] (str "S" (n :word) "\tR" (n :read)))] [(fn [pn n] (str "S" (pn :word) "\tS" (n :word)))])
        ]
    (with-open [br (java.io.BufferedReader. *in*)]
      (loop []
        (let [line (.readLine br)]
          (if (not (nil? line))
            (do
              (let [graph (graph/make dic line)]
                (println (string/join " " (map first ((decoder :viterbi) graph weight))))
                )
              (recur))))))))
