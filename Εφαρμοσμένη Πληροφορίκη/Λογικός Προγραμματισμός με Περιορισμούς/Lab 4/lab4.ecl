%%% Fulladio 4 - Askisi 1 %%%

symmetric(L):-
	append(L1,L1,L).



end_sublist(Sub,List):-
	append(_,Sub,List).



twice_sublist(L,L1):-
	append(LTemp1,LTemp2,L1),
	sublist(L,LTemp1),
	sublist(L,LTemp2).

sublist(L1,L2):-
	append(Ltemp,_,L2),
	append(_,L1,Ltemp).



last_element(List,X):-
	append(_,[X],List).







%%% Fulladio 4 - Askisi 2 %%%

word([p,r,o,l,o,g]).
word([m,a,t,h,s]).

missing_letter(WordList,X,W):-
	delete(X,W,WordList),
	word(W).






%%% Fulladio 4 - Askisi 3 %%%

reverse_alt([],[]).

reverse_alt([H|T],L):-
	reverse_alt(T,T1),
	append(T1,[H],L).




%%% Fulladio 4 - Askisi 4 %%%

rotate_left(N,List,Rotated):-
	append(



rotate_left(N,List,Rotated):-
	length(L1,N),
	append(L1,L2,List),
	append(L2,L1,Rotated).

%%%%%%%%% Enallaktiki Lusi %%%%%%%%%%%%%%%

rotate_left(0,List,List).
rotate_left(_,[],[]).

rotate_left(Pos,[H|T],RotatedList):-
	append(T,[H],List),
	TempPos is Pos-1,
	TempPos >= 0,
	rotate_left(TempPos,List,RotatedList).







%%% Fulladio 4 - Askisi 5 %%%

atom(hydrogen,[h1,h2,h3,h4,h5,h6,h7]).
atom(carbon,[c1,c2,c3,c4,c5,c6,c7]).
atom(chlorine,[cl]).

atom_bonds_to(h1,[c1]).
atom_bonds_to(h2,[c3]).
atom_bonds_to(h3,[c3]).
atom_bonds_to(h4,[c5]).
atom_bonds_to(h5,[c3]).
atom_bonds_to(h6,[c6]).
atom_bonds_to(h7,[c7]).

atom_bonds_to(c1,[c2,c4,h1]).
atom_bonds_to(c2,[c1,c5,cl]).
atom_bonds_to(c3,[c4,h2,h3,h5]).
atom_bonds_to(c4,[c1,c3,c6]).
atom_bonds_to(c5,[c2,c7,h4]).
atom_bonds_to(c6,[c4,c7,h6]).
atom_bonds_to(c7,[c5,c6,h7]).

atom_bonds_to(cl,[c2]).

carbon(X):-
	atom(carbon,List),
	member(X,List).

hydrogen(X):-
	atom(hydrogen,List),
	member(X,List).

bonded(X,Y):-
	atom_bonds_to(X,List),
	member(Y,List).

methyl(X):-
	carbon(X),
	atom_bonds_to(X,List),
	member(h2,List),
	member(h3,List),
	member(h5,List).







%%% Fulladio 4 - Askisi 6 %%%

%%% Me tin proti append xorizoume tin proti lista
%%% sto suffix pou theloume kai to proto tis kommati
%%% pou den mas endiaferei. Paromoia douleia ginetai
%%% kai stin deuteri lista. Telos, vriskoume to Pos 
%%% apla metrontas to megethos tis listas Suffix.
%%% Afou panta to Suffix tha einai sto telos tis listas.

common_suffix(L1,L2,Suffix,Pos):-
	append(_L1Prefix,Suffix,L1),
	append(_L2Prefix,Suffix,L2),
	length(Suffix,Pos).