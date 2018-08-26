
int foobar(int a, int b)
{
	int m, n;
	int k = 78;
	int result;
	
	for(m = 0; m < a; m++)
	{
		n = b;  // Must be removed in licm
		result += n + k;
	}
	
	return result;
}