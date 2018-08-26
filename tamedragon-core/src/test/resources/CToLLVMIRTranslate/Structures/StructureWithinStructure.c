struct subject {
  int practical;
  int theory;
} phy, chem;

struct student {
  int id;
  float percentage;
  struct subject compSc;
} student1, student2;

int foo(int a, int b){
  struct student st;
  student1.id=1;
  student2.percentage = 90.5f;
  student2.compSc.practical = 100;
  student2.compSc.theory = 99;
  phy.practical = 80;
  chem.theory = 98;

  a = student1.id;

  return 0;
}
