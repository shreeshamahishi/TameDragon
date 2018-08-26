void foo(int p1, int p2, int p3, int p4, int flag){
    int i;
	for(i = 0; i < 10; i++){
	     p1++;
	     
	     if(flag){
	     	for(int j = 0; j < 10; j++){
	     		 if(flag)
	     			p2++;
	     	}
	     }
	     p3++;
	}
}