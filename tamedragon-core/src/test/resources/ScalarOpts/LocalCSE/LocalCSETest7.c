int foo(int a, int b, int *ptr){
   ptr = &a;
   b = a +4;
   *ptr = *ptr + 4;
   b = a + 4;	
   return b;
}