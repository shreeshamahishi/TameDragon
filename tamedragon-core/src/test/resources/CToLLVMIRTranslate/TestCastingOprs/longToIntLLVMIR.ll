define i32 @foo(i32 %a, double %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca double, align 8
  %l = alloca i64, align 8
  store i32 %a, i32* %1, align 4
  store double %b, double* %2, align 8
  store i64 10, i64* %l, align 8
  %3 = load i64, i64* %l, align 8
  %4 = trunc i64 %3 to i32
  store i32 %4, i32* %1, align 4
  %5 = load i32, i32* %1, align 4
  ret i32 %5
}
