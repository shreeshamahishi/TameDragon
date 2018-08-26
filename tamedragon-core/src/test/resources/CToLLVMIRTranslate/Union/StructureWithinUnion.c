union techno
{
	int serial_no;
	struct s{
	  char* name;
	  char* rollno;
	  double total_marks;
	  double percentage;
	} student;
}tch;


int main()
{
    tch.serial_no = 1;
    tch.student.name = "Vikash Joshi";
    tch.student.rollno = "0502270017";
    tch.student.total_marks = 0.000562;
    tch.student.percentage = 76.2;
    return 0;
}
