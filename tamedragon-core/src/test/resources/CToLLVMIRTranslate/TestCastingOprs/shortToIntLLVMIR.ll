define i32 @foo(i32 %a, double %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca double, align 8
  %s = alloca i16, align 2
  store i32 %a, i32* %1, align 4
  store double %b, double* %2, align 8
  store i16 10, i16* %s, align 2
  %3 = load i16, i16* %s, align 2
  %4 = sext i16 %3 to i32
  store i32 %4, i32* %1, align 4
  %5 = load i32, i32* %1, align 4
  ret i32 %5
}
