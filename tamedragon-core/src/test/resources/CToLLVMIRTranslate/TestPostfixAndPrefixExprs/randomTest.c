int foo(int a, int b){
	a++;
	--b;
	a = b++;
	b = ++a;
	return a++;
}
