
int Prog2() {
	
	int a, b, c[100], d, i;
	extern int *q;
	q = &a;
	a = 2;
	b = *q + 2;
	
	q = &b;
	for(i = 0; i < 100; i++) {
		c[i] = c[i] + a;
		*q = i;
	}
	
	d = *q + a;
	
	return d;	
}