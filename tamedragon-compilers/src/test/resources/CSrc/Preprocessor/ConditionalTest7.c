
#define _COND_TEST_1 100

int main()
{
	int result1 = 0, result2 = 3;
	
	#ifdef _COND_TEST_2
	int i = 23;
	#elif 1
	int j = 12;
	#else
	int k = 32;
	#endif
	
	result1 = i + j + 46;
	result2 = i + k + _COND_TEST_1;
	
	return 0;
}