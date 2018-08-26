
int foo(int *arg1, int MAX)
{
	int *ptr, count = 0, result = 0;

	ptr = arg1;
	while(count < MAX)
	{
		result += *ptr;
		ptr++;
		count++;
	}
	
	return result;
}