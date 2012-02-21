(ns ime.common.dic)

(defn init []
  (let [ht (new java.util.HashMap)]

    (defn add [r w]
      (if (.containsKey ht r)
        (.add (.get ht r) w)
        (do
          (let [s (new java.util.HashSet)]
            (.add s w)
            (.put ht r s)))))

    (defn find- [s]
      (if (.containsKey ht s)
        (.get ht s)
        java.util.Collections/EMPTY_SET))

    (defn common-prefix-search [s, m]
      (let [result (new java.util.ArrayList)]
        (doseq [i (range 1 (+ 1 (min m (count s))))]
          (let [r (subs s 0 i)]
            (doseq [w (find- r)]
              (.add result [r w])
              )
            )
          )
        result)
      )
    {:add add :find find- :common_prefix_search common-prefix-search}
    )
  )

