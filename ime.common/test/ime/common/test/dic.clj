(ns ime.common.test.dic
  (:use [ime.common.dic])
  (:use [clojure.test]))

(deftest dic
         (let [dic (ime.common.dic/init)]
           ((:add dic) "あう" "あう")
           ((:add dic) "あう" "合う")
           (is (= ((:find dic) "あう") (doto
                                        (new java.util.HashSet)
                                        (.add "あう")
                                        (.add "合う"))))
           (is (= (.size ((:find dic) "あ")) 0))

           (is (= ((:common_prefix_search dic) "あ" 16) (doto
                                        (new java.util.ArrayList)
                                                        )))

           (is (= ((:common_prefix_search dic) "あう" 16) (doto
                                        (new java.util.ArrayList)
                                        (.add ["あう" "あう"])
                                        (.add ["あう" "合う"])
                                                           ))
               )
           (is (= ((:common_prefix_search dic) "あ" 1) (doto
                                        (new java.util.ArrayList)
                                                        )))
           )
         )
