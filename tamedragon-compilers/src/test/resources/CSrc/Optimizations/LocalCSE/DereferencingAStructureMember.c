struct student {
  int id;
  double percentage;
};

int foo(int a, int b){
  struct student *st;
  (*st).id = 1;
  (*st).percentage = 95.4;
  return 0;
}
