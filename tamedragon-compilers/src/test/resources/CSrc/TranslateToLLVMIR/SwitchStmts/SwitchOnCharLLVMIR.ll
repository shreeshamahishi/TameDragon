define void @foo() nounwind {
  %a = alloca i8, align 1
  store i8 97, i8* %a, align 1
  %1 = load i8, i8* %a, align 1
  %2 = sext i8 %1 to i32
  switch i32 %2, label %7 [
    i32 97, label %3
    i32 98, label %4
    i32 99, label %5
    i32 100, label %6 
  ]

; <label>:3   		; preds = %0
  store i8 65, i8* %a, align 1
  br label %8

; <label>:4   		; preds = %0
  store i8 66, i8* %a, align 1
  br label %8

; <label>:5   		; preds = %0
  store i8 67, i8* %a, align 1
  br label %8

; <label>:6   		; preds = %0
  store i8 68, i8* %a, align 1
  br label %8

; <label>:7   		; preds = %0
  store i8 90, i8* %a, align 1
  br label %8

; <label>:8		; preds = %3, %4, %5, %6, %7
  ret void
}
