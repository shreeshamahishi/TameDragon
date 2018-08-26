#include<stdio.h>
#include<string.h>
#define size 10
char stack[size];
int tos=0,ele;
void push(int ele);
char pop();
void show();
int isempty();
int isfull();
char infix[30],output[30];
int prec(char ch);

int main()
{
	int i=0,j=0,k=0,length;
	char temp;
	printf("\nEnter an infix expression:");
	scanf("%s",infix);
	printf("\nThe infix expresson is %s",infix);
	length=strlen(infix);
	for(i=0;i<length;i++)
	{
		//Numbers are added to the out put QUE
		if(infix[i]!='+' && infix[i]!='-' && infix[i]!='*' && infix[i]!='/' && infix[i]!='^' && infix[i]!=')' && infix[i]!='(' )
		{
			output[j++]=infix[i];
			printf("\nThe element added to Q is:%c",infix[i]);
		}
		//If an operator or a bracket is encountered...
		else
		{
			if(tos==0) //If there are no elements in the stack, the operator is added to it
			{
				push(infix[i]);
				printf("\nThe pushed element is:%c",infix[i]);
			}
			else
			{       //Operators or pushed or poped based on the order of precedence
				printf("\n Now the current character is :%c",infix[i]);
				if(infix[i]!=')' && infix[i]!='(')
				{
					printf("\nprec(infix[i]) = %d", prec(infix[i]));
					printf("\nprec(stack[tos-1]) = %d", prec(stack[tos-1]));

					if( prec(infix[i]) <= prec(stack[tos-1]) )
					{
						temp=pop();
						printf("\n the poped element is :%c",temp);
						output[j++]=temp;
						push(infix[i]);
						printf("\n The pushed element is :%c",infix[i]);
						show();
					}
					else
					{
						printf("\nThe pushed element is:%c",infix[i]);
						printf("\nVikash");
						push(infix[i]);
						printf("\nThe pushed element is:%c",infix[i]);
						printf("\nJoshi");
						//printf("\nThe pushed element is");
						show();
					}
				}
				else
				{
					if(infix[i]=='(')
					{
						push(infix[i]);
						printf("\nThe pushed-- element is:%c",infix[i]);
					}
					if(infix[i]==')')
					{
						temp=pop();
						while(temp!='(')
						{
							output[j++]=temp;
							printf("\nThe element added to Q is:%c",temp);
							//temp=pop();
							printf("\n the poped element is :%c",temp);
							temp=pop();
						}
					}
				}

			}

		}

		printf("\nthe infix expression is: %s",output);

	}
	while(tos!=0)
	{
		output[j++]=pop();
	}
	printf("the infix expression is: %s\n",output);

}
//Functions for operations on stack
void push(int ele)
{
	char ch = ele;
	printf("\ninside push()");
	printf("\nelement to be pushed is %c", ch);
	printf("\ntos = %d", tos);
	stack[tos]=ele;
	tos++;
	printf("\ncomming out of push()");
}
char pop()
{
	tos--;
	return(stack[tos]);
}
void show()
{
	int x=tos;
	printf("--The Stack elements are.....");
	while(x!=0)
		printf("%c, ",stack[--x]);
}
//Function to get the precedence of an operator
int prec(char symbol)
{

	if(symbol== '(')
		return 0;
	if(symbol== ')')
		return 0;
	if(symbol=='+' || symbol=='-')
		return 1;
	if(symbol=='*' || symbol=='/')
		return 2;
	if(symbol=='^')
		return 3;
	return 0;
}
