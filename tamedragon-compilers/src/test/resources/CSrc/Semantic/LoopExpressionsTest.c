
void foo(){
}

void main()
{
	int i, a = 20;
	int res;
	int j = 90;
	
	double d;

	char c[9];

	int *pt;



	for(d = 34.5; pt; res++){
		if(res > 45)
			break;
	}

	for(d = 34.5; d; foo()){
		if(res > 45)
			break;
	}

	for(d = 34.5; c; foo()){
		if(res > 45)
			break;
	}

	for(d = 34.5; foo(); d){
		if(res > 45)
			break;
	}

	for(foo(); c; d){
		if(res > 45)
			break;
	}

	while(foo()){
		res++;
	}

	while(d){
		break;
	}

	do{
		a = a + 2;
		res = a;
	}
	while(a);

	do{
		a = a + 2;
		res = a;
	}
	while(foo());
}