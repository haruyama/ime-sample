(ns ime.common.test.graph
  (:use [clojure.test]))

(require '[ime.common.node :as node])
(require '[ime.common.dic :as dic])
(require '[ime.common.graph :as graph])

(deftest graph
         (let [dic (dic/init)]
           ((:add dic) "あう" "あう")
           ((:add dic) "あう" "会う")
           ((:add dic) "みんな" "みんな")
           ((:add dic) "みん" "みん")
           (let [graph1 (graph/init dic "みんなあう")]
             (let [eos (graph1 :eos)]
;               (println (eos :read))
               (let [prevs_1-1 ((graph1 :get_prevs) eos)]
                 (is (= (count prevs_1-1) 3))
                 (is (= (count ((graph1 :get_prevs) (node/init "あう" "あう" 4))) 2))
                 )
               )
             )
           )
         )

