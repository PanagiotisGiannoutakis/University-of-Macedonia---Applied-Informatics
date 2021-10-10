%%% Fulladio 5 - Askisi 2 %%%

set_diff([],_,[]).

set_diff([H1|T1],List2,[H1|T]):-
	not(member(H1,List2)),!,set_diff(T1,List2,T).

set_diff([_|T1],List2,T):-
	set_diff(T1,List2,T).





%%% Fulladio 5 - Askisi 3 %%%

lunion([],List,List).
lunion(List,[],List).

lunion([H1|T1],List2,[H1|T]):-
	not(member(H1,List2)),
	!,
	lunion(T1,List2,T).

lunion([_|T1],List2,T):-
	lunion(T1,List2,T).







%%% Fulladio 5 - Askisi 4 %%%

max_list(Max,List):-
	member(Max,List),
	not((member(Y,List), Y>Max)).







%%% Fulladio 5 - Askisi 5 %%%

unique_element(X,L):-
	delete(X,L,LRemoved),
	not(member(X,LRemoved)).






%%% Fulladio 5 - Askisi 6 %%%

proper_set(List):-
	member(X,List),
	unique_element(X,List).







%%% Fulladio 5 - Askisi 7 %%%

%%%double([X],[Y]):-
%%%	Y is X * 2.

%%%double([H|T],[Y|ResultTemp]):-
%%%	double(T,ResultTemp),
%%%	Y is H * 2.

%%%square([X],[Y]):-
%%%	Y is X * X.

%%%square([H|T],[Y|ResultTemp]):-
%%%	double(T,ResultTemp),
%%%	Y is H * H.

double(X,Y):-
	Y is X * 2.

square(X,Y):-
	Y is X * X.

map(_,[],[]).
	
map(Oper,[H|T],[R|ResList]):-
	Predicate =..[Oper,H,R],
	call( Predicate ),
	map(Oper,T,ResList).






%%% Fulladio 5 - Askisi 8 %%%

reduce(_,[],0).
reduce(_,[X],X).

reduce(Operation,[H1,H2|T],Result):-
	C =..[Operation,H1,H2,R1],
	call(C),
	reduce(Operation,T,R2),
	C2 =..[Operation,R1,R2,Result],
	call(C2).


%%% Enallaktiki Lusi - Sakellariou %%%

reduce(_,[X],X).

reduce(Op,[A,B|Tail],Res):-
	C =..[Op,A,B,R],
	call(C),
	reduce(Op,[R|Tail],Res).





%%% Fulladio 5 - Askisi 9 %%%

valid_queries(Q):-
	call(Q),
	write(Q),
	nl,
	fail.