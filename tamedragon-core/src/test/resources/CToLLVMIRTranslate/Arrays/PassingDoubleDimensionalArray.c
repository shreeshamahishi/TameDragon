#include<stdio.h>
void boo(int a[3][3]){
	int sum, i, j;
	for(i = 0; i < 3; i++){
		for(j = 0; j < 3; j++){
			sum += a[i][j];
		}
	}
	printf("sum = %d", sum);
}
int main(){
	int a[3][3] = {{1,2,3},{4,5,6},{7,8,9}};
	boo(a);
}
