int main(void) {
  int n;           /* The current exponent */
  int val = 1;     /* The current power of 2  */

  for (n=0; n<=16; n++) {
    val = 2*val;
  }
  return 0;
}
