define double @foo(i32 %a, double %passed_dbl) nounwind {
  %1 = add i32 23, %a
  br label %2

; <label>:2                		; preds = %0, %12
  %d.0 = phi double [ undef, %0 ], [ %d.1, %12 ]
  %c.0 = phi i32 [ %1, %0 ], [ %c.1, %12 ]
  %b.0 = phi i32 [ 12, %0 ], [ %13, %12 ]
  %3 = icmp slt i32 %b.0, %a
  br i1 %3, label %4, label %14

; <label>:4         		; preds = %2
  %5 = fadd double %d.0, %passed_dbl
  %6 = icmp slt i32 %c.0, %b.0
  br i1 %6, label %7, label %10

; <label>:7       		; preds = %4
  %8 = fsub double %5, %passed_dbl
  %9 = add i32 %c.0, 1
  br label %12

; <label>:10       		; preds = %4
  %11 = fadd double %5, %passed_dbl
  br label %12

; <label>:12           		; preds = %7, %10
  %d.1 = phi double [ %8, %7 ], [ %11, %10 ]
  %c.1 = phi i32 [ %9, %7 ], [ %c.0, %10 ]
  %13 = sub i32 %b.0, 1
  br label %2

; <label>:14     		; preds = %2
  %15 = sitofp i32 %c.0 to double
  %16 = fadd double %d.0, %15
  ret double %16
}
