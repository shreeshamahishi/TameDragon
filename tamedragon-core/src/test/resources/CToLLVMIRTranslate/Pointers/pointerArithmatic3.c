int foo(int a){
  int b = 10;
  int* c = &a;
  int d = *(c + b + 2);
  return d;
}
