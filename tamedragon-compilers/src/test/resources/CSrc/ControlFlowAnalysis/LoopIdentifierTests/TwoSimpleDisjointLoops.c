
int bar(int x, int y)
{
	int a, b, m = 45, n = 50;
	
	for(a = 0; a < m; a++)
	{
		b += x;
	}
	
	b += n;
	
	for(a = 0; a < n; a++)
	{
		b += y;
	} 
	
	n = b + x + y;
	return n;
	
}