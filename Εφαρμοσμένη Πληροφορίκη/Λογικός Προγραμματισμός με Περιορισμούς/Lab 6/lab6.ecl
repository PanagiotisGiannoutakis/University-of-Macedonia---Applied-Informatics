%%% Fulladio 6 - Askisi 1 %%%

sample(2).
sample(5).
sample(14).
sample(7).
sample(26).

less_than_ten(X):-
	findall(Var,(sample(Var),Var<10),Solutions),
	length(Solutions,X).





%%% Fulladio 6 - Askisi 2 %%%

set_diff_f(L1,L2,L):-
	findall(X,(member(X,L1),not(member(X,L2))),L).






%%% Fulladio 6 - Askisi 3 %%%

minlist(Min,List):-
	setof(Term,member(Term,List),[Min|_]).
 




%%% Fulladio 6 - Askisi 4 %%%

proper_set_s(List):-
	setof(Term,member(Term,List),List).







%%% Fulladio 6 - Askisi 5 %%%

map_f(Op,List,Result):-
	findall(Y,map_aux(Op,List,Y),Result).

map_aux(Op,List,Res):-
	member(X,List),
	C=..[Op,X,Res],
	call(C).

double(X,Y):-
	Y is X + X.

square(X,Y):-
	Y is X * X.







%%% Fulladio 6 - Askisi 6 %%%

:-dynamic stack/1.

%pop(X):-
	%retract(stack([X|T]),
	%assert(stack(T)).

push(X):-
	asserta(stack(X)).

pop(X):-
	retract(stack(X)).









%%% Fulladio 6 - Askisi 7 %%%

Vlepe arxeio operators.pl




















%%% Fulladio 6 - Askisi 8 %%%

:-op(500,yfx,and).
:-op(500,yfx,or).
:-op(500,yfx,nor).
:-op(500,yfx,nand).
:-op(500,yfx,xor).

:-op(400,fy,--).
:-op(600,xfx,==>).
:-op(600,xfx,<==>).

--Arg1:-not(Arg1).
 
Arg1 ==> Arg2 :- Arg1 or --Arg2.

Arg1 and Arg2 :- Arg1, Arg2.

Arg1 or _Arg2 :- Arg1.
_Arg1 or Arg2 :-Arg2.

Arg1 xor Arg2 :- Arg1, --Arg2.
Arg1 xor Arg2 :- --Arg1, Arg2.

Arg1 nor Arg2 :- --(Arg1 or Arg2).
Arg1 nand Arg2 :- --(Arg1 and Arg2).

t. 
f:-!,fail.

%%% variable/1
variable([]).

variable([t|T]):-
	variable(T).

variable([f|T]):-
	variable(T).

%%% model/1
model(Exp):-
	term_variables(Exp,Vars),
	variable(Vars),
	Exp.

%%% theory/1
theory([]).

theory([H|T]):-
	model(H),
	theory(T).

%%% orismos telesti <==>

t <==> X or t:-
	setof(Y,theory([Y ==> X or Y, X or Y ==> Y]),_).