
int foo(unsigned max, unsigned min, int val){
	unsigned m = 40;
	unsigned n = 45;
	unsigned p = m + n;
	unsigned q = max - m;
	unsigned r = m + q; 
	return r + val;
}