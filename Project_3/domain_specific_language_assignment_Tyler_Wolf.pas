(*Unit 3  Domain Specific Language Portion of Assignment for Tyler Wolf
    I acknowledge and agree to the acedemic integrity policy.*)
Program domain_specific_language_assignment_Tyler_Wolf;
uses Crt,sysutils;
var 
   cuts : char;
   dimensions : char;

function factorial(n: integer): integer;
   var
  result: integer;
begin
   if(n = 0) then
      result := 1
   else  
      result := n * factorial(n-1);
   factorial := result;
end;

function combinations(n: integer; r: integer): integer;
var
   result: integer;
begin
   if(r >= 0) and (r <= n) then
      result := integer(Trunc(factorial(n)/(factorial(r)*factorial(n-r))))
   else
      result := 0;
   writeln(result);
   combinations:=result;
end;

function hypercake(n: integer; k: integer): integer;
var
   result: integer;
begin
   if(k = 0) then
      result:=combinations(n,k)
   else  
      result:=combinations(n,k) + hypercake(n, k-1);
   hypercake:=result;
end;



begin
writeln('What is the number of cuts you want to use?');
cuts:=readkey;
writeln('What is the number of dimensions you want to use?');
dimensions:=readkey;

writeln('The answer to the hypercake problem with ' + ShortString(cuts) + ' cuts and ' + ShortString(dimensions) + ' dimensions is ' + ShortString(IntToStr(hypercake(integer(cuts), integer(dimensions)))));
end.