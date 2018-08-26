
int foo(int a, int b)
{
	int c = 4, d = 9;
	
	if(a > b){
		c = d + 9;
		if(a > c)
		{
			d = c + 56;
		}
		else
		{
			d = d + 21;
		}
		
		d = a + d;
		
	}
	else
	{
		d = a + b;
	}
	
	return d;
}

