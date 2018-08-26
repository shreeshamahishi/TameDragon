void foo(int a, int b){
	
	int c,d,e,i,f[10],g[10];
    
    c = a + b;
    d = a * c;
    e = d * d;
	
	for(i = 1; i < 10; i++){
		f[i] = a + b;
        c = c * 2;
    
        if(c > d){
		    g[i] = c * a;
        }
		else{
		   g[i] = d * d;
		}
    }
}
