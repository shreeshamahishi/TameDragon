define i32 @foo(i32 %a, i32 %b) nounwind {
  br label %1

; <label>:1		; preds = %3, %0
  %2 = icmp slt i32 0, 10
  br i1 %2, label %3, label %5

; <label>:3   		; preds = %1
  %4 = icmp slt i32 0, 2
  br i1 %4, label %5, label %1

; <label>:5		; preds = %3, %1
  ret i32 0
}