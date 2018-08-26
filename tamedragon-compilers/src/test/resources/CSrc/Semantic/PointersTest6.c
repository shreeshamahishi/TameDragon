/*
1. Multi-dimensional arrays and initialization
2. de-referencing a pointer to void.
3. If pa is a pointer, pa[i] is the same as *(pa + i)
4. p[-1], p[-2], etc. are syntactically correct, but things like p[6.7], p[4.5f] are meaningless
5. Comparison between pointers of that do not point to members of the same array is undefined?

 */

void foo(int d[][2][3])
{
	return;
}


void bar(int d[][][3])
{
	return;
}

void foobar(int d[][][])
{
	return;
}

void main()
{
	int r[] = {1, 2, 3};
	int s[] = {3, 4, 5};

	int x, y, z;
	int *pr, *py;


	char c[][2];

	char x1[][3] = {{1, 2, 4}, {3, 4, 8}, {5, 3, 2}, {6,5,7}};
	char x2[4][3] = {{1, 2, 4}, {3, 4, 8}, {5, 3, 2}};
	int x3[3] = {1, 2};
	
	int mm[5.6] = {23, 43, 56};
	int mmq[2][3.2] = {{23, 43, 56}, {23, 43, 56}};

	int m[2][2][3] = {
					{{1, 2, 3}, {4, 5, 6}}, {{1, 2, 3}, {4, 5, 6}}, 
					{{1, 2, 3}, {4, 5, 6}}, {{1, 2, 3}, {4, 5, 6}}					
					};
	int m1[2][2][3] = {
					{{1, 2, 3}, {4, 5, 6}}, 
					{{1, 2, 3}, {4, 5, 6}}					
					};

	int xa[][2][3]  = {
					{{1, 2, 3}, {4, 5, 6}}, {{1, 2, 3}, {4, 5, 6}}, 
					{{1, 2, 3}, {4, 5, 6}}, {{1, 2, 3}, {4, 5, 6}}					
					};

	int yb[][][3]  = {
					{{1, 2, 3}, {4, 5, 6}}, {{1, 2, 3}, {4, 5, 6}}, 
					{{1, 2, 3}, {4, 5, 6}}, {{1, 2, 3}, {4, 5, 6}}					
					};
	int zc[][][]  = {
					{{1, 2, 3}, {4, 5, 6}}, {{1, 2, 3}, {4, 5, 6}}, 
					{{1, 2, 3}, {4, 5, 6}}, {{1, 2, 3}, {4, 5, 6}}					
					};

	foo(m);

	x = r[4.5];
	y = r[2];

	pr = r +1;
	py = s +2;

	r[-3] = -56;
	pr[-6] = 2;
	
	if(pr < py){
		x = 43;
	}
}