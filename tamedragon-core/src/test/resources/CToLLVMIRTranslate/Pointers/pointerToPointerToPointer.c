int foo(int a){
	int* b = &a;
	int** c = &b;
	int*** d = &c;
	int e = ***d;
	return e;
}
