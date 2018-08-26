#define MACRO1 10
#define MACRO2 20
#if !defined MACRO1 && !defined MACRO2
#define MACRO3 30
#else
#define MACRO3 40
#endif

void foo(){
	int i = MACRO3;
}
