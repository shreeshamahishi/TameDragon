
int foo(int a)
{
	int arr[3]; arr[0] = 1; arr[1] = 2; arr[2] = 3; 
	int i;
	int result;
	for(i = 0; i < a; i++)
	{
		result += arr[i];
	}
	
	return result;
}