define i32 @foo(i32 %a, double %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca double, align 8
  %f = alloca float, align 4
  store i32 %a, i32* %1, align 4
  store double %b, double* %2, align 8
  %3 = load i32, i32* %1, align 4
  %4 = sitofp i32 %3 to float
  store float %4, float* %f, align 4
  %5 = load i32, i32* %1, align 4
  ret i32 %5
}
