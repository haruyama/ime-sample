(ns ime.common.dic)

(defn make []
  (let [ht (atom (hash-map))
        add (fn [r w]
              (if (contains? @ht r)
                (let [s (get @ht r)]
                  (reset! s (conj @s w)))
                (let [s (atom (hash-set w))]
                  (reset! ht (assoc @ht r s)))))

        find- (fn [s]
                ;      @(get @ht s (atom (hash-set)))
                (if (contains? @ht s)
                  @(get @ht s)
                  (hash-set)))

        common-prefix-search (fn [s, m]
                               (let [result (atom [])]
                                 (doseq [i (range 1 (+ 1 (min m (count s))))]
                                   (let [r (subs s 0 i)]
                                     (doseq [w (find- r)]
                                       (reset! result (conj @result [r w])))))
                                 @result))
        ]
    {:add add :find find- :common_prefix_search common-prefix-search}
    ))
