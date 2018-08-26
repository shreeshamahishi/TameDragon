#include <stdio.h>

double sin(double d);
double cos(double d);

// Function taking a function pointer as an argument
double compute_sum(double (*funcp)(double d))
{
	double sum = (*funcp)(30.0);
	return sum;
}

int main(void)
{
	double (*fp)(double d); // Function pointer
	double sum;
	// Use 'sin()' as the pointed-to function
	fp = &sin;
	sum = compute_sum(fp);
	printf("sum(sin): %f\n", sum);
	// Use 'cos()' as the pointed-to function
	sum = compute_sum(&cos);
	printf("sum(cos): %f\n", sum);
	return 0;
}
