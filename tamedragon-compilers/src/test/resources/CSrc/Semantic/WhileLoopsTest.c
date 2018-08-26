
void main()
{
	int i, a = 20;
	int res;
	int j = 90;
	
	while(i < 56)
	{
		int m, n;
		res = a + i;
		m = res;
		n = m -1;
		i++;
	} 
	
	do {
		if(res > 1000){
			break;
		}
		else{
			res++;
			continue;
		}
	}
	while(0);
	
	break;
	
	while(res)
	{
		if(res > 2000)
			break;
		res++;
	}
	
	continue;
	
	while(res < 20000)
	{
		i++; 
		res++;
	}

	while(0) {}
}