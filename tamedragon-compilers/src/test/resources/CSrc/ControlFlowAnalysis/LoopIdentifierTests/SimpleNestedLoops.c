
int foo(int a, int b)
{
	int m, n, x, y;
	for(m = 0; m < a; m++)
	{
		x += m;
		for(n = 0; n < b; n++)
		{
			y += x;
		}
	}	
	
	return x + y;
}