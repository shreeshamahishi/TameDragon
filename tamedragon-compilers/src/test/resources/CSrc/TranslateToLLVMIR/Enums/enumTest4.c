enum DAY{
  saturday,
  sunday = 0,
  monday,
  tuesday,
  wednesday,
  thrusday,
  friday
}weekdays;

int main(){
   enum DAY today = wednesday;
   int day_value = 3;
   weekdays = ( enum DAY ) ( day_value - 1 );
}