(ns ime.learn.learner)

(require '[ime.common.node :as node])
(require '[ime.common.graph :as graph])
(require '[ime.common.decoder :as decoder])
(require '[clojure.string :as string])

(defn make [dic node-features edge-features]
  (let [w (atom (hash-map))
        decoder (decoder/make node-features edge-features)
        learning-rate 0.1

        convert-to-nodes
        (fn [sentence]
          (defn- iter [sen ret prev i]
            (if (empty? sen)
              (let [
                    eos (node/make "" "" (+ i 1))
                    ]
                ((eos :set_prev!) prev)
                (conj ret eos))
              (let [
                    x (first sen)
                    j (+ i (count (second x)))
                    node (node/make (first x) (second x) j)
                    ]
                ((node :set_prev!) prev)
                (recur (rest sen) (conj ret node) node j))))
          (let [
                bos (node/make "" "" 0)
                ]
            (iter sentence [bos] bos 0)))

        update-node-score!
        (fn [node diff]
          ((dic :add) (node :read) (node :word))
          (doseq [func node-features]
            (let [
                  feature (func node)
                  ]
              (if (contains? @w feature)
                (swap! w assoc feature (+ (get @w feature) diff))
                (swap! w assoc feature diff)))))

        update-edge-score!
        (fn [prev-node node diff]
          (if (not (nil? prev-node))
            (doseq [func edge-features]
              (let [
                    feature (func prev-node node)
                    ]
                (if (contains? @w feature)
                  (swap! w assoc feature (+ (get @w feature) diff))
                  (swap! w assoc feature diff))))))

        update-parameters-body!
        (fn [sentence diff]
          (defn- iter [prev-node nodes]
            (if (not (empty? nodes))
              (let [
                    node (first nodes)
                    ]
                (update-node-score! node diff)
                (update-edge-score! prev-node node diff)
                (recur node (rest nodes)))))
          (iter nil (convert-to-nodes sentence))
          )

        update-parameters!
        (fn [sentence result]
          (update-parameters-body! sentence learning-rate)
          (update-parameters-body! result (* -1 learning-rate)))

        save
        (fn [filename]
          (with-open [
                      fw (java.io.FileWriter. filename)
                      bw (java.io.BufferedWriter. fw)
                      ]
            (doseq [feature (sort (keys @w))]
              (.write bw (str feature "\t\t" (get @w feature) "\n")))
            )
          )

        learn
        (fn [sentence]
          (let [
                s (string/join (map (fn [x] (second x)) sentence))
                graph (graph/make dic s)
                result ((decoder :viterbi) graph @w)
                ]
            (if (not= sentence result)
              (update-parameters! sentence result)))
          )

        ]
    {:learn learn :save save}
    )
  )
