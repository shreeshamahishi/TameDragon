#include<stdio.h>
int add(int a, int b){
	int result;
	result = a + b;
	return result;
}	

int foo(int b){
	int a,c;
	a = 10;
	c = add(a, b);
	printf("addtition of the two no is %d",c);
	return c;
	}
	
