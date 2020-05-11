
define i8 @foo(i32 %val1, i32 %val2, i32 %val3) nounwind uwtable {

  %x01 = add i8 -9, 100
  %x02 = add i8 -9, -56
  %x03 = add i8 21, -12
  %x04 = add i8 -7, -6
  ret i8 %x04
}
