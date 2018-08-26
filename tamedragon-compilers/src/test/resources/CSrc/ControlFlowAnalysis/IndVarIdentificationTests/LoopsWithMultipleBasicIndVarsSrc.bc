define i32 @foo(double %a, i32 %b, i32 %j) nounwind {
  br label %1

; <label>:1                         		; preds = %15, %0
  %result.0 = phi i32 [ %14, %15 ], [ 34, %0 ]
  %dotherincr.0 = phi double [ %8, %15 ], [ 2.300000e+00, %0 ]
  %dincr.0 = phi double [ %7, %15 ], [ 0.000000e+00, %0 ]
  %i.0 = phi i32 [ %16, %15 ], [ 0, %0 ]
  %2 = icmp slt i32 %i.0, %b
  br i1 %2, label %3, label %17

; <label>:3   		; preds = %1
  %4 = mul i32 %i.0, 42
  %5 = sub i32 %j, %4
  %6 = mul i32 30, %5
  %7 = fadd double %dincr.0, 1.200000e+01
  %8 = fadd double %dotherincr.0, 4.500000e+00
  %9 = fmul double %7, 6.700000e+00
  %10 = fsub double %9, %8
  %11 = fmul double 2.560000e+00, %10
  %12 = fptosi double %11 to i32
  %13 = add i32 %6, %12
  %14 = add i32 %result.0, %13
  br label %15

; <label>:15		; preds = %3
  %16 = add i32 %i.0, 3
  br label %1

; <label>:17		; preds = %1
  ret i32 %result.0
}