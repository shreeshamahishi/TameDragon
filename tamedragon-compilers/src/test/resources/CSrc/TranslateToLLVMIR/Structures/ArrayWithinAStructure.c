struct student {
  int id;
  double marks[3];
};

int foo(int a, int b){
  struct student *st;
  st->id = 1;
  st->marks[0] = 97.6;
  return 0;
}
