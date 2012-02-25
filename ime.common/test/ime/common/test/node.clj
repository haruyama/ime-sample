(ns ime.common.test.node
  (:use [ime.common.node])
  (:use [clojure.test]))

(deftest node
         (def node1 (ime.common.node/make "あ" "あ" 0))
         (is (= true ((node1 :is_bos?))))
         (is (= false ((node1 :is_eos?))))
         (is (= 0.0 ((node1 :get_score))))
         ((node1 :set_score!) 1.0)
         (is (= 1.0 ((node1 :get_score))))
         (is (= 1 ((node1 :length))))


         (def node2 (ime.common.node/make "" "" 1))
         (is (= nil ((node2 :get_prev))))
         ((node2 :set_prev!) node1)
         (is (= node1 ((node2 :get_prev))))
         (is (= 0 ((node2 :length))))

         (is (= false ((node2 :is_bos?))))
         (is (= true ((node2 :is_eos?))))
         (def node3 (ime.common.node/make "" "" 3))
         (is (= true ((node3 :is_eos?))))
         )
