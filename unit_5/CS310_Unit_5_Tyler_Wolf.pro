log2(I,E):-
  (
    number(I)
    -> E is log(I)/log(2);
    number(E)
    -> I is 2**E
  ).

lgstar(N,A):-
  (N>1
  ->
    (
      log2(N,Nprev),
      lgstar(Nprev,Aprev),
      A is Aprev + 1
    );
     A is 0
  ).
