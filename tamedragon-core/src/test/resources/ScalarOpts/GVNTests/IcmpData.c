 int foo(int m){
int a=m,b=m,c,d,e,f;
c = a-5;
d = b-5 ;
e = c+a;
f = d+a;
if(c<d)
return c;
else if(d==c)
return d;
else if(e != f)
return e;
else if(e>= f)
return d;
return a;
}