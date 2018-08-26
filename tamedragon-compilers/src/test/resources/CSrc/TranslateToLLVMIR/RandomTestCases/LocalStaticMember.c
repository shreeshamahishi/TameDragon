 int i  ;
 int* a[10];
void foo(){
   static int j = 20;
   auto int k = 30;
   register int q = 40;
   i = 100;
   j = k + q;
   k = j * q;
   q = i / j;
}
