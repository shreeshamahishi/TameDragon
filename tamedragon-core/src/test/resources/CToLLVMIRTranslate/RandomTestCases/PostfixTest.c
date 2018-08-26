#include <stdio.h>

int i = 0;
void foo(int a){
	printf("foo %d\n", a);
	if(i < 10){
		foo(i++);
	}
}

int main(){
	foo(-1);
}
