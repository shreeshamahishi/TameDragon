int  foo (int m){
int a, b, c;
int *p, *q, *r;
 a = m+10;
 b = m+10;
 p = &a;
 q = &b;
 
  if(m)
  r = p;
  else
  r = q;
  
  c = *r;
  return c;
}