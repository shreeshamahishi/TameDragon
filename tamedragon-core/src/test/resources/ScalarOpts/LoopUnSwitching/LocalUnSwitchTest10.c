void foo(int p1, int flag){
    int i, j;
	while(i < 10){
		if(flag)
		   p1++;
		for(j = 0; j < 10; j++){
			if(flag < 6)
			   p1++;
		}		
		i++;
	}
}