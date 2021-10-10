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
	X > 0,
	XTemp is X - 1,
	sumn(XTemp,YTemp),
	Y is X + YTemp.