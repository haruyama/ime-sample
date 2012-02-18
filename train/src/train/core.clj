(ns train.core)
(require '[common.dic])

(defn -main [& args]
  (let [dic (common.dic/init)]
    (with-open [fr (new java.io.FileReader (first args))
                br (new java.io.BufferedReader fr)]
      (doseq [line (take-while (comp not nil?) (repeatedly #(.readLine br)))]
        ((:add_line dic) line)))
    (println ((:find dic) "あう"))
    (println ((:common_prefix_search dic) "あう" 16))
    )
  )
