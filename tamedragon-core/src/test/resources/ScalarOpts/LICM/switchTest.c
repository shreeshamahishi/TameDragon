int switchTest(int a,int b,int c)
{
int i,j,k=0;
	switch( a )
     {
        case 1 : k++;
                   break;
        case 2 : k++;
                   break;
        case 3 : k++;
                   break;
        case 4 : k++;
                   break;
        default  : k++;
                   break;
     }
        do
        {
        j = a+b;
        i = j+2;
        }
        while(i<c);
        
      return j+k;
}