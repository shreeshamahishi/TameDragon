define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = add i32 2, 23
  %2 = icmp slt i32 %b, %a
  br i1 %2, label %3, label %5

; <label>:3		; preds = %0
  %4 = sub i32 %b, 1
  br label %5

; <label>:5		; preds = %3, %0
  ret i32 %b
}