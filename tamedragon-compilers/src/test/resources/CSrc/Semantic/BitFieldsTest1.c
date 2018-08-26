
double mm;
int i;

struct st{
	int m;
	double d;
};
// Try with various options for bitfield members
struct abc {
	unsigned int is_float : 1;
	unsigned int is_double : 1;
	unsigned int i2 : i;

	double d : 2;
	unsigned int xyz: 1.2;

	char* pstr : 1;
	
	struct st st1 : 3;
};

// Combination of bitfield and other members
struct abc1 {
	unsigned int is_float : 1;
	unsigned int is_double : 1;

	char * pstr;
	double d1;
};

// Combination of bitfield and other members (other members first)
struct abc2 {
	char * pstr;
	double d1;

	unsigned int is_float : 1;
	unsigned int is_double : 1;

};

// Try with unions
union uab{
	int i;

	unsigned int m1 : 1;
	unsigned int m2 : 2;

};

// Try with unions (other members first)
union another_uab {
	unsigned int m1 : 1;
	unsigned int m2 : 2;

	int i;
};

void main()
{
	struct abc a1;
	union uab u1;

	a1.is_float = 0;
	a1.is_double = 23.4;
	a1.d = 34.2;
	u1.i = 10;
	u1.m1 = 30;
}