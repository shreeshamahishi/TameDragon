

double foo(int a, double passed_dbl)
{
	int b = 12, c = 23;
	double d;
	
	c += a;
	
	while(b < a)
	{
		d += passed_dbl;
		if(c < b)
		{
			d -= passed_dbl;
			c++;
		}
		else
		{
			d += passed_dbl;
		}
		b--;
	}
	
	d += c;
	return d;
	
}