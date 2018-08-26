
int val;

void foo(int *ptrX, int *ptrY)
{
	int a = 40, b = 50, *p1, *p2, *p3;
	
	p1 = &a; p2 = &a; p3 = &b;
	
	if(p1 == p2)
	{
		*ptrX = 10; *ptrY = 20;
	}
	
	if(p1 == p3)
	{
		*ptrX += *ptrY;
	}
	
	val = *ptrX + *ptrY;
}