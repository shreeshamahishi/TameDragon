int foo(int a, int b)
{
	int c = 0;
	if(b < a){
		c = b - 1;
	}
	else{
		c = a - 1;
	}

	return c;
}
