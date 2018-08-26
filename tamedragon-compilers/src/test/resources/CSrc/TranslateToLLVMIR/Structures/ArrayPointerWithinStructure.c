struct student {
  int id;
  double percentage;
  double *marks[2];
};

int foo(int a, int b){
  int i;
  struct student st;
  double practMarks[2];
  double theoryMarks[2];
  double totalPracticalMarks;
  double totalTheoryMarks;
  st.id = 1;

  for(i = 0; i < 2; i++){
	  practMarks[i] = 50.0;
	  theoryMarks[i] = 50.0;
  }

  st.marks[0] = practMarks;
  st.marks[1] = theoryMarks;

  totalPracticalMarks = st.marks[0][0] + st.marks[0][1];
  totalTheoryMarks = st.marks[1][0] + st.marks[1][1];

  st.percentage = (totalPracticalMarks + totalTheoryMarks)/4.0;

  return 0;
}
