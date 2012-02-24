(ns ime.common.graph)

(require '[ime.common.node :as node])

(defn make [dic s]
  (let [nodes (vec (map atom (repeat (+ 2 (count s)) [])))
        ]
    (dotimes [i (count s)]
;      (println eos)
;      (println (eos :is_eos?))
;      (println "hoge")
;      (println ((eos :is_eos?)))
      (do
        (doseq [j (range (+ 1 i) (+ 1 (min (count s) (+ i 16))))]
          (let [r (subs s i j)]
            (doseq [w ((dic :find) r)]
;              (println ((eos :is_eos?)))
              (let [n (node/make w r j)]
;                (println ((eos :is_eos?)))
                (swap! (nth nodes j) conj n)))))
;        (println ((eos :is_eos?)))
        (let [r (subs s i (+ 1 i))]
          (let [n (node/make r r (+ i 1))]
            (swap! (nth nodes (+ i 1)) conj n)))))

    (let [
          eos (node/make "" "" (+ 1 (count s)))
          bos (node/make "" "" 0)
          ]
      (swap! (nth nodes 0) conj  bos)
      (swap! (nth nodes (+ 1 (count s))) conj eos)
;      (map (fn [n] ((n :is_eos?))) (flatten (fn [] (map (fn [n] @n) nodes))))


      (defn get-prevs [node]
;        (println (node :endpos))
;        (println ((node :length)))
;              (println (node :read))
;              (println ((node :length)))
;              (println ((node :is_bos?)))
;              (println ((node :is_eos?)))
        (cond ((node :is_eos?)) @(nth nodes (- (node :endpos) 1))
              ((node :is_bos?)) []
              :else @(nth nodes (- (node :endpos) ((node :length))))
              ))

      {:get_nodes (fn [] (map (fn [n] @n) nodes))
       :eos eos
       :get_prevs get-prevs
       }
      )
    )
  )

