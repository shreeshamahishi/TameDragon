define void @foo() nounwind {
  %a = alloca i32, align 4
  store i32 10, i32* %a, align 4
  %1 = load i32, i32* %a, align 4
  switch i32 %1, label %14 [
    i32 1, label %2
    i32 2, label %5
    i32 3, label %8
    i32 4, label %11 
  ]

; <label>:2      		; preds = %0
  %3 = load i32, i32* %a, align 4
  %4 = add i32 %3, 1
  store i32 %4, i32* %a, align 4
  br label %17

; <label>:5      		; preds = %0
  %6 = load i32, i32* %a, align 4
  %7 = sub i32 %6, 1
  store i32 %7, i32* %a, align 4
  br label %17

; <label>:8      		; preds = %0
  %9 = load i32, i32* %a, align 4
  %10 = add i32 %9, 1
  store i32 %10, i32* %a, align 4
  br label %17

; <label>:11      		; preds = %0
  %12 = load i32, i32* %a, align 4
  %13 = sub i32 %12, 1
  store i32 %13, i32* %a, align 4
  br label %17

; <label>:14      		; preds = %0
  %15 = load i32, i32* %a, align 4
  %16 = sub i32 %15, 1
  store i32 %16, i32* %a, align 4
  br label %17

; <label>:17		; preds = %2, %5, %8, %11, %14
  ret void
}
