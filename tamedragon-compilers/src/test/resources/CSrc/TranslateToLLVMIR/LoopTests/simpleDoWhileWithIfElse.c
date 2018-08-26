int foo(int a, int b)
{
	int c = 23;
	double d;

	c = 2 + c;

	do{
		if(c < b)
		{
			c = c + 1;
		}
		else
		{
		}
		c = b - 1;
	}while(b < a);

	return b;

}
