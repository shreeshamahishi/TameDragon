int foo(int a, int b)
{
	int c = 43, d = 98;
	int i = 3;
	for(i = 0; i < a; i++)
	{
		if(b > i + c)
		{
			d += a + i;
		}
		else{
			d += b * i;
		}

		d += 3;
	} 

	d -= 4;

	return d;
}
