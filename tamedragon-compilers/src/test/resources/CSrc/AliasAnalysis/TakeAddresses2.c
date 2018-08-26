
int glb = 20;

int foo(int m, int n)
{
	int a = 45, b = 43, c = 98, *p1, *p2, *p3;
	
	p1 = &a; p2 = &b; p3 = &c;
	
	if(m > n)
		p1 = p2;
	
	p3 = p2;
	
	glb++;
	
	return *p1 + *p2 + *p3 + glb;
	
}