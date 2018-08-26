
#include <stdio.h>

int do_bin_ops(int m, int n)
{
	int result;
	
	int r1, r2, r3, r4,r5, r6, r7, r8, r9, r10,
			r11, r12, r13;
	int r14, r15, r16, r17;
	
	r1 = m + n;  // r1 = 36
	r2 = n - m;  // r2 = 12
	r3 = m * n;  // r3 = 288
	r4 = n / m;  // r4 = 2
	r5 = m % 5;  // r5 = 2
	r6 = m & 2;  // r6 = 0
	r7 = m | 2;  // r7 = 14
	r8 = m ^ 2;  // r8 = 14
	r9 = n << 1; // r9 = 48
	r10 = m >> 2;// r10 = 3
	r11 = ~ m;   // r11 = -13
	r12 = n++;   // r12 = 24
	r13 = ++m;   // r13 = 13
	
	r14 = r1 + r2 * r3 / r4 ^ n;  // r14 = 1789
	r15 = r5 && r6;      // r15 = 0
	r16 = r7 || r6;      // r16 = 1
	r17 = r14 + r15 + r16 + 210;   // r17 = 2000
	
	result = r17 + r7 - r8 + r9 + r10 + r11 + r12 + r13;  	 // result = 2075
	return result;
		
}

int main(int argc, char * argv[])
{
	int result;
	result = do_bin_ops(12, 24);
	printf("The result is %d\n", result);
	
	return 0;
}

