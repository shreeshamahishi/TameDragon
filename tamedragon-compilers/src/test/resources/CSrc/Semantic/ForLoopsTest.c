
void main()
{
	int i, a = 20;
	int res;
	int j = 90;
	
	for(i = 0; i < a; i++)
	{
		int m, n;
		res = a + i;
	} 
	
	for(; j < 180; j++){
		res += j;
	}
	
	for(; ;)
	{
		res++;
		if(res > 1000)
			break;
	}
	
	for(i = 40; ; i++)
	{
		if(i < 80)
			break;
		else {
			continue;
		}
	}
	
	for(; ; res++)
	{
		if(res > 2000)
			break;
	}
	
	for(; a < 45; )
	{
		a--;
	}
	
	for(j = 40 ; ;)
	{
		j++;
		if(j > 567)
			break;
		else
			continue;
	}
	
	for(j = 0; j < 40; )
	{
		j = j + 1;
		res += j;
	}
	
	continue;
	if(a > 40)
		return 0;
	else
		return 1;
		
	break;
	
	if(res > 40)
		return res;
	else
		return 0;
	
}