
int* foo(int p, double *pd)
{
	int arr[2];
	int m = 0;
	
	m = (int) (*pd);
	arr[0] = m *2;
	arr[1] = 2;
	
	return arr;
}

int*  foo1(int i, char *pc)
{
	int arr[2];
	int m = 0;
	
	m = *pc;
	arr[0] = m *2;
	arr[1] = 2;
	
	return arr;
}

int* bar()
{
	int m = 20, *pm;
	pm = &m;
	return pm;
}

void* foobar()
{
	char c;
	return &c;
}

void main()
{
	int m, n, *pn, *pm, *px, *py;
	int arr[40];
	int x = 0;
	double *pd, d, *pd2;
	
	for(x = 0; x < 40; x++){
		arr[x] = x;
	}
	
	pn = arr;
	px = arr;
	pm = arr;

	pn += 20;
	px += 25;
	pm += 45;
	
	d = 3.141;
	pd = &d;
	n = m[45];
	px = n;
	px++;
	px--;
	
	px += 3;
	py -= 2;
	
	pd *= 34;
	
	px = pn * pm;
	pm = pn / px;
	px = pn *2;
	px = pm / 4;
	pn = pm + 3.45;
	pn = px * 45;
	
	py = pn + 4;
	
	pn = pn % 5;
	px = pm % pn;
	pm = pn * 4.76;
	px = px - 3.2f;
	
	n = px - py;   // OK
	n = py - pm + 1;
	
	m = pd - pd2;   // OK?
	n = pn - pd; // Different types
	
	pd = px;
	pn = pm;
	
	pm = pd2;
	
	
	pn = 8;
	py += 4.28;
	px = 9.7;
	pn = 0; // OK
	
	if(pn > px){
		m++;
	}
	else{
		m--;
	}
	
	n = pn < py;
	m = pn != px;
	n = pm == px;
	
	d = pn <= px;
	m = py >= pn;
	
	if(pn == 5)
		return;
		
	if(pn == pd)
		n--;
	
	if(py != x * 8)
		m += 87;
		
	
	if(pn == 0)
		m++;
		
	pn = foobar();

}

