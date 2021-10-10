%%% Fulladio 7 - Askisi 1 %%%

transition(S1,10,s2).
transition(S1,20,s3).
transition(S2,10,s3).
transition(S2,20,s4).
transition(S3,10,s4).
transition(S3,20,s5).
transition(S4,10,s5).
transition(S4,20,s6).
transition(S5,10,s6).
transition(S1,50,s6).

coins_to_insert(State,[],State).

coins_to_insert(CurrentState,[X|Rest],State):-
	transition(CurrentState,X,NextState),
	coins_to_insert(NextState,Rest,State).

%%% Erotima 3 --> findall(L,coins_to_insert(s1,L,s6),List), length(List,N).







%%% Fulladio 7 - Askisi 2 %%%

connect(rb1,i1,b2).
connect(rb1,i1,b3).
connect(rb1,i2,b4).
connect(i1,i2,b1).
connect(rb2,i1,b5).
connect(rb2,i1,b6).
connect(rb2,i2,b7).

connection(Loc1,Loc2,Bridge):-
	connect(Loc1,Loc2,Bridge).

connection(Loc1,Loc2,Bridge):-
	connect(Loc2,Loc1,Bridge).

walk(Loc1,Loc2,Path):-
	walk(Loc1,Loc2,[],Path).

walk(Loc1,Loc2,Visited,[Bridge]):-
	connection(Loc1,Loc2,Bridge),
	not(member(Bridge,Visited)).

walk(Loc1,Loc2,Visited,[Bridge|Rest]):-
	connection(Loc1,LocX,Bridge),
	not(member(Bridge,Visited)),
	walk(LocX,Loc2,[Bridge|Visited],Rest).

%%% Erotima 3 --> not(walk(X,Y,L),length(L,7)). ----> no ara isxuei.