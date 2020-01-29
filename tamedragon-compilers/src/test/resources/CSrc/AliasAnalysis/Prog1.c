
typedef struct {int i; char c;} st;

int Prog1(unsigned int a) {
	st p;
	st q;
	
	p = {5, 'a'}; 
	q = {10, 'b'};
	
	if(a > 50) {
		p = q;		
	}
	
	p.i = 4;
	return q.i;	
}