
	struct xyz {
		int i;
		double d;
	};

void main()
{
	// Check various switch expressions

	struct xyz sx = {10, 32.4};

	double d = 32.4;
	int i = 3;
	short s = 2;
	long l = 34567;
	char c = 'd';

	switch(d)
	{
		int m = 0;
	case 2:
		m++;
	}

	switch(i)
	{
		int m = 0;
	case 2:
		m++;
	}

	switch(l)
	{
		int m = 0;
	case 2:
		m++;
	}

	switch(c)
	{
		int m = 0;
	case 2:
		m++;
	}

	switch(sx.i)
	{
		int m = 0;
	case 2:
		m++;
	}

	switch(&d)
	{
		int m = 0;
	case 2:
		m++;
	}

	switch(sx)
	{
		int m = 0;
	case 2:
		m++;
	}

	// Nested switch
	switch(sx.i)
	{
		int i = 0;
	case 2:
		i--;
		break;
	case 3:
		i++;
		switch(i){
			case 0:
				i++;
				break;
			case 1:
				i--;
				break;
		}
		break;
	}

}

// Case and default tags without a switch context
void foo(int flag)
{
	int m = 0;
	int k = 0;

	case 12:
		m++;
		k = 30;
		break;
	case 13:
		m--;
		k = 32;
		break;

	default:
		m--;

	switch(flag)
	{
	case 32 :
		m--;
		k = 34;
		break;

	case 34:
		m++;
		k = 21;
		break;

	default:
		m = 40;
		break;
	}
}

void bar()
{
	// Empty switch case
	int m = 40;
	double d = 45.6;
	struct st {
		int i;
		double d;
	} str1 = {3, 12.4};
	
	switch(m)
	{
		int m1 = 34;
		case 20:
			m1++;
		break;
		case d:
			m1--;
			break;
		case str1 :
			m++;
			break;
		case str1.i:
			m++;
			break;
		case str1.d:
			m1--;
			break;
		case &d :
			m++;
			break;
	}
}