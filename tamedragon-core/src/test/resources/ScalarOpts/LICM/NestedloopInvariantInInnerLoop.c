int nestLoop(int a, int b)
{
	int x,y = 0,z;
	do
	{
		x = a+10;
		do{
			x+=a+10;
			//y = a+10;
			}
			while(a<20);
	a++;
	}
while(b < 20);
	return x + y;
	}