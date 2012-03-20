(ns ime.common.dic)

(require '[clojure.string :as string])

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

(defn read_dic [filename]
  (let [
        dic (make)
        ]
    (with-open [
                fr (java.io.FileReader. filename)
                br (java.io.BufferedReader. fr)
                ]
      (doseq [line (take-while (comp not nil?) (repeatedly #(.readLine br)))]
        (let [
              [r w pos] (string/split line #"\t" 3)
              ]
          ((dic :add) r w))))
    dic))

