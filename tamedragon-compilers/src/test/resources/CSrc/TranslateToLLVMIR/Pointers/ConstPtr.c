int bar(int max_val, int j, int addr)
{
	int var = 10, foo = 20;
	const int* ptr = &var;
//	*ptr = &foo;
	return *ptr;
}
