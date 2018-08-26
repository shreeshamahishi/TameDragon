define void @foo() nounwind {
  %1 = sext i8 97 to i32
  switch i32 %1, label %6 [
    i32 97, label %2
    i32 98, label %3
    i32 99, label %4
    i32 100, label %5 
  ]

; <label>:2		; preds = %2, %0
  br label %7

; <label>:3		; preds = %2
  br label %7

; <label>:4		; preds = %2
  br label %7

; <label>:5		; preds = %2
  br label %7

; <label>:6		; preds = %0
  br label %7

; <label>:7		; preds = %6, %5, %4, %3, %2
  ret void
}