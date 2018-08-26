#include <stdio.h>
enum DAY{
  saturday,
  sunday = 0,
  monday,
  tuesday,
  wednesday,
  thrusday,
  friday
};


int main(){
  enum DAY today = tuesday;
  printf("%d\n", today);
  return 0;
}
