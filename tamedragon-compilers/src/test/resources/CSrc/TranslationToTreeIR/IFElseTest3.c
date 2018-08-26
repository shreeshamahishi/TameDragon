#include<stdio.h>
void main(int args, char *agrv[]){
	int x = 5;
	if(x == 5){
		int y = 10;
		if(y == 10){
			x = x * y;
		}else{
			x = y/x;
		}
	}else{
		x = 0;
	}
	printf("%d", x);
}