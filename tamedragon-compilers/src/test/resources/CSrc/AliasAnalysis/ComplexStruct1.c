
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

typedef struct PersonAttr person;

int foo(person* allPersons, int m, int n)
{
	char* name1, name2;
	
	int hn1 = allPersons[0].residenceContactDtls.addr.houseNum;
	int hn2 = allPersons[0].residenceContactDtls.addr.houseNum;
	
	int hn3 = allPersons[1].residenceContactDtls.addr.houseNum;
	
	if(hn1 == hn3){
		name1 = allPersons[0].firstName;
	}
	if(hn1 == hn2){
		name2 = allPersons[0].firstName;
	}
	
	extern person refPerson;
	refPerson.residenceContactDtls.addr.houseNum = hn1 + hn2;
	
	return 0;
} 
