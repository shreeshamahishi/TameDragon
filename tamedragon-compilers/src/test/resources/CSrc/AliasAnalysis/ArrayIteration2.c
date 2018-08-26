
int foo(int MAX, int *arr)
{
	int *ptr, *p1, *p2, *p3;
	
	p1 = arr; p2 = arr; p3 = arr;
	
	int count = 0, result = 3;
	while(count < MAX)
	{
		p1++; p2++; p3 += 2;
		count++;
		
		result += *p1 + *p2 + *p3;
	}
	
	if(*p1 < 20)
	{
		result += 1;
	}
	
	if(*p2 < 30)
	{
		result += 2;
	}
	
	if(*p3 < 40)
	{
		result += 3;
	}
	
	return result;
}