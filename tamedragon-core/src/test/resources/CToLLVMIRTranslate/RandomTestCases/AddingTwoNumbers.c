#include <stdio.h>

int main(void) {
  int first, second;

  printf("Enter two integers > ");
  scanf("%d %d", &first, &second);
  printf("The two numbers are: %d  %d\n", first, second);
  printf("Their sum is %d\n", first+second);
}
