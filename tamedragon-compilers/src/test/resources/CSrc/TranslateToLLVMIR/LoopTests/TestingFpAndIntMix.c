
double foo(int a, double passed_dbl)
{
	int b = 12, c = 23;
	double d;

	d = 2 + c;

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
		b = b - 1;
	}

	d = d + 3.4;
	return d;

}
