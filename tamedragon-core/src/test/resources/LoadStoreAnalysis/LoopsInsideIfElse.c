
int func(int m, int threshold, int MAX)
{
	int a = 43;
	if(m < threshold)
	{
		a++;
		while(m < MAX)
		{
			a += 3;
		}
	}
	else
	{
		a--;
		while(m < threshold)
		{
			a += 2;
		}
	}
	
	a += 4;
	return a;
	
}