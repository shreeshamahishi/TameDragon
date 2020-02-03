define i32 @foo(i32 %m) nounwind {
  %1 = sitofp i32 %m to double
  %2 = fadd double %1, 3.333000e+00
  %3 = fptrunc  double %2 to float
  %4 = sitofp i32 %m to double
  %5 = fadd double %4, 3.333000e+00
  %6 = fptrunc  double %5 to float
  %7 = fptosi float %3 to i32
  %8 = fptosi float %6 to i32
  %9 = icmp slt i32 %m, 5
  br i1 %9, label %10, label %11

; <label>:10		; preds = %0
  br label %12

; <label>:11		; preds = %0
  br label %12

; <label>:12      		; preds = %11, %10
  %.0 = phi i32 [ %8, %11 ], [ %7, %10 ]
  ret i32 %.0
}