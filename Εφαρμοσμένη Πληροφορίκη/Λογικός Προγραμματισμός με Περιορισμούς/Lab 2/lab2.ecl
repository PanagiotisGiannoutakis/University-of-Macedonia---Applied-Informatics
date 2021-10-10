%%% Fulladio 2 - Askisi 2 %%%

power(_,0,1).

power(X, Y, C):-
	Y > 0,
	YTemp is Y - 1,
	power(X, YTemp, CTemp),
	C is X * CTemp.

power(X, Y, C):-
	Y < 0,
	YTemp is Y + 1,
	power(X, YTemp, CTemp),
	C is CTemp / X.






%%% Fulladio 2 - Askisi 3 %%%

fibonacci(1,1).
fibonacci(2,1).

fibonacci(X,Y):-
	XTemp1 is X - 1,
	XTemp2 is X - 2,
	fibonacci(XTemp1,YTemp1),
	fibonacci(XTemp2,YTemp2),
	Y is YTemp1 + YTemp2.






%%% Fulladio 2 - Askisi 4 %%%

mcd(0,N,N).

mcd(M,N,Result):-
	M > N,
	mcd(N,M,Result).

mcd(M,N,Result):-
	N >= M,
	X is N mod M,
	mcd(M,X,Result).






%%% Fulladio 2 - Askisi 5 %%%

divides(X,X):-
	X > 0.

divides(X,Y):-
	X > 0,
	Y > 0,
	YTemp is Y - X,
	divides(X,YTemp).

dividess(X,X,1):-
	X > 0.

dividess(X,Y,Result):-
	X > 0,
	Y > 0,
	YTemp is Y - X,
	dividess(X,YTemp,ResultNew),
	Result is ResultNew + 1.










%%% Fulladio 2 - Askisi 6 %%%

fn(0,1).

fn(N,N):-
	N > 0,
	5 > N.

fn(N,X):-
	N >= 5,
	N =< 8,
	NTemp is N - 4,
	fn(NTemp,XTemp),
	X is XTemp * 2.

fn(N,X):-
	N > 8,
	NTemp is N - 8,
	fn(NTemp,XTemp1),
	fn(XTemp1,XTemp2),
	X is XTemp2.







%%% Fulladio 2 - Askisi 7 %%%

%%% sumn/2
%%% To athroisma tis monadas einai 1.
sumn(1,1).

%%% sumn/2
%%% sumn(X,Y)
%%% To teliko apotelesma prokuptei apo tin athroisi tou telikou 
%%% arithmou me to apotelesma pou proekpuse apo tin athroisi
%%% tou telikou arithmou meion 1. Kai auto ginete anadromika
%%% mexri na ftasoume sto teliko apotelesma na einai 1 pou
%%% to exoume dilosei san gegonos pio pano.
sumn(X,Y):-
	X > 1,
	XTemp is X - 1,
	sumn(XTemp,YTemp),
	Y is X + YTemp.







%%% Fulladio 2 - Askisi 8 %%%

int_in_range(Min,Max,Min):-
	Min =< Max.

int_in_range(Min,Max,X):-
	Min =< Max,
	MinNew is Min + 1,
	int_in_range(MinNew,Max,X).



