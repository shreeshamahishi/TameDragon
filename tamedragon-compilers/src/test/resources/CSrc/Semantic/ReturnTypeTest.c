
// void function returning a value must result in a warning
void getError(int m);
int setMax(int m, int n);

void getError(int m){

	m = m = 3;
	return m;
}

int setMax(int m, int n) {
	
	int ret = m;
	if(m < n)
		ret = n;
	
	return ;
}