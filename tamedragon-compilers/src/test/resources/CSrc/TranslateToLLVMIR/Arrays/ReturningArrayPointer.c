int foo(int a, int b){
   float arr[3];
   int i;
   for(i = 0; i < 3; i++){
       arr[i] = i;
   }
   return *arr;
}
