
define i32 @foo(i32 %val1, i32 %val2, i32 %val3) nounwind uwtable {

  %1 = add i32 %val1, 14
  %2 = sub i32 %1, %val1
  %3 = sdiv i32 %2, 7
  ret i32 %3
}
