
void foo(int m, int *result)
{
	int a, b, c = 40, x = 12, y = 23;
	
	for(a = 3; a < m; a++)
	{
		*result += a;
		while(x != 0)
		{
			b += *result;
			for(b = 1; b < m; b++)
			{
				y += c;
			}
			*result += y;
		}
	}
}
