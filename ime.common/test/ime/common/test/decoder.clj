(ns ime.common.test.decoder
  (:use [ime.common.decoder])
  (:use [clojure.test]))

(require '[ime.common.dic :as dic])
(require '[ime.common.node :as node])
(require '[ime.common.graph :as graph])
(require '[ime.common.decoder :as decoder])

(deftest decoder1
         (def weight {"S不良\tRふりょう" 0.1 "S独特\tSな" 0.3})
         (def node1 (node/init "不良" "ふりょう" 1))
         (def node2 (node/init "独特" "どくとく" 1))
         (def node3 (node/init "な" "な" 1))
         (def decoder (decoder/init [(fn [n] (str "S" (n :word) "\tR" (n :read)))] [(fn [pn n] (str "S" (pn :word) "\tS" (n :word)))]))
         (is (= ((decoder :get_node_score) node1 false weight) 0.1))
         (is (= ((decoder :get_node_score) node2 false weight) 0.0))

         (is (= ((decoder :get_edge_score) node2 node3 false weight) 0.3))
         (is (= ((decoder :get_edge_score) node1 node3 false weight) 0.0))
         )

(deftest decoder2
         (let [dic (dic/init)]
           ((:add dic) "あう" "あう")
           ((:add dic) "あう" "会う")
           ((:add dic) "みんな" "みんな")
           ((:add dic) "みん" "みん")
           (let [
                 graph (graph/init dic "みんなあう")
                 weight {"S会う\tRあう" 0.5 "Sみんな\tS会う" 0.1}
                 decoder (decoder/init [(fn [n] (str "S" (n :word) "\tR" (n :read)))] [(fn [pn n] (str "S" (pn :word) "\tS" (n :word)))])
                 ]
;             (println ((graph :get_nodes)))
;             (println (((node/init "あう" "あう" 5) :length)))
;             (println ((graph :get_prevs) (node/init "あう" "あう" 5)))
             (println ((decoder :viterbi) graph weight))
             )
           )
         )
