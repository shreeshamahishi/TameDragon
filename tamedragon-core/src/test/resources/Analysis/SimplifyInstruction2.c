
int foo(unsigned val1, unsigned val2, unsigned val3){
	unsigned q  = val1 + val2;
	unsigned r = q + val3;
	unsigned s = val2 + val3 ;
	unsigned t = val1 + s;
	unsigned v = r -t; 
	return v;
}