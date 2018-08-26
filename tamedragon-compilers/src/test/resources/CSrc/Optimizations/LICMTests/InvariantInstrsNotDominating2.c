
int foobar(int a, int b, int c)
{
	int i = 0, m, n = 3, k = 1;;
	int result;
	
	while(i < a)
	{
		if(i < b)
		{
			m = c;
			k += m;
		}
		else
		{
			result += n + c;
		}
		i++;
	}
	
	result += k + m;
	
	return result;
}