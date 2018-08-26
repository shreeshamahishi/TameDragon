
#include <stdio.h>

int main()
{
	int result = 0;
	int flag = 1;
	int x = 1;
	int counter;
	
	for(counter = 0; counter < 25; counter++){
		if(x < 7){
			x = flag;	
			result += 82;
		}
		else{
			x = counter;
			result += x;
		}	
	}
	
	printf("Result is %d\n", result);
	
	return 0;
}