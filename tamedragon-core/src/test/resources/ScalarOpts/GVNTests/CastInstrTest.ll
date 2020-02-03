define i32 @foo(i32 %m) nounwind {
  %1 = sitofp i32 %m to double
  %2 = fadd double %1, 3.333000e+00
  %3 = fptrunc  double %2 to float
  %4 = fptosi float %3 to i32
  %5 = icmp slt i32 %m, 5
  br i1 %5, label %6, label %7

; <label>:6                                       ; preds = %0
  br label %8

; <label>:7                                       ; preds = %0
  br label %8

; <label>:8                                       ; preds = %7, %6
  ret i32 %4
}