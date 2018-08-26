void foo(int a, int b){
	int i = 0;
	for(; i < 10; ){
		if(i == 2)
			continue;
		else if(i == 1)
			break;
		if(i == 6)
			return ;
		i = i + 1;
	}
	return ;
}
