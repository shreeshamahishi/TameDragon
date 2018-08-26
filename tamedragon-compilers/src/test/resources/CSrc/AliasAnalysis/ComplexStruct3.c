
struct PersonStruct {
	char* fname;
	char* lname;
	int age;
	double height;
};

typedef struct PersonStruct Person;

struct CompanyStruct {
	char *name;
	char* streetAddress;
	Person *persons;
};

typedef struct CompanyStruct Company;

unsigned foo(int compIndex1, int personIndex1, int compIndex2, int personIndex2, Company* companies)
{
	unsigned int elder = 0;
	
	if(companies[compIndex1].persons[personIndex1].age > 
		companies[compIndex2].persons[personIndex2].age){
		elder = 1;	
	}
	else{
		elder = 0;
	}
	
	return elder;
}