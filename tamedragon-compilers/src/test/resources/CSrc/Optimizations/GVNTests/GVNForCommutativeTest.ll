define i32 @foo(i32 %m) nounwind {
  %1 = sub i32 10, 5
  %2 = add i32 %1, 10
  %3 = icmp slt i32 %m, 100
  br i1 %3, label %4, label %5

; <label>:4		; preds = %0
  br label %6

; <label>:5		; preds = %0
  br label %6

; <label>:6      		; preds = %4, %5
  %.0 = phi i32 [ %1, %4 ], [ 5, %5 ]
  ret i32 %.0
}
