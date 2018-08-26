
int bar(int max_val, int j, int addr)
{
	int index;
	int result = 0;
	int var1, var2, var3, var4, var5;
	for(index = 0; index < max_val; index++)
	{
		var1 = 100 * index;
		var2 = var1 + j;
		var3 = var2 - 101;
		var4 = 4 * var3;
		var5 = var4 + addr;
		
		result += var1 - var2 + var3 + var4 * var5;
		
	}
	
	return result;
}