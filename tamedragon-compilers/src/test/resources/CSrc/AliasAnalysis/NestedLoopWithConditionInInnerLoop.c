

int foo(int arr[], int max, int m, int split, int x, int y)
{
	int a = 10, b = 20, c = 40, count = 0, inner_count = 0;
	
	int *ptr;
	ptr = arr;
	
	int result = 0;
	while(count < max)
	{
		ptr++;
		a = b + c + *ptr;
		ptr += m;
		
		while(inner_count < x)
		{
			ptr += 3;
			a = *ptr + 3;
			if(inner_count < y)
			{
				ptr += 2;
			}
			
			inner_count++;
		}
		
		if(count < split)
		{
			ptr += 2;
		}

		count++;
		result += *ptr + a + b + c;
	}
	
	return result;
}