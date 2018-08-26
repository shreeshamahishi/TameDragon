
union abc
{
	int i;
	double d;
	char *c;
};

union def
{
	int x[2][2];
	double d;
};

struct xyz
{
	float fl;
	short s;
	union abc mtr;
};

int main()
{
	union abc ua1, ua2 = 30;
	union abc ua3 = "is there anybody out there"; // NOT OK
	union abc something = {45};
	union abc another = {675.56};
	union abc yet_another = {"got it"};
	union abc ua4 = {20};
	union abc ua5 = {20, 43.2, "pink"};
	union def ud1 = {{{1, 2}, {3, 4}}}; 
	union def ud2 = {{1, 2}, {3, 4}};
	union def ud3 = {1, 2, 3, 4};
	union def ud4 = {1, 2, 3, 4, 5};
	union def ud5 = {3, 4};
	union def ud6 = 3.2;
	union def ud7 = {3.2};

	struct xyz x1 = {32.9, 8, ua4};

	struct xyz x2, x3 = 76.2; // NOT OK
	

	double dd;
	char *str;

	ua1 = 20;
	ua1 = 20.34;
	ua1 = "hello";

	dd = ua1.d;
	str = ua1.c;

	x3 = 20;                // NOT OK
}
