(ns ime.common.test.node
  (:use [ime.common.node])
  (:use [clojure.test]))

(deftest node
         (def node1 (ime.common.node/make "あ" "あ" 0))
         (is (= true ((:is_bos? node1))))
         (is (= false ((:is_eos? node1))))
         (is (= 0.0 ((:get_score node1))))
         ((:set_score! node1) 1.0)
         (is (= 1.0 ((:get_score node1))))
         (is (= 1 ((:length node1))))


         (def node2 (ime.common.node/make "" "" 1))
         (is (= false ((:get_prev node2))))
         ((:set_prev! node2) node1)
         (is (= node1 ((:get_prev node2))))
         (is (= 0 ((:length node2))))

         (is (= false ((:is_bos? node2))))
         (is (= true ((:is_eos? node2))))
         (def node3 (ime.common.node/make "" "" 3))
         (is (= true ((:is_eos? node3))))
         )
