
int bar(int m, int threshold)
{
	extern int MAX;
	
	int a = 23, b, result = 0;
	
	if(m < threshold)
	{
		a += 46;
		for(b = 0; b < MAX; b++)
		{
			a += 2;
		}
	}
	else
	{
		a = threshold;
	}
	
	result += a;
	
	return result;
}