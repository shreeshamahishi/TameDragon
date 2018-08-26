define i32 @foo(i32 %m) nounwind {
  %1 = alloca i32, align 4
  %i = alloca i32, align 4
  %x = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  store i32 10, i32* %i, align 4
  %2 = load i32* %1, align 4
  %3 = load i32* %i, align 4
  %4 = add i32 %2, %3
  store i32 %4, i32* %x, align 4
  %5 = load i32* %r, align 4
  ret i32 %5
}