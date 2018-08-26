main()
{
	int c, first, last, middle, n, search, array[100];

	n = 10;
	
	array[0]=0;
	array[1]=1;
	array[2]=2;
	array[3]=3;
	array[4]=4;
	array[5]=5;
	array[6]=6;
	array[7]=7;
	array[8]=8;
	array[9]=9;

	search = 6;

	first = 0;
	last = n - 1;
	middle = (first+last)/2;

	while( first <= last )
	{
		if ( array[middle] < search )
			first = middle + 1;
		else if ( array[middle] == search )
		{
			break;
		}
		else
			last = middle - 1;

		middle = (first + last)/2;
	}

	return 0;
}
