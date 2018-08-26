
??= define MAX_COUNT 3
int main()
{
	int arr1??(MAX_COUNT??) = {1, 2, 3};
	int arr2[2] = ??< 2, 3 ??>;
	int m = 23, n = 2, result1, result2, result3;
	char * str = "This ??> or ??! should not get replaced, but it does";
	result1 = m ??' n;
	result2 = m ??! n;
	result3 = m ??- n; 
}