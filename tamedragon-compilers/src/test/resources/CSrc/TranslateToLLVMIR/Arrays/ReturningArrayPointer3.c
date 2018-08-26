int foo(int a, int b){
   float arr[3][3];
   int i;
   for(i = 0; i < 3; i++){
       arr[0][i] = i;
   }
   return *(arr + 1);
}
