#include <stdio.h>
# define MAXSIZE 200

int stack[MAXSIZE];
int top;	//index pointing to the top of stack

void push(int y)
{

	if(top>MAXSIZE)
	{
		printf("STACK FULL");
		return;
	}
	else
	{
		top++;
		stack[top]=y;
	}
}

int pop()
{
	int a;
	if(top<=0)
	{
		printf("STACK EMPTY");
		return 0;
	}
	else
	{
		a=stack[top];
		top--;
	}
	return a;

}

int main()
{
	int will=1,i,num;

	while(will == 1)
	{
		printf("MAIN MENU:\n1.Add element to stack\n2.Delete element from the stack\n");
		scanf("%d",&will);

		switch(will)
		{
		case 1:
			printf("Enter the data... ");
			scanf("%d",&num);
			push(num);
			break;
		case 2: i=pop();
		printf("\nValue returned from pop function is  %d ",i);
		break;
		default: printf("Invalid Choice . ");
		}

		printf("\nDo you want to do more operations on Stack ( 1 for yes, any other key to exit) ");
		scanf("%d" , &will);
	} //end of  outer while
}               //end of main

