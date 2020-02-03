define i32 @foo(i32 %m) nounwind {
  %1 = sub i32 10, 5
  %2 = sub i32 10, 5
  %3 = add i32 %1, 10
  %4 = add i32 10, %2
  %5 = icmp slt i32 %m, 100
  br i1 %5, label %6, label %7

; <label>:6		; preds = %0
  br label %8

; <label>:7		; preds = %0
  br label %8

; <label>:8       		; preds = %7, %6
  %.0 = phi i32 [ %2, %7 ], [ %1, %6 ]
  ret i32 %.0
}