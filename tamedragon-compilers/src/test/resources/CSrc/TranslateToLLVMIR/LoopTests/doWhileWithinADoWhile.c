int foo(int a, int b){
	int i = 0, k = 0;
	do{
		if(i < 2){
			break;
		}
		else{
			do{
				k += 1;
			}while(k < 10);
		}
		i += 1;
	}while(i < 10);
	return k;
}
