int foo(int a, int b)
{
	int c = 23;
	double d;

	c = 2 + c;

	if(b < a){
		c = b - 1;
	}
	else{
		c = a - 1;
	}

	return c;

}
