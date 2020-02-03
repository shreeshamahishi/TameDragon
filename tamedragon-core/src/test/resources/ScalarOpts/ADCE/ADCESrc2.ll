define i32 @bar(i32* %a, i32 %b) nounwind uwtable {
  store i32 10, i32* %a
  %1 = load i32, i32* %a
  %2 = mul i32 %1, 10
  %3 = add i32 30, %2
  ret i32 %b
}
