int foo(int m){
	int a = m * 2, b = m * 2, i ,d = m;
	
	for(i = 0 ;i < 3;i++)
	{
		if(d==5)
			d = a + 3;
		else
			d = b + 3;
	}
	
	return d;
}