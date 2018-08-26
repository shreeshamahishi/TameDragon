#include<stdio.h>
#include<malloc.h>
int main(){
	int ** S, i = 0, j = 0;
    S = (int **)malloc( 10 * sizeof(int) );
	for(; i < 10; i++)
		for(; j < 10; j++)
			S[i][j] = i + j;
	free(S);
}
