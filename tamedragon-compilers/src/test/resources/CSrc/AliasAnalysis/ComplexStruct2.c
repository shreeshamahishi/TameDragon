
struct addressStruct{
	int houseNum;
	char* streetName;
	char* city;
	char* stateOrProvince;
	char* zipCode;
	char* country;
};

typedef struct addressStruct address;

struct contactDetails{
	address addr;
	char* phoneNum;
};

typedef struct contactDetails Contact;

struct PersonAttr{
	char* firstName;
	char* lastName;
	double heightInFeetAndInches;

	Contact residenceContactDtls;
	Contact officeContactDtls;
	
	long salary;
};

int m, n;

typedef struct PersonAttr person;

int foo(person* allPersons)
{
	char* name1, name2;
	char* lname1, lname2;
	
	int hn1 = allPersons[0].residenceContactDtls.addr.houseNum;
	int hn2 = allPersons[1].residenceContactDtls.addr.houseNum;
	
	int hn3 = allPersons[2].residenceContactDtls.addr.houseNum;
	
	if(hn1 == hn3){
		name1 = allPersons[0].firstName;
	}
	if(hn1 == hn2){
		name2 = allPersons[0].firstName;
	}
	
	int hn4 = allPersons[m].residenceContactDtls.addr.houseNum;
	int hn5 = allPersons[m].residenceContactDtls.addr.houseNum;
	int hn6 = allPersons[n].residenceContactDtls.addr.houseNum;
	
	if(hn4 == hn5){
		lame1 = allPersons[m].lastName;
	}
	
	if(hn4 == hn6){
		lame1 = allPersons[n].lastName;
	}
	
	
	return 0;
} 
