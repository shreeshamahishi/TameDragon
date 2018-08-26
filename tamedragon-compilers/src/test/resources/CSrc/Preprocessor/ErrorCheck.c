#define FOO 10
#if !defined FOO
#error "Error"
#else
#define MACRO 1
#endif

int main(){
	int i = FOO;
	int j = MACRO;
}