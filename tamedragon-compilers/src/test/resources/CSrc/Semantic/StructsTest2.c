
struct abc {
	int m;
	double d;
	struct  {
		short int i;
		float f;
		struct  {
			int m;
			double point;
		} st;
	} yard;
	
	char* str;
};

struct ext {
	char c;
	struct ext_inner{
		double r;
		int qq;
	} x1;	
};

struct a{
	int mq;
	char c;
};

struct b{
	int mq;
	char c;
};

struct key{
	char *word;
	int count;
};

void foo(int m, struct a a, struct b b);

struct b foo1(){
	struct b ret;
	ret.mq = 3;
	ret.c = 'c';
	
	return ret;
}

void foo(int m, struct a a, struct b b){
	struct a w1;
	struct b w2;
	
	w1 = a;
	w2 = b;
	
}

struct point {
	double x;
	double y;
};

void main(){
	double r1, r2;
	int mz1, mar2, num;
	short int mxx; 
	
	struct abc abc;

	struct def{
		int mm;
		struct inner{
			double x;
			double y;
		} rr;
	};

	struct ext_inner{
		int r;
		short int qq;
	} b;	

	struct a a1;
	struct b b1, b2;
	
	struct inner *ptr_inner;	
	struct point origin, *pp;

	// Declaration of array of structs with continuous initializers
	struct key keytab1[] = {"auto", 0,
							"break", 0,
							"case", 0
							};
							
	// Declaration of array of structs with more precise initializers							
	struct key keytab2[] = {{"auto", 0},
							{"break", 0},
							{"case", 0},
							};
							
	abc.yard.st.m = 45;   // Test complex nested defintions
	if(a1 == b1){    // Comparison of two similar structs - invalid
		a.c1 = 'c';
	}
	if(a1 == a){    // Comparison of two dis-similar structs - invalid
		a.c1 = 'c';
	}

	// Comparison with some other expression
	if(b1 == 3){      
		b1.mq = 45;
		b1.c = 'f';
	}
	if(b1 >=  4){ 
		b1.mq = 0.98;
		b1.c = 'g';
	}
	if(3 <= a1){      
                b1.mq = 45;
                b1.c = 'f';
        }
        if(76 != a1){      
                b1.mq = 0.98;
                b1.c = 'g';
        }

	// Other operators should be invalid
	if(b1 + 3){      
                b1.mq = 45;
                b1.c = 'f';
        }
        if(a1 -  4){      
                b1.mq = 0.98;
                b1.c = 'g';
        }
	if(76 *  b1){
                b1.mq = 0.98;
                b1.c = 'g';
        }

	b1 = a1 + b1;

	abc = a;          // Assign two dis-similar structs
	a1 = b1;          // Assign two "similar" structs
	b1 = b2;          // Assign two structs of the same type
	ptr_inner = &a;   // Take address of an undeclared struct
	ptr_inner = &a1;  // Take address of a declared struct that is not the same type

	r1 = 2.3;
	a1 = 10;   // Incompatible types		
	b1 = r1;   // Incompatible types

	b1 = foo1();  
	a1 = foo1();  // InCompatible types (returning "similar" structure, but different)
	
	foo(9, a1, b1); // Calling with the correct parameters;
	foo(19, a, b);  // Calling with the wrong parameters;

	pp = &origin;
	r1 = (*pp).x;   // OK
	r2 = *pp.x;   // NOT OK

	r1 = pp->x;    // OK	
	keytab1[0].word = "heaven";
	keytab1[0].count = 6;
	
	num = sizeof keytab1 / sizeof(struct key);  // Form of sizeof : sizeof(struct key)
	num = sizeof keytab1 / sizeof keytab1[0];   // Form of sizeof : sizeof keytab1[0]
}
