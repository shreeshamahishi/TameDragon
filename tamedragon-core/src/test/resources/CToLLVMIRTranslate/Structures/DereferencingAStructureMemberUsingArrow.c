struct student {
  int id;
  float percentage;
};

int foo(int a, int b){
  struct student *st;
  st->id = 1;
  st->percentage = 95.4f;
  return 0;
}
