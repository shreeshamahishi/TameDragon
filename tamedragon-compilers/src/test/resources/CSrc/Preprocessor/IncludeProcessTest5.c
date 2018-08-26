#include "h3.h"

#define MAX 30
#define MIN 2

#include "h4.h"

int main()
{
	int x = 0;
	int m = 43, n = 45;
	
	if(switch_over())
		x = VAR1 + VAR2;
	else
		x = VAR3 + VAR4 - VAR5;
		
	return x;
}

double switch_over()
{
	int m = 400;
	if(m > MAX - MIN)
		return 0.0;
	else
		return 0.2;
}