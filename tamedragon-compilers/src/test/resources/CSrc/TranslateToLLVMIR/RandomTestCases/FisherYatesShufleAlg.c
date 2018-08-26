#define RAND_MAXIMUM 10
#include<stdio.h>
#include<stdlib.h>

int main(){
	int arr[10] = {1,2,3,4,5,6,7,8,9,10};
	int i = 0;
	for(i = 0; i < 10; i++)
		printf("%d,", arr[i]);
}
int random_int(int n) {
  int limit = RAND_MAXIMUM - RAND_MAXIMUM % n;
  int rnd;

  do {
    rnd = rand();
  } while (rnd >= limit);
  return rnd % n;
}
void shuffle(int *array, int n) {
  int i, j, tmp;

  for (i = n - 1; i > 0; i--) {
    j = random_int(i + 1);
    tmp = array[j];
    array[j] = array[i];
    array[i] = tmp;
  }
}

