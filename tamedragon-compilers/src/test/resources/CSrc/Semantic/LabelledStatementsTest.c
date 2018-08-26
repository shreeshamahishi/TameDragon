
void main()
{
	int x= 0;
	int m = 2;

	xyz:

	if(x > 1)
		goto xyz;
	else
		continue;
	
	xyz: 
	for(m = 0; m < 10; m++){

	 }

	 {
		 int m1 = 34;
		abc: 
		 m1 += 2;

		 if(m1 < 36)
			 goto abc;
		 else
			 goto xyz1;

		xyz:
		 m1 = 36;

		 goto unknown;

	 }

	 if(m == 20){
		 m1 = 34;
		 goto abc;
	 }
}