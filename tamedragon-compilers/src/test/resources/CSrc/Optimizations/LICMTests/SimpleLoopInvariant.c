
int foo(int a)
{
	int m, n;
	int k = 21;
	int result;
	
	for(m = 0; m < a; m++)
	{
		n = 81;  // Must be removed in licm
		result += n + k;
	}
	
	return result;
}