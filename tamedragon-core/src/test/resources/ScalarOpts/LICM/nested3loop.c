int nestes3loop(int a,int b,int c)
{
int x=0, y=0, z=0;
	for(;;)
	{
		do
		{
			do
			{
			y = b + 50;
			z = c + 50;
			x = a + 50;
			}while(c<15);
		 b++;
		}while(b<10);
	
	if(a<5)
	break;
	a++;	
	} 
	return x+y+z;
}