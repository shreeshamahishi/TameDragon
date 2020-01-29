define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %f = alloca double, align 8
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store double 1.090000e+01, double* %f, align 8
  %3 = load double, double* %f, align 8
  %4 = fsub double %3, 1.000000e+00
  store double %4, double* %f, align 8
  %5 = load i32, i32* %1, align 4
  ret i32 %5
}
