#include<stdio.h>

void main(){
	int x = 5;
	int y;
	if(x == 1){
		y = x+1;
	}
	else if(x == 2){
		y = x+2;
	}
	else if(x == 3){
		y = x+3;
	}
	else{
		y = x+5;
	}
	printf("%d",y);
}