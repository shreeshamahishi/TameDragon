int bar(int a)
{
	int m = 10, n = 30, p, *ptr;
	
	ptr = &m;
	p = a * m;
	n += p;
	
	return a;
	
}