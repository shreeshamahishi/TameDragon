
int foo(int m)
{
	int a, b, c = 40, x = 12, y = 23;
	int result;
	
	for(a = 3; a < m; a++)
	{
		result += a;
		while(x != 0)
		{
			b += result;
			for(b = 1; b < m; b++)
			{
				y += c;
			}
			result += y;
		}
	}
	
	return result;
}