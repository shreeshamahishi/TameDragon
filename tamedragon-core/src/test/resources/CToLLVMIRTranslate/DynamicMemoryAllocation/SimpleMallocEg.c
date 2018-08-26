#include <stdio.h>
#include <malloc.h>

int main(int argc, char** argv){
	int* ptr = malloc(sizeof(int));
	*ptr = 3;
	printf("value at ptr = %d", *ptr);
	return 0;
}
