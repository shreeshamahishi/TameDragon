int a = 10;
int foo(int flag){
  int x = a+1;
 // a++;
  int y = a+1;

  if(flag)
    return x;
  else
    return y;
}