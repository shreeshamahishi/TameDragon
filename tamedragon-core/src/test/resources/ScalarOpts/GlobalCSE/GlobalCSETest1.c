int foo(int a, int b, int sum){
    for(int i = 0; i < a+b; i++)
	sum += a+b;

    return sum;
}