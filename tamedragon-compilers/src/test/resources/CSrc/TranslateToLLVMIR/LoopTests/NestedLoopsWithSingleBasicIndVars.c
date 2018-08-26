
int foo(double a, int b, int j)
{
	int master;
	int i;
	double dincr = 0.0, dotherincr = 2.3;
	int result = 34;
	for(i = 0; i < b; i+=3)
	{
		int p = 20, var;
		double dx = 2.0, dy = 4.0, dz = 6.0;

		int inner_index1 =0;
		int inner_index2 = 3;
		int ir = 0;
		while(inner_index1 < p)
		{
			int var1 = inner_index1 * 2;
			int var2 = inner_index2 * 4;

			master += var1 + var2;

			inner_index1 += 2;
			inner_index2 + 5;

			int check = 0;
			int innermost_index = 3;
			for(check = 1; check < innermost_index; innermost_index+=4){
				dx = 89.7 * innermost_index;
				dy = dx - 8.0;
				dz = 3.0 * dy;
			}

			ir = (int) dz;
		}

		result += ir + j;
	}

	return result;
}
