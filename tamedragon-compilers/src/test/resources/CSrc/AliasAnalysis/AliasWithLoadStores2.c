
int *pglobal;
int FLAG;

int foo(int max_iter, int split, int *pArg)
{
	int **ptr1, **ptr2, *p1, *p2, a, b;
	ptr1 = &p1;
	ptr2 = &p2;
	p1 = &a; p2 = &b;
	*p1 = 42; *p2 = 34;
	int result = 0;
	int count = 0;
	int m0 = **ptr1;
	int n0 = **ptr2;
	result = m0 - n0;
	
	while(count < max_iter)
	{
		*ptr1 = pglobal;
		int m1 = **ptr1;
		int n1 = **ptr2;
		result -= m1 * n1;
		if(count < split)
		{
			*ptr1 = p1;
			*p2 = FLAG; 
		}
		else
		{
			*ptr1 = p2;
			*ptr2 = p2;
			
			int m2 = **ptr1;
			int n2 = **ptr2;
			
			result += m2 + n2;
		}
		int m3 = **ptr1;
		int n3 = **ptr2;
		result += m3 + n3;
		
		*ptr1 = pArg;
		
		int m4 = **ptr1;
		int n4 = **ptr2;
		result -= m4 + n4;
	}

	return result;	
}