
int foo(int a, int b)
{
	int d = 12, c = 23;
	c = 2 + c;
	
	while(b < a)
	{
		if(c < b)
		{
			c = c + 1;
		}
		else
		{
		}
		d = d - 1;
	}
	
	return d;
	
}
