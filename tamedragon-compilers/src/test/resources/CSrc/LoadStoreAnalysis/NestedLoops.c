
int foo(int m, int n, int MAX1, int MAX2)
{
	int a = 12, result;
	
	for(int i = 0; i < MAX1; i++)
	{
		a++;
		for(int j = 0; i < MAX2; i++)
		{
			a += 3;
		}
		
		a *= n;
	}
	
	return a++;
	
}