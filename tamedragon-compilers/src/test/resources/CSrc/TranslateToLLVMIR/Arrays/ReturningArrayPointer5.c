int foo(int a, int b){
   int c = 10;
   int arr[3][3];
   arr[0][0] = a;
   return *(arr + a + b + c);
}
