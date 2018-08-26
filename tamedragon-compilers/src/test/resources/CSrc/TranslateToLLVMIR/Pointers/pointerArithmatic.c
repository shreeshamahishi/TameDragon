int foo(int a){
  int b = 10;
  int* c = &a;
  int d = *(c + (a + b));
  return d;
}
