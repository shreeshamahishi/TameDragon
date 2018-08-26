#include<stdio.h>
#define ESP 0.0001
#define F(x) (x)*(x) - 4*(x) - 10
double fabs(double d){
	if(d < 0)
		return -1.0 * d;
	else
		return d;
}
int main()
{
	double x1,x2,x3,f1,f2,t;
	printf("\nEnter the value of x1: ");
	scanf("%lf",&x1);
	printf("\nEnter the value of x2: ");
	scanf("%lf",&x2);
	printf("\n______________________________________________\n");
	printf("\n    x1\t  x2\t  x3\t     f(x1)\t f(x2)");
	printf("\n______________________________________________\n");
	do
	{
		f1=F(x1);
		f2=F(x2);
		x3=x2-((f2*(x2-x1))/(f2-f1));
		printf("\n%lf   %lf   %lf   %lf   %lf",x1,x2,x3,f1,f2);
		x1=x2;
		x2=x3;
		if(f2<0)
			t=fabs(f2);
		else
			t=f2;
	}while(t>ESP);
	printf("\n______________________________________________\n");
	printf("\n\nApp.root = %lf",x3);
}
