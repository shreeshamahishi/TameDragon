
#include <stdio.h>

int foo(int m, int n, int k);

int main(int argv, char *argc[])
{
	printf("%d", foo(2, 3, 8));
	return 0;
}

int foo(int m, int n, int k)
{
	int a = 2;
	int b = 3;
	int c, d, e, f;
	
	d = a + b;
	e = m + n;
	c = d + e;
	f = c + k;
	
	return f;
}