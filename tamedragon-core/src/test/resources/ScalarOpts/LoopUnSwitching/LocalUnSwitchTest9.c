void foo(int p1, int flag){
    int i;
	while(i < 10){
		if(flag)
		   p1++;
		else if(flag < 2)
		   p1--;
		else
		   p1 += 2;
		
		i++;
	}
}