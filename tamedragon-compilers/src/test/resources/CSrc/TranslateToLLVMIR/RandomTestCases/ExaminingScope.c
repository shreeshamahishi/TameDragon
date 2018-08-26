int a=0;    /* This is a global variable */

void foo(void);

int main(void) {
  int a=2;  /* This is a variable local to main */
  int b=3;  /* This is a variable local to main */

  foo();
}

void foo(void){
  int b=4;  /* This is a variable local to foo */
}
