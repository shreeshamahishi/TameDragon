
#include <stdio.h>

int main(int argc, char* argv[])
{
	int *pa, *pb;
	int a, b, c;
	a = 10; b = 20; c = 30;
	
	pa = &a; pb = &b;
	
	*pa = *pb + c;
		
	printf("Value of a, b and c are %d, %d and %d respectively\n", a, b, c);
}