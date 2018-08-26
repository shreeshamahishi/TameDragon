
void main()
{
	// Test the validity of the unary negation operator(!)

	int m1, m2;
	double d1, d2;
	char c1, c2;
	float fl1, fl2 = 0.9f;
	short s1, s2 = 9;
	long ln1, ln2 = 9;

	char *pi = "hello", *p2;
	struct abc {
		int i;
		double d;
		char *next;
	} a1 , a2 = {1, .32, "world"};
	int iarr[2];
	double darr[3];

	m1 = !m1;
	d1 = !d2;
	c1 = !c2;
	fl1 = !fl2;
	s1 = !s2;
	ln1 = !ln2;
	c1 = !*p2;
	ln1 = !"Hello";

	p2 = !pi;
	a1 = !a2;
	m2 = !iarr;
	d1 = !darr;
	m2 = ! 23;	
	m2 = ! &m1;
	
	// Test the validity of the one's complement (applicable only to integers)
	m1 = ~m2;
	m1 = ~c1;
	m1 = ~s1;
	m1 = ~ln1;
	m1 = ~23;
	
	m1 = ~d1;
	m1 = ~fl1;
	m1 = ~p2;
	m1 = ~a1;
	m1 = ~iarr;
	m1 = ~darr;
	m1 = ~23;
	m1 = ~&m1;
	c1 = ~*pi;
	ln1 = ~"Hello";

	~m1 = 32;
}