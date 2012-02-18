(ns train.core)
(use '[common.dic])

(defn -main [& args]
  (common.dic/init (nth args 0))
;  (println (common.dic/f "あう"))
;  (println (common.dic/f "あうi"))
  )
