
int* foo(double d, char* c)
{
	return *c;
}

void strcpy1(char *s, char *t)
{
	while((*s++ = *t++) != '\0')
	;
}

void strcpy2(char *s, char *t)
{
	while((*s++ = *t++) != 0)
	;
}

void strcpy3(char *s, char *t)
{
	int i = 0;
	while(s[i] = t[i] != 0)
	{
		i++;
	};
}

void main()
{
	int arr[];
	char carr[];

	char amessage1[] = "Now is the time";
	char amessage2[50] = " for all good men";
	char amessage3[2] = " to come together ";
	char amessage4[] = " for the good of the country.";
	
	char *pmsg1 = amessage1;
	char *pmsg4 = amessage4;
	
	foo(0.5, pmsg1);
	foo(3.1, amessage1);
	
	strcpy1(pmsg1, pmsg4);
	strcpy3(amessage2, "for all not so good men");
	
	amessage1++;   // PROBLEM
	
}