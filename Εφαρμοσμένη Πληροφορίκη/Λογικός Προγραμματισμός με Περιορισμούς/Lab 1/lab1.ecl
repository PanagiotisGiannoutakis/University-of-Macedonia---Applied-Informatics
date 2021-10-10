%%% Fulladio 1 - Askisi 1 %%%

and(0,0,0).
and(0,1,0).
and(1,0,0).
and(1,1,1).

xor_gate(0,0,0).
xor_gate(0,1,1).
xor_gate(1,0,1).
xor_gate(1,1,0).

or(0,0,0).
or(1,0,1).
or(0,1,1).
or(1,1,1).

half_adder(X,Y,S,C):-
	xor_gate(X,Y,S),
	and(X,Y,C).

full_adder(X,Y,C,S,C1):-
	xor_gate(X,Y,Output1),
	and(X,Y,Output2),
	xor_gate(C,Output1,S),
	and(Output1,C,Output3),
	or(Output3,Output2,C1).	

three_bit_adder(X2,Y2,X1,Y1,X0,Y0,C0,S0,S1,S2,C3):-
	full_adder(X0,Y0,C0,S0,C1),
	full_adder(X1,Y1,C1,S1,C2),
	full_adder(X2,Y2,C2,S2,C3).








%%% Fulladio 1 - Askisi 2 %%%

%% Company Data 
employee(name(john),position(programmer),wage(40000)).
employee(name(alice),position(programmer),wage(35000)).
employee(name(peter),position(uxdesigner),wage(25000)).
employee(name(nick),position(accountant),wage(60000)).
employee(name(helen),position(project_leader),wage(140000)).
employee(name(bob),position(programmer),wage(15000)).
employee(name(mathiew),position(project_namager),wage(50000)).
employee(name(donald),position(public_relations),wage(100000)).
employee(name(igor),position(server_admin),wage(20000)).


data(john,status(married,children(2))).
data(alice,status(single,children(0))).
data(peter,status(married,children(1))).
data(nick,status(married,children(3))).
data(helen,status(single,children(2))).
data(bob,status(single,children(0))).
data(mathiew,status(married,children(1))).
data(donald,status(single,children(1))).
data(igor,status(married,children(1))).
 
wage(Empl,Wage):-
	employee(name(Empl),_,wage(Wage)).

single_with_children(Empl,N):-
	data(Empl,status(single,children(N))),
	N > 0.

benefit(Name,Wage,0):-
	data(Name,status(single,children(0))),
	employee(name(Name),_,wage(Wage)).

benefit(Name,Wage,1500):-
	data(Name,status(single,children(N))),
	N > 0,
	employee(name(Name),_,wage(Wage)).

benefit(Name,Wage,500):-
	data(Name,status(married,children(0))),
	employee(name(Name),_,wage(Wage)).

benefit(Name,Wage,1100):-
	data(Name,status(married,children(N))),
	N > 0,
	employee(name(Name),_,wage(Wage)).





%%% Fulladio 1 - Askisi 3 %%%

profession(smith).
profession(baker).
profession(carpenter).
profession(tailor).

professions(S,B,C,T,SonS,SonB,SonC,SonT):-
	%%% oi paterades den exoun to idio epaggelma me to onoma toys	
	profession(S),S\=smith,
	profession(B),B\=baker,
	profession(C),C\=carpenter,
	profession(T),T\=tailor,
	
	%%% oi gioi den exoun to idio epaggelma me to onoma toys
	profession(SonS),SonS\=smith,
	profession(SonB),SonB\=baker,
	profession(SonC),SonC\=carpenter,
	profession(SonT),SonT\=tailor,

	%%% oi paterades exoun diaforetika epaggelmata
	S\=B,S\=C,S\=T,
	B\=C,B\=T,
	C\=T,

	%%% oi gioi exoun diaforetika epaggelmata
	SonS\=SonB,SonS\=SonC,SonS\=SonT,
	SonB\=SonC,SonB\=SonT,
	SonC\=SonT,
	
	%%% oi gioi kai oi paterades exoun diaforetika epaggelmata
	S\=SonS,B\=SonB,C\=SonC,T\=SonT,

	%%% o baker exei to idio epaggelma me ton gio toy carpenter
	B=SonC,

	%%% o gios toy smith einai baker
	SonS=baker.







%%% Fulladio 1 - Askisi 4 %%%

