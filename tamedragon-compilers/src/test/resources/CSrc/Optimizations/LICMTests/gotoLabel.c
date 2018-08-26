int gotoLabel(int a,int b)
{
	int i,j,k=0;
 	if(a<b)
 	{
 	goto LabelA;
 	}
 	else
 	{
 	k++;
 	return 0;
 	//goto LabelA;
 	}
 	
 	LabelA:
 	{
 	do{
 		b = b+10;
 		j = a+50;
 	}while(b<20);
 	}
 	return a+j;
}