#include<stdio.h>
int foo(int a, int b){
	if(a && b || a || b)
		return 0;
	else
		return 1;
}
int main(){
	int retVal = foo(1,2);
	printf("%d", retVal);
	return retVal;
}