%%% add commands
command(add_r1,state(acc(X),reg1(Y),R2,R3),state(acc(X+Y),reg1(Y),R2,R3)).
command(add_r2,state(acc(X),R1,reg2(Y),R3),state(acc(X+Y),R1,reg2(Y),R3)).
command(add_r3,state(acc(X),R1,R2,reg3(Y)),state(acc(X+Y),R1,R2,reg3(Y))).

%%% subtract commands
command(subtract_r1,state(acc(X),reg1(Y),R2,R3),state(acc(X-Y),reg1(Y),R2,R3)).
command(subtract_r2,state(acc(X),R1,reg2(Y),R3),state(acc(X-Y),R1,reg2(Y),R3)).
command(subtract_r3,state(acc(X),R1,R2,reg3(Y)),state(acc(X-Y),R1,R2,reg3(Y))).

%%% store commands
command(store_r1,state(acc(X),reg1(_),R2,R3),state(acc(X),reg1(X),R2,R3)).
command(store_r2,state(acc(X),R1,reg2(_),R3),state(acc(X),R1,reg2(X),R3)).
command(store_r3,state(acc(X),R1,R2,reg3(_)),state(acc(X),R1,R2,reg3(X))).

%%% load commands
command(load_r1,state(acc(_),reg1(X),R2,R3),state(acc(X),reg1(X),R2,R3)).
command(load_r2,state(acc(_),R1,reg2(X),R3),state(acc(X),R1,reg2(X),R3)).
command(load_r3,state(acc(_),R1,R2,reg3(X)),state(acc(X),R1,R2,reg3(X))).

%%% findOps/3
findOps(X,Y,Z):-
	command(X,state(acc(c1),reg1(0),reg2(c2),reg3(c3)),InBetween),
	command(Y,InBetween,InBetween2),
	command(Z,InBetween2,state(acc(_),reg1(c1-c2+c3),reg2(_),reg3(_))).






%%% Fulladio 1 - Askisi 5 %%%

%%% figure/2

figure(1,middle(triangle,square)).
figure(2,middle(circle,triangle)).
figure(3,middle(square,circle)).
figure(4,middle(square,square)).
figure(5,middle(square,triangle)).
figure(6,middle(triangle,circle)).
figure(7,middle(circle,square)).
figure(8,middle(triangle,triangle)).
figure(9,bottomLeft(circle,circle)).
figure(10,topLeft(circle,circle)).
figure(11,bottomRight(circle,circle)).
figure(12,topRight(circle,circle)).
figure(13,topLeft(square,square)).
figure(14,bottomLeft(square,square)).
figure(15,topRight(square,square)).
figure(16,bottomRight(square,square)).


%%% relation/3

relation(middle(S1,S2),middle(S2,S1),inverse).

relation(middle(S1,S1),middle(S2,S2),same_in_out).

relation(topLeft(S1,S2),bottomRight(S1,S2),opposite).
relation(topRight(S1,S2),bottomLeft(S1,S2),opposite).
relation(bottomRight(S1,S2),topLeft(S1,S2),opposite).
relation(bottomLeft(S1,S2),topRight(S1,S2),opposite).


relation(topLeft(S1,S2),bottomLeft(S1,S2),same_side).
relation(bottomLeft(S1,S2),topLeft(S1,S2),same_side).
relation(topRight(S1,S2),bottomRight(S1,S2),same_side).
relation(bottomRight(S1,S2),topRight(S1,S2),same_side).

relation(topLeft(S1,S2),topRight(S1,S2),top_half).
relation(topRight(S1,S2),topLeft(S1,S2),top_half).

relation(bottomLeft(S1,S2),bottomRight(S1,S2),bottom_half).
relation(bottomRight(S1,S2),bottomLeft(S1,S2),bottom_half).

relation(topLeft(S1,S2),topRight(S1,S2),opposite_sides).
relation(topRight(S1,S2),topLeft(S1,S2),opposite_sides).
relation(bottomLeft(S1,S2),bottomRight(S1,S2),opposite_sides).
relation(bottomRight(S1,S2),bottomLeft(S1,S2),opposite_sides).


%%% analogy/4

analogy(F1,F2,F3,F4):-figure(F1,R1),figure(F2,R2),relation(R1,R2,X),
                      figure(F3,R3),figure(F4,R4),relation(R3,R4,X),
                      F4\=F3.