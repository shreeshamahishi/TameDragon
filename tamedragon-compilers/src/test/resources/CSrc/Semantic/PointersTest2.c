
void foo(int *p)
{
	return;
}

void foo1(int p[])
{
	return;
}

void foo2(int p[10])
{
	return;
}

void main(){
	int int_arr1[10];
	int int_arr2[10];
	int int_arr3[20];
	
	int *pi, *pm, a;
	int x = 0, y;
	double d = 3.414;
	float fl = 45.2f;
	char c = '4';
	short int shr = 34;
	long int ln = 6787634;
	int m1 = 3, m2 = 67, m3 = 56;
	
	int pa[];
	int px[2];
	
	int py[x + 8];
	int pz[d];
	int pn[fl];
	int pb[shr];
	int pc[c];
	int pl[ln];
	int pq[m1 > 3];
	int pd[m1 + m2 * m3];
	int pe[pi];
	int pf[&a];
	
	int varr1[3.21];
	int varr2[3.14f];
	int varr3['m'];
	 
	int_arr1 = int_arr2;   // Problem!!
	int_arr1++;             // Problem!!
	int_arr2--;             // Problem!!
	
	pi = int_arr1;
	pi[2] = 56;        // Problem
	(pi + 2) = 56 + 87;  // Problem 
	*(pi + 2) = 23;    // Problem
	x = *(pi + 2);   // OK
	y = *(pi++);     // OK
	x = pi[3];       // Problem  
	pm = pi++;       // OK
	a = *pm;         // OK
	
	pi = int_arr2;   // OK
	pi++;            // OK
	a = *pi;         // OK
	
	foo(int_arr1);
	foo1(int_arr2);
	foo(int_arr1 + 3);
	foo1(int_arr2 + 4);
	foo1(&int_arr2[3]);
	
	foo2(&int_arr3[3]);

}