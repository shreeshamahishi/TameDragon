
int bar(int m, int n, int threshold, int MAX)
{
	int a = 21, b = 0;
	
	while(m < MAX)
	{
		a++;
		if(m < threshold)
		{
			a += 3;
			b = a + m;
		}
		
		m++;
		b += a;
	}
	
	return a + b;
}