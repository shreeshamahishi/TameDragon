union un{
  struct s{
    int a;
    float f;
    double d;
  }st;
  int intArr[10];
}uno;

int main(){
   uno.st.a = 10;
}