(ns ime.common.graph)

(require '[ime.common.node :as node])

(defn init [dic s]
  (let [nodes (atom (hash-map))
        bos (node/init "" "" 0)
        eos (node/init "" "" (+ 1 (count s)))
        ]
    (reset! nodes (assoc @nodes 0 (atom [bos])))
    (reset! nodes (assoc @nodes (+ 1 (count s)) (atom [eos])))

    (doseq [i (range 0 (count s))]
      (do
        (doseq [j (range (+ 1 i) (+ 1 (min (count s) (+ i 16))))]
          (let [r (subs s i j)]
            (doseq [w ((dic :find) r)]
              (let [node (node/init w r j)]
                (if (contains? @nodes j)
                  (reset! (get @nodes j) (conj @(get @nodes j) node))
                  (reset! nodes (assoc @nodes j (atom [node]))))))))
        (let [r (subs s i (+ 1 i))]
          (if (not (= r ""))
            (let [node (node/init r r (+ i 1))]
              (if (contains? @nodes (+ i 1))
                (reset! (get @nodes (+ i 1)) (conj @(get @nodes (+ i 1)) node))
                (reset! nodes (assoc @nodes (+ i 1) (atom [node]))))))))
      )

    (defn get-prevs [node]
;      (println ((node :is_eos?)))
;      (println (node :endpos))
;      (println ((node :length)))
      (cond ((node :is_eos?)) @(get @nodes (- (node :endpos) 1))
            ((node :is_bos?)) []
            :else @(get @nodes (- (node :endpos) ((node :length))))
            ))

    {:get_nodes (fn [] (map (fn [n] @n) (vals @nodes)))
     :eos eos
     :get_prevs get-prevs
     }
    )
  )



