%%%% 
:-op(450,yfx,and).
:-op(500,yfx,or).
:-op(400,fy,--).
:-op(600,xfx,==>).
:-op(500,yfx,xor).
:-op(500,yfx,nor).
:-op(450,yfx,nand).

Arg1 and Arg2 :- Arg1, Arg2.
Arg1 or _Arg2 :- Arg1.
_Arg1 or Arg2 :- Arg2.

--Arg :- not(Arg).

Arg1 ==> Arg2 :- --Arg1 or Arg2.

Arg1 xor Arg2 :- Arg1, --Arg2.
Arg1 xor Arg2 :- Arg2, --Arg1.

Arg1 nor Arg2 :- --(Arg1 or Arg2).

Arg1 nand Arg2 :- --(Arg1 and Arg2).

t. 
f:-!,fail.