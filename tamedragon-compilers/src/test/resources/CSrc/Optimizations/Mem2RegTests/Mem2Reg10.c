
int foo(int a, int b)
{
	int c, d;
	
	// c = a > 20 &&  b < 10;
	c = a  || b;
	
	d = c * 20;
	
	c += 10;
	if(c > 100)
	{
		d = 1;
	}
	else
	{
		d = 2;
	}
	
	return d;

}

