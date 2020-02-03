define i32 @foo(i32 %m) nounwind {
  %1 = sub i32 10, 5
  %2 = add i32 %1, 10
  %3 = icmp slt i32 %m, 100
  br label %4

; <label>:4          		; preds = %0
  %.0 = phi i32 [ 5, %0 ], [ %1, %0 ]
  ret i32 %.0
}