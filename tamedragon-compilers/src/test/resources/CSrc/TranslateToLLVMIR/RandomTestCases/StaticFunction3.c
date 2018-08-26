 int i ;
 int* a[10];
 static int j = 20;
static void foo(){
   auto int k = 30;
   register int q = 40;
   i = 100;
   k = i * q;
   q = i / k;
}
void moo(){
   foo();
}
