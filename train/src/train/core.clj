(ns train.core)
(require '[common.dic])

(defn -main [& args]
  (let [dic (common.dic/init (nth args 0))]
    (println dic)
    (println ((:find dic) "あう"))
    )
  )
