int main(void) {
  int n = 5;
  int i;
  int flag;

  flag = 1;
  for (i=2; (i<(n/2)) && flag; ) { /* May be we do not need to test
			values of i greater than the square root of n? */
    if ((n % i) == 0) /* If true n is divisible by i */
      flag = 0;
    else
      i++;
  }

  return 0;
}
