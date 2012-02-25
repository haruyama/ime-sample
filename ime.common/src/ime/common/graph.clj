(ns ime.common.graph)

(require '[ime.common.node :as node])

(defn make [dic s]
  (let [nodes (vec (map atom (repeat (+ 2 (count s)) [])))
        get-prevs (fn [node]
                    (cond ((node :is_eos?)) @(nth nodes (- (node :endpos) 1))
                          ((node :is_bos?)) []
                          :else @(nth nodes (- (node :endpos) ((node :length))))
                          ))
        eos (node/make "" "" (+ 1 (count s)))
        bos (node/make "" "" 0)
        ]
    (dotimes [i (count s)]
      (do
        (doseq [j (range (+ 1 i) (+ 1 (min (count s) (+ i 16))))]
          (let [r (subs s i j)]
            (doseq [w ((dic :find) r)]
              (let [n (node/make w r j)]
                (swap! (nth nodes j) conj n)))))
        (let [r (subs s i (+ 1 i))]
          (let [n (node/make r r (+ i 1))]
            (swap! (nth nodes (+ i 1)) conj n)))))
    (swap! (nth nodes 0) conj  bos)
    (swap! (nth nodes (+ 1 (count s))) conj eos)

    {:get_nodes (fn [] (map (fn [n] @n) nodes))
     :eos eos
     :get_prevs get-prevs
     }
    )
  )

