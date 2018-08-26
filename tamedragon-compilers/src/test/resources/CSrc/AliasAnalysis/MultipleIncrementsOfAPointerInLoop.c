
int foo(int arr[], int max, int m, int n)
{
	int a = 10, b = 20, c = 40, count = 0;
	
	int *ptr;
	ptr = arr;
	
	int result = 0;
	while(count < max)
	{
		ptr++;
		a = b + c + *ptr;
		ptr += m;
		b += 2 + *ptr;
		ptr += n;
		c += 3;
		
		result += *ptr + a + b + c;
	}
	
	return result;
}