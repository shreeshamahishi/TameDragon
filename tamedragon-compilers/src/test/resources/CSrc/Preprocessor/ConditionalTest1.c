
#define _COND_TEST_1 100

int main()
{
	int result = 0;
	#ifdef _COND_TEST_1
	int i = 23;
	#endif
	
	result = i + 46;
	
	return 0;
}

