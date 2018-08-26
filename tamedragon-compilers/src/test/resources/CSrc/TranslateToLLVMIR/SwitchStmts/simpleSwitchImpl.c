void foo(){
	int a = 10;
	switch(a){
	case 1 : a++;break;
	case 2 : a--; break;
	case 3 : ++a; break;
	case 4 : --a; break;
	default : a = a - 1;
	}
}
