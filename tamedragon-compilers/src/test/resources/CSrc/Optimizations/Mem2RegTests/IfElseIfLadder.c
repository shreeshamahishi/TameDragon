int foo(int m){
   int a=10,b=10,c,d,e,f;
   
   c = a-5;
   d = b-5 ;
   e = c+a;
   f = a+d;
   if(c<d)
       return c;
   else if(d==c)
      return d;
   else if(a != b)
     return e;
   else if(a>= b)
     return d;

   return a;
}
