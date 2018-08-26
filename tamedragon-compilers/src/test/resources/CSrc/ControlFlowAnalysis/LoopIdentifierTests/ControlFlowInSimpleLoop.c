
int foo(int a, int b, int c)
{
	int i = 12, j = 14, k = 16;
	
	while(i > 0)
	{
		if(a > b)
		{
			j += k;
		}
		else
		{
			k += c; 
			j -= k;
			if(k == a + b)
			{
				a = i + b;		
			}
		}
		i--;
	}	
	
	return a + b + c;
}