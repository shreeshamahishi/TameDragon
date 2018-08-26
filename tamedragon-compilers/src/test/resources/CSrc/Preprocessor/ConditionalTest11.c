# define ABSDIFF(a,b) ((a) < (b) ? (b) - (a) : (a) - (b))
# if ABSDIFF(1,2)
# define MACRO1 10
# else
# define MACRO1 20
# endif

void foo(){
	int i = MACRO1;
}
