define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = icmp slt i32 %a, %b
  br label %2

; <label>:2		; preds = %0
  %3 = add i32 %a, 1
  br label %4

; <label>:4		; preds = %2
  ret i32 %b
}