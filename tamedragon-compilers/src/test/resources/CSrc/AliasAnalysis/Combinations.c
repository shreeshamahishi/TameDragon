
int glb_var1, glb_var2;
double glb_var3, glb_var4;

int foo(int* a, int* b, double* d1, double* d2)
{
	int *p1, *p2, x = 32, y = 34;
	double *pd1, *pd2, c = 56.7, d = 21.1;
	
	p1 = &x; p2 = &y;
	pd1 = &c; pd2 = &d;
	
	double res = *pd1 + *pd2 + *d1 + *d2 + glb_var3 + glb_var4;
	
	return *p1 + *p2 + *a + *b + glb_var1 + glb_var2; 
}