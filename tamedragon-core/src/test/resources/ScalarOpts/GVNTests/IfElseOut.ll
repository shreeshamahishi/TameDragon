define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = icmp slt i32 %a, %b
  br i1 %1, label %2, label %4

; <label>:2		; preds = %0
  %3 = add i32 %a, 1
  br label %6

; <label>:4		; preds = %0
  %5 = add i32 %a, 1
  br label %6

; <label>:6		; preds = %4, %2
  ret i32 %b
}