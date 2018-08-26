
#define VAR0 0 
#define VAR1 3
#define VAR2 20

#define VAR3 VAR1
#define VAR4 VAR3
#define VAR5 VAR4
#define VAR6 VAR5
#define VAR7 VAR6

#define VAR8 VAR2
#define VAR9 VAR8
#define VAR10 VAR9
#define VAR11 VAR10

#define MAX_COUNT VAR7 * VAR11
#define INIT 0
#define _DEBUG_ 1

int main()
{
	int x = 0;
	int val = 25;
	for(x = INIT; x < MAX_COUNT; x++)
	{
		val += x;				
	}
}


