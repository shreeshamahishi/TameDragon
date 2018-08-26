#define XYZ(a,b) ((a) + (b))
#if XYZ (1,2)
	#if 1
	#define PQR 20
	#else
	#define PQR 30
	#endif
#endif

int main(){
	int i = PQR;
	return 0;
}
