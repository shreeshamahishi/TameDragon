#define xy _x
#define CONCAT(a,b) a ## b
#include <stdio.h>
int main(){
	int CONCAT(x,y) = 10;
	printf("%d\n", CONCAT(x,y));
	return 0;
}
