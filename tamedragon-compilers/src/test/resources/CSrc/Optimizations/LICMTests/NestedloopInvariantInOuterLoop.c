int nestLoopInvOuter(int a, int b)
{
	int x,y = 0,z;
	do
	{
		x += a+10;
		do{
			y++;
			}
			while(y<20);
	}
while(b < 20);
	return x + y;
	}