int foo(double a, int b, int j)
{
	int i; 
	double dincr = 0.0, dotherincr = 2.3;
	int result = 34;
	for(i = 0; i < b; i += 3)
	{
		int p = 20, q = 30, r = 40;
		double dx = 2.0, dy = 4.0, dz = 6.0;
		
		p = i * 42;
		q = j - p;
		r = 30 * p;
		
		dincr += 12.0;
		dotherincr += 4.5;
		
		dx = dincr * 6.7;
		dy = dx - dotherincr;
		dz = 2.56 * dy;
		
		int m = (int) dz;
		
		result += r + m;
		
	}
	
	return result;
}
