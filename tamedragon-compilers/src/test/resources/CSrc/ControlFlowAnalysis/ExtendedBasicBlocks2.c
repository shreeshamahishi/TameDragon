

int bar(int a, int b, int c)
{
	int d = 100, e, f, x;
	
	for(e = 0; e < a + b; e++)
	{
		if(e < d)
		{
			c += 2;
		}
		else
		{
			c -= 2;
		}
		x = 30;
		while(x > 0){
			c += 1;
			x--;
		}
	}
	f = 100;
	if(c > 40)
	{
		for(e = 20; e > 0; e--)
		{
			f += a + e;
		}
	}
	else
	{
		f = 10;
	}
	
	return f;
}
