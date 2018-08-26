
void main()
{
	struct st{
		int i;
		double d;
		char *ptr;
	} st1, st2;
	
	union un{
		int m;
		char* str;
		double d;
	} un1, un2;
	
	typedef struct st *StrPtr;
	
	typedef int Length;
	typedef long Population;
	
	Length length1, length2;
	Population pop1, pop2;
	StrPtr str_ptr1;
	
	double d = 45.6, d1;
	long l;
	float fl1, fl2;
	int i;
	short int s;
	char *ptr = "Hello";
	int *pi, *pm;
	double *pd;
	char c;
	int iarr[20];
	double darr[20];
	
	enum boolean{NO, YES} yesOrNo;

	// Assign various types to a char	
	c = '3';
	c = 127;
	c = 128;
	c = 256;
	c = -127;
	c = -255;
	c = 1000;	
	
	c = "world";
	c = d;
	c = fl1;
	c = l;
	c = i;
	c = s;
	c = ptr;
	c = pi;
	c = pd;
	c = st1;
	c = un1;
	c = &i;
	c = i * i;
	c = iarr;
	c = darr;
	c = length1;
	c = pop1;
	c = str_ptr1;
	c = yesOrNo;
	c = YES;
	
	// Assign various types to a short
	s = "world";
	s = d;
	s = fl1;
	s = l;
	s = i;
	s = c;
	s = ptr;
	s = pi;
	s = pd;
	s = st1;
	s = un1;
	s = &i;
	s = i * i;
	s = iarr;
	s = darr;
	s = length1;
	s = pop1;
	s = str_ptr1;
	s = yesOrNo;
	s = YES;
	
	// Assign various types to a int	
	i = "world";
	i = d;
	i = fl1;
	i = l;
	i = c;
	i = s;
	i = ptr;
	i = pi;
	i = pd;
	i = st1;
	i = un1;
	i = &i;
	i = i * i;
	i = iarr;
	i = darr;
	i = length1;
	i = pop1;
	i = str_ptr1;
	i = yesOrNo;
	i = YES;
	
	// Assign various types to a enum specifier variable	
	yesOrNo = "world";
	yesOrNo = d;
	yesOrNo = fl1;
	yesOrNo = l;
	yesOrNo = c;
	yesOrNo = s;
	yesOrNo = ptr;
	yesOrNo = pi;
	yesOrNo = pd;
	yesOrNo = st1;
	yesOrNo = un1;
	yesOrNo = &i;
	yesOrNo = i * i;
	yesOrNo = iarr;
	yesOrNo = darr;
	yesOrNo = length1;
	yesOrNo = pop1;
	yesOrNo = str_ptr1;
	yesOrNo = YES;
	
	// Assign to a enum constant (error)
	NO = yesOrNo;
	NO = d;
	
	// Assign various types to a long value
	l = "world";
	l = d;
	l = fl1;
	l = c;
	l = i;
	l = s;
	l = ptr;
	l = pi;
	l = pd;
	l = st1;
	l = un1;
	l = &i;
	l = i * i;
	l = iarr;
	l = darr;
	l = length1;
	l = pop1;
	l = str_ptr1;
	l = yesOrNo;
	l = YES;
	
	// Assign various types to a float value
	fl1 = "world";
	fl1 = d;
	fl1 = fl2;
	fl1 = c;
	fl1 = i;
	fl1 = s;
	fl1 = ptr;
	fl1 = pi;
	fl1 = pd;
	fl1 = st1;
	fl1 = un1;
	fl1 = &i;
	fl1 = i * i;
	fl1 = iarr;
	fl1 = darr;
	fl1 = length1;
	fl1 = pop1;
	fl1 = str_ptr1;
	fl1 = yesOrNo;
	fl1 = YES;
	
	// Assign various types to a double value
	d1 = "world";
	d1 = d;
	d1 = fl1;
	d1 = c;
	d1 = i;
	d1 = s;
	d1 = ptr;
	d1 = pi;
	d1 = pd;
	d1 = st1;
	d1 = un1;
	d1 = &i;
	d1 = i * i;
	d1 = iarr;
	d1 = darr;
	d1 = length1;
	d1 = pop1;
	d1 = str_ptr1;
	d1 = yesOrNo;
	d2 = YES;
	
	// Assign various types to a pointer to an int or a double
	pi = 0;
	pd = 0;
	pi = 00;
	pi = 0x0;
	pi = "world";
	pi = d;
	pi = fl1;
	pi = c;
	pi = i;
	pi = s;
	pi = ptr;
	pi = pi;
	pi = pd;
	pi = st1;
	pi = un1;
	pi = &i;
	pi = i * i;
	pi = iarr;
	pi = darr;
	pi = length1;
	pi = pop1;
	pi = str_ptr1;
	pi = yesOrNo;
	pi = YES;
	
	// Assign various types to a struct
	st1 = 0;
	st1 = "world";
	st1 = d;
	st1 = fl1;
	st1 = c;
	st1 = i;
	st1 = s;
	st1 = ptr;
	st1 = pi;
	st1 = pd;
	st1 = st2;
	st1 = un1;
	st1 = &i;
	st1 = i * i;
	st1 = iarr;
	st1 = darr;
	st1 = length1;
	st1 = pop1;
	st1 = str_ptr1;
	st1 = yesOrNo;
	st1 = YES;
}