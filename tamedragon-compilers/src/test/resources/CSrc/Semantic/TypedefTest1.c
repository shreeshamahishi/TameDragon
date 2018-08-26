
double dd = 56.5432;
typedef int Length;
typedef double Height;
typedef double Area;

Length length;
const Length al;
Height height;

struct tnode{
	char *pmsg;
	int count;
	Length len;
};

struct another_node{
	double d;
	Area ar;
};

typedef struct tnode* ptr_node;
typedef struct another_node* ptr_anode;

typedef double Height;  // Repeat same
typedef int Height;  // Repeat with different type

typedef int XX = 34;  // With initializer
typedef char (*(*Complex1[2])())[3];  // x is array[2] of pointer to function returning
                                    // pointer to array[3] of char

char* foo1();
char* foo2();

typedef int (*Complex2)(char * c1, char * c2);
typedef int (*AnotherComplex)(double d1 );

typedef int XYZ;

typedef int ABC;
typedef ABC DEF;
typedef DEF GHI;

typedef int XXXX;
typedef const XXXX YYYY;
typedef double Abcd;
typedef unsigned Abcd Efgh;

typedef int what, where, how;
typedef int here = 56; 

typedef noel foobar();
typedef almeida foobar1(int x, char* y);

ABC *ptr_abc;

void foo(Length len, Area ar)
{
	int m;
	double r;
	m = len;
	r = ar; ar = m * r;
}

int bar1(char* c1, char* c2)
{
	typedef double XYZ; // Repeat inside another scope with different type
	int x;
	x = XYZ;
	return -1;
}

int bar2(double d)
{
	return 10;
}

GHI bar3()
{
	return 8.9;
}

DEF bar4()
{
	return 1;
}

void main()
{
	int i;
	ABC j;
	Length l1, l2;
	Height h1, h2, h3;
	Area a1, a2, a3;
	
	double Area;    // Variable name is same as that of the name of a type
	
	struct tnode *pnode1, *pnode2;
	struct another_node *panode1, *panode2;
	
	ptr_node pnodetd1;
	ptr_anode panodetd1;
	
	char* (*x1) ();  // x1->Pointer to function returning pointer to array[3] of char
	char* (*x2) ();  // x1->Pointer to function returning pointer to array[3] of char
	
	Complex1 c1;
	Complex2 complex2_1, complex2_2;
	AnotherComplex ac1;
	
	complex2_1 = bar1;      // OK
	complex2_2 = bar2;      // NOT OK
	ac1 = bar2;             // OK
	ptr_abc = &i;           // OK
	ptr_abc = &Area;         // NOT OK
	
	pnode1->pmsg = "hello world";
	pnode1->count = 1;
	
	pnode2 = pnode1; // OK
	panode1 = pnode2; // NOT OK
	
	pnodetd1 = pnode1;    // OK
	panodetd1 = panode1;  // OK
	
	pnodetd1 = panodetd1;  // NOT OK
	
	l2 = 30;
	h2 = 43.4;  // OK
	l1 = l2;    // OK
	l1 = h2;    // NOT OK
	
	i = 34;
	length += i;
	al += length;
	
	Area = l2 * h2;
	
	x1 = foo1;
	x2 = foo2;
	
	c1[0] = x1;
	c1[1] = x2;
	
	j = 56.8;
	
	foo(l1, a1);  // OK
	foo(i, Area);  // OK
}
