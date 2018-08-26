// Function taking a function pointer as an argument
double compute_sum(double (*funcp)(double d), int a)
{
	a++;
	printf("%d\n", a);
	double sum = (*funcp)(30.0);
	return sum;
}
