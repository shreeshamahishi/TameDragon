
void foo()
{
	int i;
	char C[2];
	char D[2];
	char A[10];

	for (i = 0; i != 10; ++i) {
	  C[0] = A[i];          /* One byte store */
	  C[1] = A[9-i];        /* One byte store */
	}
	
	for (i = 0; i != 10; ++i) {
  		((short*)D)[0] = A[i];  /* Two byte store! */
  		D[1] = A[9-i];          /* One byte store */
	}
}