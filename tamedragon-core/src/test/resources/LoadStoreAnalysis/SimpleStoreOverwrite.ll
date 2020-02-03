define i32 @foo(i32 %m, i32 %n) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %x = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  store i32 %n, i32* %2, align 4
  store i32 34, i32* %x, align 4
  store i32 54, i32* %x, align 4
  %3 = load i32* %x, align 4
  %4 = load i32* %1, align 4
  %5 = add i32 %3, %4
  %6 = load i32* %2, align 4
  %7 = add i32 %5, %6
  ret i32 %7
}

