
int foo(int a, int b)
{
	int c = 3, d = 6, e;
	
	if(a + c > b + d)
	{
		d = 21 * a;
		a = c + 4;
		b = d + 3;
		c = a + 4;
		d = a + b + c;
	}
	else
	{
		d = 68 * b;
		a = 45 + b;
		d += a + c; 
	}
	
	e = d + a;
	return d;
}