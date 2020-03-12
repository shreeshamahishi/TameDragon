
define i32 @foo(i32 %val1, i32 %val2, i32 %val3) nounwind uwtable {

  %x = add i32 %val1, %val2
  %x1 = add i32 %x, %val3
  %x2 = add i32 %val2, %val3
  %x3 = add i32 %val1, %x2
  %s = sub i32 %x1, %x3
  ret i32 %s
}
