
#include <stdio.h>

double foo(int arg);

void main(int argc, char* argv[])
{
	double result = 0.0;
	result = foo(argc);
	printf("Final result is %d", result); 
}

double foo(int arg) {
	char *pc; 
	short int *psi;
	int *pi, *pi2;
	unsigned int *pi3;
	long int *pli;
	float *pf;
	double *pd;
		 
	char c = 'c';
	short int si = 23;
	int i = 345;		
	signed int i2 = 332;
	unsigned int i3 = 443;
	long int li = 9856789;
	float f = 8.7f;
	double d = 4563.2345;
	int result = 0;
	
	pc = &c; 
	psi = &si;
	pi = &i; pi2 = &i2; pi3 = &i3;
	pli = &li;
	pf = &f;
	pd = &d;

	result = i * arg;
	
	if(i > 200){	
		printf("%c\n", *pc);
		printf("%d\n", *psi);
		printf("%d\n", *pi);
		printf("%d\n", *pi2);
		printf("%d\n", *pi3);
		printf("%d\n", *pli);
		printf("%f\n", *pf);
		printf("%f\n", *pd);	
	}

	return d * arg;

}