
int bar(int max_val, int j, int addr)
{
	int index;
	int result = 0;
	for(index = 0; index < max_val; index++)
	{
		int var1 = 3 * index;
		int var2 = var1 + j;
		int var3 = var2 - 101;
		int var4 = 4 * var3;
		int var5 = var4 + addr;
		
		result += var1 - var2 + var3 + var4 * var5;
		
	}
	
	return result;
}