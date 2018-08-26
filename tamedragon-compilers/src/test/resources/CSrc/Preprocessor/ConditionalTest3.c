
#define _COND_TEST_1 100

int main()
{
	int result = 0;
	#ifdef _COND_TEST_1
	int i = 23;
	#else
	int j = 12;
	#endif
	
	result = i + j + 46;
	
	return 0;
}
