(ns ime.common.node)

(defn make [w r endpos]
  (let [score (atom 0.0)
        prev (atom nil)
        set-score! (fn [s] (reset! score s))
        set-prev! (fn [p] (reset! prev p))
        is-bos? (fn [] (if (= endpos 0) true false))
        length (fn [] (count r))
        is-eos? (fn [] (if (and (= (length) 0) (not (= endpos 0))) true false))
        ]
        {:endpos endpos :word w :read r
         :get_score (fn [] @score) :get_prev (fn [] @prev)
         :set_score! set-score! :set_prev! set-prev!
         :length length :is_bos? is-bos? :is_eos? is-eos?
         }
    )
  )
