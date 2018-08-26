# include <stdio.h>
void boo(int a[3]){
	int sum, i;
	for(i = 0; i < 3; i++){
		sum += a[i];
	}
	printf("sum = %d", sum);
}
int main(){
	int a[3] = {1,2,3};
	boo(a);
}
