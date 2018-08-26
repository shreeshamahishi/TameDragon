
struct struct_ext_decl_test
{
	const double d, ad, yad;
	volatile unsigned int i, ai, yai, yaai;
	unsigned long lo, *lo, alo, yalo;
	volatile const volatile mmmm;
	short s, as;
	double int short heh;
	int i[87][45], **pi;
	volatile double *const *volatile *pd, d;
	signed long lo[67], *plo[56], **pplo[45][69];
	
	char **argv;
	int (*daytab)[13];
	int *daytab1[13];
	void *comp();
	void (*comp1)();
	char (*(*x())[])();
	char (*(*a[3])())[5];
	
	char c[56]();
	int mm[67][]();
	int qn()();
	double dd()[];
};

struct point {
	int x;
	int y;
};

struct dpoint {
	double x;
	double y;
};

struct test1
{
	double d;
	struct point p;
	int m;
	struct xyz q;
	
};

struct test2
{
	int m;
	double d;
	char * pmsg;
};

struct test3
{
	int m;
	struct inner {
		double m;
		double n;
	} rms;
	char * pmsg;
};

struct test3
{
	int m; double d;
};

struct test4
{
	int m;
	struct inner {
		double m;
		double n;
	} rms;
	char * pmsg;
};

void main()
{
	int m, n, p;
	
	struct inner i2;
	
	struct struct_ext_decl_test mx;
	
	struct point x, y, z;
	struct point mr = {23, 4.3};
	struct point mr1 = {23, 4, 45};
	struct point mr2 = {23, "Hello", 43};
	struct point mr3 = {45};
	struct test4 t4;
	
	struct dpoint dr = {4.6, 6.7};
	struct dpoint dr1 = {4.6, "tootsie"};
	struct dpoint dr2 = {4.6, 6.7};
	
	struct another_test xyz1 = {1, .03, "Shutter"};
	
	struct test4 inner_inst = {0, {4.1, 3.2}, "hello complex"};
	
	m = x.x + y.y + z.y;
	n = mr.x + mr.y;
	p = mr3.x + mr3.y;
	
	mx.d = 78.0987;
	mx.heh = 9.0;
	t4.heh = 908;
}
