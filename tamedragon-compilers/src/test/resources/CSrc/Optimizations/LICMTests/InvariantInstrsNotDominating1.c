
int foobar(int a, int b, int c)
{
	int m, n, p;
	int k = 78;
	int result;
	
	for(m = 0; m < a; m++)
	{
		if(m < c)
		{
			n = b;  
			result += n + k;
		}
		else
		{
			result += n + c;
		}
		
		p = c + n; // Must be removed in licm
	}
	
	return result;
}