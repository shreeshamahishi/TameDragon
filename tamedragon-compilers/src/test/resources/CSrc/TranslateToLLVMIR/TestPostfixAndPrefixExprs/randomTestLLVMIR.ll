define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = load i32, i32* %1, align 4
  %4 = add i32 %3, 1
  store i32 %4, i32* %1, align 4
  %5 = load i32, i32* %2, align 4
  %6 = sub i32 %5, 1
  store i32 %6, i32* %2, align 4
  %7 = load i32, i32* %2, align 4
  %8 = add i32 %7, 1
  store i32 %8, i32* %2, align 4
  store i32 %7, i32* %1, align 4
  %9 = load i32, i32* %1, align 4
  %10 = add i32 %9, 1
  store i32 %10, i32* %1, align 4
  store i32 %10, i32* %2, align 4
  %11 = load i32, i32* %1, align 4
  %12 = add i32 %11, 1
  store i32 %12, i32* %1, align 4
  ret i32 %11
}
