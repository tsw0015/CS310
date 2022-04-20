member :: a -> lizt -> Bool
member null lizt = False
member head lizt == a = True
member n = member a tail lizt

main = do
    print (member 5 [1,2,3,4,5])