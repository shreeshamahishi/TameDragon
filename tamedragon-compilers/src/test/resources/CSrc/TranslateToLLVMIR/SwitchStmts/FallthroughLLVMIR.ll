define void @foo() nounwind {
  %a = alloca i32, align 4
  store i32 10, i32* %a, align 4
  %1 = load i32, i32* %a, align 4
  switch i32 %1, label %8 [
    i32 0, label %2
    i32 1, label %5 
  ]

; <label>:2      		; preds = %0
  %3 = load i32, i32* %a, align 4
  %4 = add i32 %3, 1
  store i32 %4, i32* %a, align 4
  br label %5

; <label>:5  		; preds = %0, %2
  %6 = load i32, i32* %a, align 4
  %7 = add i32 %6, 1
  store i32 %7, i32* %a, align 4
  br label %8

; <label>:8		; preds = %0, %5
  ret void
}
