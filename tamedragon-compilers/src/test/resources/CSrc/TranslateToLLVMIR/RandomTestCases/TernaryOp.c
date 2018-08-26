#include <stdio.h>
int foo(int a){
	return a;
}
int main(){
	int a, b, c;
	printf("Enter the value for a and b");
	scanf("%d,%d",&a,&b);
	c = (foo(a))? a : b;
	printf("\n%d\n",c);
	return c;
}
