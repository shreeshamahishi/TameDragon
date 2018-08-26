
int foo(int m)
{
	int result = 100;
	int a = 40;
	if(m < a)
	{
		a = 200;
	}
	else
	{
		result = a;
	}
	
	result += a;
	return result;
}