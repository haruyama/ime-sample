(ns ime.learn (:gen-class))

(require '[ime.common.dic :as dic])
(require '[clojure.string :as string])
(require '[ime.learn.learner :as learner])

(defn -main [& args]
  (let [dic (dic/read_dic "juman.dic")
        learner (learner/make dic [(fn [n] (str "S" (n :word))) (fn [n] (str "S" (n :word) "\tR" (n :read)))] [(fn [pn n] (str "S" (pn :word) "\tS" (n :word)))])
        corpus_filename (first args)
        ]
    (with-open [
                fr (java.io.FileReader. corpus_filename)
                br (java.io.BufferedReader. fr)
                ]
      (doseq [line (take-while (comp not nil?) (repeatedly #(.readLine br)))]
        (let [
              sentence (map (fn [s] (string/split s #"/")) (string/split line #" "))
              ]
          ((learner :learn) sentence)))
      )
    ((learner :save) "test.model")))
