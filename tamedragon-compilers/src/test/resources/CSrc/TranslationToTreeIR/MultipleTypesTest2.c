
#include <stdio.h>

void main()
{
	char c = 'c';
	short int si = 23;
	int i = 345, max1 = 3, index;		
	signed int i2 = 332;
	unsigned int i3 = 443;
	long int li = 9856789;
	float f = 8.7f;
	double d = 4563.2345;

	for(index = 0; index < max1; index++){
		c++;
		si +=5;
		i -= 2;
		i2 *= 2;
		f += 3;	
		d *= 2;	
	}
	
	printf("%c\n", c);
	printf("%d\n", si);
	printf("%d\n", i);
	printf("%d\n", i2);
	printf("%d\n", i3);
	printf("%d\n", li);
	printf("%f\n", f);
	printf("%f\n", d);

}