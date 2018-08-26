
int glb1, glb2;
int *ptrglb1, *ptrglb2;

struct QuestionStruct{
	int marks;
	char* question;
	char* options[4];
	int negativeMark;
} ;

typedef struct QuestionStruct Question;

struct SectionStruct{
	char* sectionName;
	Question questions[20];
	int totalMarks;
} ;

typedef struct SectionStruct Section;

struct TestStruct{
	char* testName;
	Section sections[3];
	int duration;
};

typedef struct TestStruct Test;

Test glblTest1, glblTest2;

int foo(Test *argTest1, Test *argTest2, int m1, int n1, int x1, int y1)
{
	Test localTest1;
	Test localTest2;
	
	Test localTest3;
	localTest3.testName = "GK";
	localTest3.duration = 60;
	localTest3.sections[0].sectionName = "Geography";
	localTest3.sections[0].totalMarks = 10;
	localTest3.sections[0].questions[0].marks = 1;
	localTest3.sections[0].questions[0].question = "What is the capital of India";
	localTest3.sections[0].questions[0].options[0] = "Delhi";
	localTest3.sections[0].questions[0].options[1] = "Bangalore";
	
	localTest1.sections[0].questions[0].options[2] = "Bangalore";
	
	int localTestMarks1 = localTest1.sections[m1].questions[n1].marks 
						+ localTest2.sections[m1].questions[n1].marks
						+ localTest3.sections[m1].questions[n1].marks;
						
	int localTestMarks2 = localTest1.sections[x1].questions[y1].marks 
						+ localTest2.sections[x1].questions[y1].marks
						+ localTest3.sections[x1].questions[y1].marks;
	
	
	char* localTest1Opt1 = localTest1.sections[0].questions[0].options[1];
	char* localTest1Opt2 = localTest1.sections[0].questions[0].options[2];
	char* localTest2Opt1 = localTest2.sections[0].questions[0].options[1];
	char* localTest2Opt2 = localTest2.sections[0].questions[0].options[2];
	
	char* localTest3Opt1 = localTest3.sections[0].questions[0].options[1];
	
	char* arg1Opt1 = argTest1[0].sections[0].questions[0].options[1];
	char* arg1Opt2 = argTest1[0].sections[0].questions[0].options[2];
	
	char* arg2Opt1 = argTest2[0].sections[0].questions[0].options[1];
	char* arg2Opt2 = argTest2[0].sections[0].questions[0].options[2];
	
	char* glblTest1Opt1 = glblTest1.sections[0].questions[0].options[1];
	char* glblTest1Opt2 = glblTest1.sections[0].questions[0].options[2];
	
	char* glblTest2Opt1 = glblTest2.sections[0].questions[0].options[1];
	char* glblTest2Opt2 = glblTest2.sections[0].questions[0].options[2];
	
	int result = 0;
	if(localTest1Opt1 == 0)
	{
		result = glb1 + glb2 + *ptrglb1 + *ptrglb2;
	}
	if(localTest1Opt2 == 0)
	{
		result = 20;
	}
	if(localTest2Opt1 == 0)
	{
		result = 30;
	}
	if(localTest2Opt2 == 0)
	{
		result = 40;
	}
	if(arg1Opt1 == 0)
	{
		result = 50;
	}
	if(arg1Opt2 == 0)
	{
		result = 60;
	}
	if(arg2Opt1 == 0)
	{
		result = 70;
	}
	if(arg2Opt2 == 0)
	{
		result = 80;
	}
	if(glblTest1Opt1 == 0)
	{
		result = 90;
	}
	if(glblTest1Opt2 == 0)
	{
		result = 100;
	}
	if(glblTest2Opt1 == 0)
	{
		result = 110;
	}
	if(glblTest2Opt2 == 0)
	{
		result = 120;
	}
	if(localTest3Opt1 == 0)
	{
		result = 320;
	}
	
	return result + localTestMarks1 + localTestMarks2;
}