/**
 * author: Tyler Wolf 
 * 
 * 	I accept and acknowledge the academic integrity agreement
 * 
 * 	Unit 5 
 * 	Date: 05/03/2022 
 */
log2(I,E):-
  (number(I) -> E is log(I)/log(2);
   number(E) -> I is 2**E).

lgstar(N,Iterations):-
  (N > 1
  ->(log2(N,Nprev), lgstar(Nprev,Iterationsprev), Iterations is Iterationsprev + 1);
    Iterations is 0).
