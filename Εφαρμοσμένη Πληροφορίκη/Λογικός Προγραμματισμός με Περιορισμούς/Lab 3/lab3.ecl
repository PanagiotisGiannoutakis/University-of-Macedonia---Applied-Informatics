%%% Fulladio 3 - Askisi 1 %%%

sumoflist([],0).

sumoflist([H|T],Sum):-
	sumoflist(T,SumTemp),
	Sum is H + SumTemp.






%%% Fulladio 3 - Askisi 2 %%%

before_last_element([X,Y],X).

before_last_element([H|T],El):-
	before_last_element(T,El).








%%% Fulladio 3 - Askisi 3 %%%

occurs(_,[],0).

occurs(Element,[Element|Tail],Times):-
	occurs(Element,Tail,TimesInTail),
	Times is TimesInTail + 1.

occurs(Element,[Head|Tail],Times):-
	Element \= Head,		% To get one solution only
	occurs(Element,Tail,Times).






%%% Fulladio 3 - Askisi 4 %%%

count_odd([],0).

count_odd([H|T],Result):-
	1 is H mod 2,
	count_odd(T,ResultTemp),
	Result is ResultTemp + 1.

count_odd([H|T],Result):-
	0 is H mod 2,
	count_odd(T,Result).







%%% Fulladio 3 - Askisi 5 %%%

vowel(a).
vowel(e).
vowel(i).
vowel(o).
vowel(u).

count_vowels([],0).

count_vowels([H|T],Result):-
	vowel(H),
	count_vowels(T,ResultTemp),
	Result is ResultTemp + 1.

count_vowels([H|T],Result):-
	not(vowel(H)),
	count_vowels(T,Result).







%%% Fulladio 3 - Askisi 6 %%%

sum_even([],0).

sum_even([H|T],Result):-
	0 is H mod 2,
	sum_even(T,ResultTemp),
	Result is ResultTemp + H.

sum_even([H|T],Result):-
	1 is H mod 2,
	sum_even(T,Result).







%%% Fulladio 3 - Askisi 7 %%%

%%%replace(_,_,[],[])

%%%replace(X,Y,[X|T],[Y|ResultList]):-
%%%	replace(X,Y,T,ResultList).

replace(X,Y,[X|T],[Y|T]).

replace(X,Y,[H|T],[H|T2]):-
	replace(X,Y,T,T2).