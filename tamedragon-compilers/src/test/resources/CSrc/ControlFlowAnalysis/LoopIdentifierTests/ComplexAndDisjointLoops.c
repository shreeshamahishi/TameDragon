
int foobar(int a, int b, int c)
{
	int d, e, f;
	int i, j, k = 0;
	
	for(i = 0; i < a; i++)
	{
		if(b < c)
		{
			d += b + c;
		}
		else
		{
			d -= b;
		}
		
		d += 21;
		while(d >= 0)
		{
			e += c;
			d--;
		}
		
		if(e > 20)
		{
			f = 32;
		}
		else
		{
			f = 64;
		}
	}
	
	a += 10;
	b += 20;
	c += 30;
	
	while(c > 0)
	{
		f += 1;
		if(f < 40)
		{
			k = 100;
		}
		else
		{
			k = 200;
		}
		for(j = 0; j < 200; j++)
		{
			a += 21;
			for(k = 0; k < a; k++)
			{
				f += 1;
			}
		}
		c--;
	}
	
	return f;
}