define i32 @foo(i32 %a, double %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca double, align 8
  %l = alloca i64, align 8
  store i32 %a, i32* %1, align 4
  store double %b, double* %2, align 8
  %3 = load i32, i32* %1, align 4
  %4 = sext i32 %3 to i64
  store i64 %4, i64* %l, align 8
  %5 = load i32, i32* %1, align 4
  ret i32 %5
}
