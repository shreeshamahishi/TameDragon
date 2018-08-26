
int MAX = 100;

int foo(int m, int n){
	
	int a, b, c, *p1, *p2, *p3;
	
	p1 = &a; p2 = &b; p3 = &c;
	
	int interim_result = 10;
	
	for(int i = 0; i < MAX; i++){
		*p1 = b + c;
		
		if(i < 75){
			*p2 += m;
			*p3 += n;
			p1 = p2;
			
			interim_result += *p1 + 3;
		}
		else{
			*p2 -= m;
			*p3 -= -n;
			p1 = p3;
			
			interim_result += *p1 + 3;
		}
		
		p2 = p3;
		
		interim_result += *p1 + *p2 + *p3;
		
	}
	
	p2 = p1;
	
	return interim_result + *p1 + *p2 + *p3;
	
}