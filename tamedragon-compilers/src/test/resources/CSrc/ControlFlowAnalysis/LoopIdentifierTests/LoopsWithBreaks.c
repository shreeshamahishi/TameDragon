
int foo(int a, int b, int n)
{
	int m;
	int result;
	
	for(m = 0; m < a; a++)
	{
		result += b;
		if(m > b)
		{
			result += 1;
			break;
		}
		else
		{
			result += n;
		}
	}
	
	return result;
}