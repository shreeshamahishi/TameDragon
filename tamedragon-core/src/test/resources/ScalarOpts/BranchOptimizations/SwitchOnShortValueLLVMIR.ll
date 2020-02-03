define void @foo() nounwind {
  %1 = sext i16 -32765 to i32
  switch i32 %1, label %10 [
    i32 10, label %2
    i32 20, label %4
    i32 30, label %6
    i32 40, label %8 
  ]

; <label>:2            		; preds = %2, %0
  %a.0 = phi i16 [ %3, %2 ], [ -32765, %0 ]
  %3 = add i16 %a.0, 1
  br label %12

; <label>:4		; preds = %2
  %5 = add i16 %3, 1
  br label %12

; <label>:6		; preds = %2
  %7 = add i16 %3, 1
  br label %12

; <label>:8		; preds = %2
  %9 = add i16 %3, 1
  br label %12

; <label>:10		; preds = %0
  %11 = add i16 -32765, 1
  br label %12

; <label>:12		; preds = %10, %8, %6, %4, %2
  ret void
}