int foo(int a, int b){
	int i = 0, k = 0;
	while(i < 10){
		if(i < 2){
			break;
		}
		else{
			while(k < 10){
				k += 1;
			}
		}
		i += 1;
	}
	return k;
}
