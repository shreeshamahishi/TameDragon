
int foo(int a, int b)
{
	int c = 43, d, m;
	if(a + c > b)
	{
		d = b;
	}
	else
	{
		d = a;
	}
	d += a + b;
	
	for(m = 0; m < a + b; m++)
	{
		d -= m;
		if(d < a)
		{
			c += d;
		}
		else
		{
			c -= d;
		}
	}
	
	return c;
}
