
int foo(int *pi)
{
	int r, s;
	r = pi;
	s = *pi;
	
	return pi;
}

int bar(int *d)
{
	int *r, s = 20, *ps;
	r = &s;
	
	ps = d++;
	s = *r++;
	
	return s;
	
}

void main()
{
	int i, j, m;
	int *pi, *pj;
	
	char c = 'm';
	short s = 3;
	long l = 45;
	int arr[20];
	double d = 34.5;
	float f = 123.4f;
	
	m = foo(&j);
	j = bar(&m);
	pi = arr;
	pj = pi;
	
	c = pj;
	s = pj;
	j = pj;
	l = pj;
	d = pj;
	f = pj;
	
	pi = c;
	pi = s;
	pi = i;
	pi = d;
	pi = f;
	
	arr = m;         // Cannot assign to a constant
	pj = &23;        // Cannot take address of a constant
	pj = &arr;        // Cannot take address of a constant
	pj = &(2 +4);    // Cannot take address of an expression
	pi = &(m + 8);   // Cannot take address of an expression
	m = *&j;         // Should be fine
	j = *(&i);       // Should be fine

	m = *i;          // Trying to get the value pointed to by a non-pointer
	i = *3;			 // Trying to get the value pointed to by a constant
	i = *(3 + 8);    // Trying to get the value pointed to by an expression
	i = *(m + j);    // Trying to get the value pointed to by an expression
	i = *pj;		 // Should be fine

	
}