
void main()
{
	struct abc {
		int i;
		double d;
		char* pstr;
	};
	
	struct abc astr;
	
	int i = 10, j, k;
	double d = 21.3, dd;
	short s1 = 2, s2 = 4;
	long ln1 = 3, ln2 = 678;
	float flt1 = 0.4f, flt2 = 5.6f;
	int *pi, *pj, *pk;
	double *pd;
	char *pstr = "we";
	char cr1, cr2, *pc;
	int iarr[2];
	double darr[3];
	struct abc *pastr;
	
	astr.i = 10;
	astr.d = 45.6;
	astr.pstr = "hello";
	
	j = -i;        // OK -Negative of a integer
	s1 = -s2;      // OK - Negative of a short
	ln1 = -ln2;    // OK - Negative of a long
	flt1 = -flt2;  // OK - Negative of a float
	dd = -d;       // OK - Negative of a double
	cr2 = -cr1;      // OK - Negative of a char
	k = -i * j;
	k = -(i *j);
	
	pj = -&i;      // Negative of an integral address - not OK?
	pd = -&d;      // Negative of an double address - not OK?
	pd = -darr;    // Negative of a int array
	pc = -pstr;    // Negative of a pointer
	pj = -iarr;    // Negative of a int array
	pastr = -astr; // Negative of a struct object
	
}