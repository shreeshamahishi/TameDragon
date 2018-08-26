
void foo(int* ptr);

int bar(int max)
{
	int a = max, k, *p;
	
	extern int gbl;
	gbl = 43;
	
	p = &a;
	
	k = a + 5;
	foo(p);
	k = a + 5;
	
	return k;
}