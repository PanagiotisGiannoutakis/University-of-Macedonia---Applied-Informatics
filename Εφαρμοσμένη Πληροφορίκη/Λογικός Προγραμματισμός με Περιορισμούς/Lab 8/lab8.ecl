%%% Fulladio 8 - Askisi 2 %%%

:-lib(ic).
:-lib(ic_global).
:-lib(branch_and_bound).
:-lib(ic_edge_finder).

weight(10).
weight(20).
weight(30).
weight(50).
weight(60).
weight(90).
weight(100).
weight(150).
weight(250).
weight(500).

balance_lights(Weights, Sum):-
	Weights = [W0,W1,W2,W3],
	findall(W,weight(W),Ws),
	Weights #:: Ws,
	ic_global:alldifferent(Weights),
	5*W0 #= W1*5 + 20*W2 + 40*W3,
	labeling(Weights),
	sumlist(Weights,Sum).






%%% Fulladio 8 - Askisi 3 %%%

:-lib(ic).
:-lib(ic_global).
:-lib(branch_and_bound).

num_gen([D1,5,D2,D3,3],[D4,D5,0,D6,1]):-
	[D1,D2,D3,D4,D5,D6] #:: [0..9],
	ic:alldifferent([D1,D2,D3,D4,D5,D6,5,3,0,1]),
	Num1 #= D1*10000 + 5*1000 + D2*100 + D3*10 + 3,
	Num2 #= D4*10000 + D5*1000 + 100*0 + D6*10 + 1,
	12848 #= abs(Num1 - Num2),
	labeling([D1,D2,D3,D4,D5,D6]).
	








%%% Fulladio 8 - Askisi 5 %%%

item(pizza,12).
item(burger,14).
item(kingburger,18).
item(platSurprise,15).

menu(Amount,Order):-
	Prices = [P1,P2,P3,P4],
	findall(P,item(_,P),Ps),
	Prices #:: Ps,
	P1*D1 + P2*D2 + P3D3 + P4*D4 #<= 50,
	labeling