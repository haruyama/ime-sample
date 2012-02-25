(ns ime.common.node)

(defn make [w r endpos]
  (let [score (atom 0.0)
        prev (atom nil)
        set-score! (fn [s] (reset! score s))
        set-prev! (fn [p] (reset! prev p))
        is-bos? (fn [] (if (= endpos 0) true false))
        length (fn []
                 ;      (println r)
                 (count r))
        is-eos? (fn []
                  ;      (println r)
                  ;      (println set-prev!)
                  (if (and (= (count r) 0) (not (= endpos 0))) true false)
;                        (if (and (= (length) 0) (not (= endpos 0))) true false)
                  )
        ]

;    (fn [m]
;      (cond (= m :endpos) endpos
;            (= m :word) w
;            (= m :read) r
;            (= m :get_score) (fn [] @score)
;            (= m :get_prev) (fn [] @prev)
;            (= m :set_score!) set-score!
;            (= m :set_prev!) set-prev!
;            (= m :length) length
;            (= m :is_bos?) is-bos?
;            (= m :is_eos?) is-eos?
;            ))
        {:endpos endpos :word w :read r
         :get_score (fn [] @score) :get_prev (fn [] @prev)
         :set_score! set-score! :set_prev! set-prev!
         :length length :is_bos? is-bos? :is_eos? is-eos?
         }
    )
  )
