void boo(int * ptr);

int foo(int a, int b){
   int *ptr;
   ptr = &a;
   b = a + 5;
   boo(ptr);
   b = a + 5;
   return b;
}
