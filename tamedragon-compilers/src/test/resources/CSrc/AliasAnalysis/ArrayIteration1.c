
#include <stdio.h>

int MAX = 300;

int foo(int m, int arr[MAX])
{
	extern int split1, split2;
	
	int *p1, *p2, *p3, *ptr, count = 0, result = 65;
	
	p1 = arr; p2 = arr; p3 = arr;
	
	while(count < m)
	{
		p1 = p1 + 2 ; p2++; p3++;
		if(count < split1){
			ptr = p1;
		}
		if(count >= split1 && count < split2){
			ptr = p2;
		}
		else{
			ptr = p3;
		}
		
		result += *ptr + *p1 + *p2 + *p3;
		
		count++;  
	};
	
	return result;
}