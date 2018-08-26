struct QuestionStruct{
int marks;
char* question;
char*  options[4];
int negativeMark;
} Question;

struct SectionStruct{
char* sectionName;
struct QuestionStruct questions[20];
int totalMarks;
} ;

typedef struct SectionStruct Section;

struct TestStruct{
char* testName;
Section sections[3];
int duration;
};

typedef struct TestStruct Test;

int foo(Test* tests, int m, int n, int x, int y, int opIndex)
{
int marks1 = tests[0].sections[m].questions[n].marks;
int marks2 = tests[1].sections[x].questions[y].marks;
char* option = tests[0].sections[1].questions[2].options[opIndex];
int result = marks1 + marks2 + option;
return result;
} 
