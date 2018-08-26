int foo(int a, int b){
	int i = 0;
	while(i < 10){
		if(i == 2){
			i += 1;
		}
		else{
			break;
		}
	}
	return i;
}
