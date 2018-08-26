
int foo(int m, int n)
{
	int a, b, *p1, *p2, *p3;
	
	p1 = &a;
	a = 21;
	p2 = &b;
	b = 67;
	
	if(m == n){
		p3 = p1;
	}
	else{
		p3 = p2;
	}
	
	return *p1 + *p2 + *p3;
	
}