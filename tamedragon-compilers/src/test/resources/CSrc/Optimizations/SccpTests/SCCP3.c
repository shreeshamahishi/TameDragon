
int bar(int a, int b)
{
	int m = 1, n = 2;
	int d = 1;
	
	if(m > n)
	{
		if(a + b)
		{
			d += 10;
		}
	}
	else
	{
		if(a + n > b + m)
		{
			d = 3;
		}
		else
		{
			d = 4;
		}
	}
	return d;
}
