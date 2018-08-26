
int bar(int *a, int b)
{
	int m = 10, n = 30, p;
	
	*a = m;
	p = *a * m;
	n += p;
	
	return b;
	
}