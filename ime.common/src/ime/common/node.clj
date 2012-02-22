(ns ime.common.node)

(defn init [w r endpos]
  (let [score (atom 0.0) prev (atom false)]

    (defn set-score! [s]
;      (dosync (ref-set score s))
      (reset! score s)
      )

    (defn set-prev! [p]
;      (dosync (ref-set prev p))
      (reset! prev p)
      )

    (defn length []
      (count r))

    (defn is-bos? []
      (if (= endpos 0) true false))

    (defn is-eos? []
      (if (and (= (length) 0) (not (= endpos 0))) true false))

    {:endpos endpos :word w :read r :get_score (fn [] @score) :get_prev (fn [] @prev) :set_score! set-score! :set_prev! set-prev!
     :length length :is_bos? is-bos? :is_eos? is-eos?
     }
    )
  )

