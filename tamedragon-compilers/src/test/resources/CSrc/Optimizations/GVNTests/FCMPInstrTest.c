 float foo(float m){
float a=m,b=m;
float c,d,e,f;
c = a-5.5;
d = b-5.5 ;
e = c+a;
f = a+d;
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