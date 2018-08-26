
#include <stdio.h>

int main(int args, char *agrv[])
{
	int val = 34;
	int i = 10;
	int j = 20;
	int max = 40;
	int min = 10;
	
	if(j < max){
		i = 15;
	}
	else{
		i = 4;
	}	
	printf("%d", i);
	return 0;
}
