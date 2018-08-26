#include <stdio.h>

int x = 2;
int y = 3;
int z = 4;
void moo(int x, int *y){
  int z;
  x = x+3;
  *y = *y+3;
  z = z+3;  /*Here z is the local z. Notice that it has not been
              initialized. As you see from the output below
              in this case it was implicitly initialized to 0.
              In general that is not the case and the compiler
	      should give you a warning
	      */
  printf("x = %d \n y = %d \n z = %d", x, y, z);
}
int main(void){
  moo(x, &y);
}
