
#define _COND_TEST_1 100

int main()
{
	int result1 = 0, result2 = 3, 
		result3 = 0, result4;
	
	#ifdef _COND_TEST_2
	int i = 23;
	#elif 2 -2
	int j = 12;
	#elif 23 -23
	int m = 87;
	#elif 42
	int n = 3;
	#else
	int k = 32;
	#endif
	
	result1 = i + j + 46;
	result2 = j * _COND_TEST_1;
	result3 = n + 31;
	result4 = m * 3 + k;
	
	return 0;
}