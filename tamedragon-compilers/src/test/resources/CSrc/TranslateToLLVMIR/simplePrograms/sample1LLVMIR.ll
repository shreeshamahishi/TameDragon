define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 20, i32* %c, align 4
  store i32 40, i32* %d, align 4
  %3 = load i32, i32* %1, align 4
  %4 = load i32, i32* %d, align 4
  %5 = add i32 %3, %4
  store i32 %5, i32* %c, align 4
  %6 = load i32, i32* %c, align 4
  %7 = load i32, i32* %2, align 4
  %8 = add i32 %6, %7
  store i32 %8, i32* %d, align 4
  %9 = load i32, i32* %c, align 4
  %10 = load i32, i32* %d, align 4
  %11 = add i32 %9, %10
  ret i32 %11
}
