int foo(int a, int b){
  int* c = &a;
  int d = *++c;
  return d;
}
