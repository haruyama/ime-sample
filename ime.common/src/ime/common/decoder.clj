(ns ime.common.decoder)

(require '[ime.common.node :as node])
(require '[ime.common.graph :as graph])

(defn make [node-features edge-features]
  (defn get-node-score [node gold w]
    (reduce +
            (map (fn [f] (get w (f node) 0.0)) node-features)
            )
    )
  (defn get-edge-score [prev-node node gold w]
    (reduce +
            (map (fn [f] (get w (f prev-node node) 0.0)) edge-features)
            )
    )

  (defn get-viterbi-result-list [node]
    (defn iter [n l]
      (if ((n :is_bos?))
        l
        (iter ((n :get_prev))
              (cons [(n :word) (n :read)] l)
              )
        )
      )
    (iter node '())
    )

  (defn viterbi [graph w & {:keys [gold] :or {gold false}}]
    (doseq [nodes ((graph :get_nodes))]
      (doseq [node nodes]
        (if (not ((node :is_bos?)))
          (do
            ((node :set_score!) -1000000.0)
            (let [node-score-cache (get-node-score node gold w)]
              (doseq [prev-node ((graph :get_prevs) node)]
                (let [tmp-score (+ ((prev-node :get_score)) (get-edge-score prev-node node gold w) node-score-cache)]
;                  (println (str (prev-node :word) "---" (node :word)))
;                  (println node-score-cache)
;                  (println tmp-score)
;                  (println "----")
                  (if (>= tmp-score ((node :get_score)))
                    (do
                      ((node :set_score!) tmp-score)
                      ((node :set_prev!) prev-node)
                      )
                    )
                  )
                )
              )
            )
          )
        )
      )
    (get-viterbi-result-list (((graph :eos) :get_prev)))
    )
  {:get_node_score get-node-score :get_edge_score get-edge-score
   :viterbi viterbi}
  )
