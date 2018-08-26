#define FOO(a,b) a+b
#define KOO(x,y) FOO(x,y)

int main(){
	int i = KOO(1,2);
	return 0;
}
