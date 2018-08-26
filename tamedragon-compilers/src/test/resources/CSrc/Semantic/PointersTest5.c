void foo(int dt[2][5])
{
	*dt = 45;
	(*dt)++;
	**dt = 56;
}

void bar(int dt[][3])
{
	
}

void bar1(int (*dt)[3])
{
	
}

void foobar(int dt[][])
{
}

char *month_name(int n)
{
	static char *name[] = {
	"Jan", "Feb", "Mar", "Apr", "Jun", "Jul", "Aug",
	"Sep", "Oct", "Nov", "Dec"};
	
	if(n < 1 || n > 12)
		return name[0];
	else
		return name[n];
	
}
void main()
{
	// Declarations

	char c[] = {'O', 'k'};
	char c1[] = {4, 5, 6};
	char c2[] = {4, 4.5, 6};
	
	int i[34] = {2.6, 3.4, 4};
	double d[] = {5.6, "Hello"};
	double d1[] = {3.4, c[0]};
	
	char *c4[] = {"hello", "there"};
	char *pmsgs = {"hello", "there"};
	char c5[] = '4';
	
	int iarr[] = "hello oiq";
	char c6[5] = "hello";
	
	int marr[2][3] = {{1,2,3},{4, 5, 6}};
				 
	static char daytab[2][5] = {
		{0, 31, 28, 31, 30 },
		{0, 31, 29, 31, 30 }
	};
	
	char *pc[4] = {"abcdef", "efg", "hij", ""};
	
	char pc2[3][2] = {"Jan", "February", ""};

	char **pc3;

	char * temp = {'a', 'b'};                     // Cannot initialize like this
	int x1[2][3] = {{1, 2, 4}, {3, 4, 1}};        // OK
	int *x2[3] = {{1, 2, 4}, {3, 4, 1}};          // Cannot initialize like this
	int (*x3)[3] = {{1, 2, 4}, {3, 4, 1}};        // Cannot initialize like this
	
	
	int x4[2][3] = {{1, 2, 4}, {3, 4, 1}, {1, 23, 4}};   // More elements?
	int (*x5)[2];                   
	int (*x6)[3];
	char narr[2][2] = { {'a', 'b'}, {'c', 'd', 'e'}};
	int a[10][20];
	int *b[10];
	
	int qqqq = {1, 2, 3};
	
	// Logic

	bar(x1);
	bar1(x1);
	foobar(x1);
	
	x5 = x4;
	x6 = x4;
	
	pc3 = c4;

	*pc3 = 45;
	(*pc3)++;
	**pc3 = 'i';

}