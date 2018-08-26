
void main()
{
	int m1, m2, m3;
	double d1, d2, d3;
	short s1, s2, s3;
	char c1, c2, c3;
	float fl1, fl2, fl3;
	long ln1, ln2, ln3;
	
	int iar[20];
	double dar[2];
	char *pstr = "Hello";
	struct xyz {
		int i;
		double d;
		char *pc;
	} x1, x2;
	
	// Increment
	++m1;
	++d1;
	++s1;
	++fl1;
	++ln1;
	
	++iar;
	++dar;
	++pstr;
	++x1;
	++(&m1);
	++23;
	++23.5;
	++"World";
	++ (m1*m2);
	
	// Decrement
	--m1;
	--d1;
	--s1;
	--fl1;
	--ln1;
	
	--iar;
	--dar;
	--pstr;
	--x1;
	--(&m1);
	--23;
	--23.5;
	--"World";
	-- (m1*m2);	
}