(ns ime.common.test.dic
  (:use [ime.common.dic])
  (:use [clojure.test]))

(deftest dic
         (let [dic (ime.common.dic/make)]
           ((:add dic) "あう" "あう")
           ((:add dic) "あう" "合う")
           ((:add dic) "みんな" "みんな")
           (is (= ((:find dic) "あう") (hash-set "あう" "合う")))

           (is (= ((dic :find) "あ") (hash-set)))
           (is (= (count ((:find dic) "あ")) 0))

           (is (= ((:common_prefix_search dic) "あ" 16) []))

           (is (= ((:common_prefix_search dic) "あう" 16) [["あう" "あう"] ["あう" "合う"]]
                  ))
           (is (= ((:common_prefix_search dic) "あう" 1) []))
           )
         )

