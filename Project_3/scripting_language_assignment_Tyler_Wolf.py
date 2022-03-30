#!/usr/bin/env python3

"""Unit 3  Scripting Language Portion of Assignment for Tyler Wolf
    I acknowledge and agree to the acedemic integrity policy.
"""


def hypercake(n: int, k: int):

    def combinations(n: int, r: int):
        
        def factorial(num: int):
            if(num == 0):
                return 1
            else:
                return num * factorial(num-1)


        if (r >= 0) and (r <= n):
            return factorial(n)/(factorial(r)*(factorial(n-r)))
        else:
            return 0


    if(k == 0):
        return combinations(n, k)
    else:
        return combinations(n, k) + hypercake(n, k-1)


cuts = int(input("What is the number of cuts you want to use?\n"))
dimensions = int(input("What is the number of dimensions you want to use?\n"))
print("The answer to the hypercake problem with ",cuts," cuts and ",dimensions," dimensions is ", hypercake(cuts, dimensions))
