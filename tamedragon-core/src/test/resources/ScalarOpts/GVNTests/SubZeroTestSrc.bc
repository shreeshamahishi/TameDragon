define i32 @subsZero(i32 %p, double %q) nounwind {
  %1 = icmp eq i32 10, 10
  br i1 %1, label %2, label %3

; <label>:2		; preds = %0
  br label %3

; <label>:3    		; preds = %2, %0
  %4 = sub i32 %p, 0
  %5 = fsub double %q, 0.000000e+00
  %6 = fsub double %5, 1.000000e-04
  ret i32 %4
}
