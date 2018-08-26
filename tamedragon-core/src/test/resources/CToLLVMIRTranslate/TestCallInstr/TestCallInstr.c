int boo(int b);

 int foo(int a, int b){
    boo(a);
    return b;
 }

 int boo(int a){
    int x = a;
    return x;
 }
