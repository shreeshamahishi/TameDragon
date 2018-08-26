define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = icmp slt i32 %a, %b
  br label %2

; <label>:2		; preds = %0
  %3 = add i32 %a, 1
  %4 = add i32 %b, 1
  br label %5

; <label>:5		; preds = %2
  ret i32 %4
}