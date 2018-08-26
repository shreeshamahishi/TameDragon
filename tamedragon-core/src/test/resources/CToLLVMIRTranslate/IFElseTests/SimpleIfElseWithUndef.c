
int foo(int a, int b)
{
	int c, d = 32;
	if(a > b)
	{
		d += c;
	}
	else
	{
		d += b;
	}
	
	d += a + b + c;
	
	return d;
}