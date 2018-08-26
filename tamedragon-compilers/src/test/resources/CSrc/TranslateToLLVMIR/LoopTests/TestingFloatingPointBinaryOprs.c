int foo(int a, int b)
{
	int c = 23;
	double d = 10.0, passed_dbl = 20.0;

	c = 2 + c;

	while(b < a)
	{
		d = d + passed_dbl;
		if(c < b)
		{
			d = d - passed_dbl;
			c = c + 1;
		}
		else
		{
			d = d + passed_dbl;
		}
		c = b - 1;
	}

	d = d + 3.4;
	return c;

}
