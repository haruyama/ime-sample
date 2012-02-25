(ns ime.common.test.graph
  (:use [clojure.test]))

(require '[ime.common.node :as node])
(require '[ime.common.dic :as dic])
(require '[ime.common.graph :as graph])

(deftest graph
         (let [dic (dic/make)]
           ((:add dic) "あう" "あう")
           ((:add dic) "あう" "会う")
           ((:add dic) "みんな" "みんな")
           ((:add dic) "みん" "みん")
           (let [graph (graph/make dic "みんなあう")]
             (let [eos (graph :eos)]
               (let [prevs ((graph :get_prevs) eos)]
                 (is (= (count prevs) 3))
                 (is (= (count ((graph :get_prevs) (node/make "あう" "あう" 4))) 2))
                 )
               )
             )
           )
         )

