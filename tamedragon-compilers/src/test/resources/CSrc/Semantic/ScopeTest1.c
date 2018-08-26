/*
	Tests scope of variables
*/

int a = 10;
double d, b = 42.0;
short s;

void main()
{
	int outermost_int = 43;
	double outermost_dbl = 43.345;
	
	{
		int nextA = 32;
		double r = b + nextA;
		r = outermost_dbl + outermost_int;
		
		{
			short ss = s;
			double dfd = r + outermost_dbl;
			
			int ase = 345;
		}
		
		r += ss;   // should give a problem
		
		ase = mm;   // should give a problem
		outermost_int = dfd;   // should give a problem
		
	}
	
	outermost_int = 567;
	outermost_dbl = mm;
	
	outermost_int = a;
}