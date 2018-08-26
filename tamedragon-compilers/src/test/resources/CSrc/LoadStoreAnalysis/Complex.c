
int func(int m, int n, int threshold, int MAX)
{
	int a = 0, b = 0;
	
	if(m < threshold)
	{
		a = m;
		for(int i = 0; i < n; i++)
		{
			a++;
			if(a < MAX)
			{
				a += threshold -1;
				while(b < MAX)
				{
					a++;
					b++;
				}
			}
			else
			{
				a += 4;
			}
		}
	}
	else
	{
		a = 100;
	}
	
	a *= 2;
	return a;
}