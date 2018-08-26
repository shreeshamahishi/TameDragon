int foo(int a, int b){
   int arr[3][3];
   int* d = &arr[0][0];
   return *(d + 2);
}
