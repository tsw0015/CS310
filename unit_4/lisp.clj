(defn member [atm lizt]
  (cond
    (nil? lizt) false
    (= atm (first lizt)) true
    :else (member atm (rest lizt)))
)

(defn -main 
  "Main function"
  [& args]
  (member 5 (4 5 6))
)


