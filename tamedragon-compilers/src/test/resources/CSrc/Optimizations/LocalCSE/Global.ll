define i32 @foo(i32 %flag) nounwind {
  %1 = load i32, i32* @a, align 4
  %2 = add i32 %1, 1
  %3 = icmp ne i32 %flag, 0
  br i1 %3, label %4, label %5

; <label>:4		; preds = %0
  br label %6

; <label>:5		; preds = %0
  br label %6

; <label>:6       		; preds = %4, %5
  %.0 = phi i32 [ %2, %4 ], [ %2, %5 ]
  ret i32 %.0
}
