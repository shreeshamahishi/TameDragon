
int foo(int a, int b, int c, int d, int e)
{
	int m = 5, n = 10;
	
	if(d > e){
		a = m + n;
	}
	else{
		a = n - m;
	}
	
	if(d > 10){
		a = a + m + n;
		
	}
	
	a = a -10;
	for(b = 20; b < 30; b++){
		c = c +1;
	}
	
	if(c < 10){
		c = 10;
	}
	
	if(a < 40)
		a = 20;
	
	return a + b + c;
	
}