struct subject {
  double practical;
  double theory;
};

struct student {
  int id;
  double percentage;
  struct subject subjects[2];
};

int foo(int a, int b){
  int i;
  struct student st;
  st.id = 1;
  st.percentage = 90.5;

  for(i = 0; i < 2; i++){
	  st.subjects[i].practical = 50.0;
	  st.subjects[i].theory = 60.0;
  }

  return 0;
}
