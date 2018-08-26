union
{
	int id;
	char nm[10][10];
}tch = {10};


int main()
{
    tch.id = 1;
    tch.nm[0][0] = 'V';
    return 0;
}
