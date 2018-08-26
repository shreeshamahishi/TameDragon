define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = icmp slt i32 %a, %b
  br i1 %1, label %2, label %5

; <label>:2   		; preds = %0
  %3 = add i32 %a, 1
  %4 = add i32 %3, 2
  br label %CommonInstructions

; <label>:5   		; preds = %0
  %6 = add i32 %a, 1
  %7 = add i32 %6, 3
  br label %CommonInstructions

;CommonInstructions:		; preds = %2, %5
  %8 = add i32 %b, 1
  br label %9

; <label>:9		; preds = %CommonInstructions
  ret i32 %8
}