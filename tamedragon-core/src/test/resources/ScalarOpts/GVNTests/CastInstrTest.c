int foo(int m){
 float i= m+3.333;
 float j= m+3.333;
 int n = (int)i;
 int p = (int)j;
 if(m<5)
  return n;
 else
  return p;
}