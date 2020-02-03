define i32 @foo(i32 %m, i32 %n) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  store i32 %n, i32* %2, align 4
  store i32 0, i32* %a, align 4
  store i32 23, i32* %b, align 4
  %3 = load i32* %a, align 4
  %4 = load i32* %b, align 4
  %5 = add i32 %3, %4
  ret i32 %5
}

