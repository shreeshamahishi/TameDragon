define i32 @foo(i32 %a, double %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca double, align 8
  %f = alloca float, align 4
  store i32 %a, i32* %1, align 4
  store double %b, double* %2, align 8
  store float 0x4024000000000000, float* %f, align 4
  %3 = load float, float* %f, align 4
  %4 = fpext float %3 to double
  store double %4, double* %2, align 8
  %5 = load i32, i32* %1, align 4
  ret i32 %5
}
