
int foo(int *pi)
{
	int s;
	s = *pi;
	
	return s;
}

void bar(int *d)
{
	int *r, s = 20, *ps;
	r = &s;
	
	ps = d++;
	s = *r++;
	
	return s;
}

void main()
{
	int i = 20, j = 30, m;
	int *ip;
	double d;

	i = m;         // OK
	j = (int) d;   // OK
	bar(&j) = m;

	m = i + j;
	ip = &m;

	d = bar(&m);       // cannot assign void to anything

	// suspicious or faulty left side assignment
	        
	//(m > 40 ? j : i) = 45;
	(m == 45 || i == 20) = m + 45;
	(m > 45 && 78) = i + 8;
	(m | 32) = i;    // Inclusive or
	(i ^ 2) = m;     // Exclusive or
	(j & 2) = 34;    // AND 
	(m == i) = j;    // Equality
	(i != j) = m;    // Equality
	(m > 25) = i;    // Relational
	(j < 25) = m;    // Relational
	(i >= 102) = j;    // Relational
	(j <= 256) = m;    // Relational
	(m >> i +2) = 76;  // left shift
	(j << 21) = 43;    // right shift
	(i + 3) = m;       // additive
	(987 * m) = i;     // multiplicative 
	(987 / m) = i;     // multiplicative (divide)
	(i % m) = i;     // multiplicative (modulo)
	((int) d) = m;       // cast
	++m = i;           // increment
	--j = m;           // increment
	&m = &i;           // address of
	*ip = 3456;        // pointer
	+j = 32;           // plus
	-i = 56;           // minus
	~m = 567;          // tilde
	!j = m +0;         // not
	sizeof(43) = m;    // sizeof 
	sizeof 21 = i;     // sizeof variant
	i++ = 45;          // increment
	m-- = 90;          // decrement
	foo(&j) = i + m;   // function call
	i = 9 + 8;         // ID, should be ok
	2 = m;             // constant	
	"xyz" = 653;       // string literal
	(m)   = 45;        // OK
	(m *7) = 98;       // NOT OK

	j = foo(&i);   
	
	// SHOULD BE FINE
	
	//i = m > 40 ? j : i;
	m = m == 45 || i == 20;
	i = m > 45 && 78;
	i =m | 32;    // Inclusive or
	m = i ^ 2;     // Exclusive or
	m = j & 2;    // AND 
	j = m == i;    // Equality
	m = i != j;    // Equality
	i = m > 25;    // Relational
	m = j < 25;    // Relational
	j = i >= 102;    // Relational
	m = j <= 256;    // Relational
	j = m >> i +2;  // left shift
	i = j << 212;    // right shift
	m = i + 3;       // additive
	i = 987 * m;     // multiplicative 
	i = 987 / m;     // multiplicative (divide)
	i = i % m;     // multiplicative (modulo)
	m = (int) d;       // cast
	i = ++m;           // increment
	m = --j;           // increment
	*ip = 3456;        // pointer
	m = +j;           // plus
	j = -i;           // minus
	d = ~m;          // tilde
	m = !j;         // not
	m = sizeof(43);    // sizeof 
	i = sizeof 21;     // sizeof variant
	m = i++;          // increment
	j = m-- ;          // decrement
	i = foo(&j);   // function call
	i = 9 + 8;         // ID, should be ok
	m = 2;             // constant	
	(m)   = 45;        // OK
	i = (m +7) * 98;       // NOT OK
	
}