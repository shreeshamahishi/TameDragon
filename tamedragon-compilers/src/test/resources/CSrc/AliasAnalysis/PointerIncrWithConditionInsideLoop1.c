
int foo(int arr[], int max, int m, int split)
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
		if(count < split)
		{
			ptr += 2;
		}

		count++;
		result += *ptr + a + b + c;
	}
	
	return result;
}