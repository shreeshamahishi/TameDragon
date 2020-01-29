define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 10, i32* %i, align 4
  %2 = load i32, i32* %i, align 4
  %3 = add i32 %2, 1
  store i32 %3, i32* %i, align 4
  store i32 20, i32* %j, align 4
  %4 = load i32, i32* %j, align 4
  %5 = add i32 %4, 1
  store i32 %5, i32* %j, align 4
  ret i32 0
}
