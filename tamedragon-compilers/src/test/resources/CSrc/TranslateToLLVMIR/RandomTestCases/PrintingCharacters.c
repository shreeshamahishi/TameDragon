#include<stdio.h>
#include<string.h>
int main(){
	char chArr[200];
	printf("Enter a string ");
	gets(chArr);
	printf("You Entered %s\n", chArr);
	int len = strlen(chArr);
	for(int i = 0; i < len; i++)
		printf("%c\n",chArr[i]);

	return 0;
}
