define void @foo() nounwind {
  %a = alloca i16, align 2
  store i16 -32765, i16* %a, align 2
  %1 = load i16, i16* %a, align 2
  %2 = sext i16 %1 to i32
  switch i32 %2, label %15 [
    i32 10, label %3
    i32 20, label %6
    i32 30, label %9
    i32 40, label %12 
  ]

; <label>:3      		; preds = %0
  %4 = load i16, i16* %a, align 2
  %5 = add i16 %4, 1
  store i16 %5, i16* %a, align 2
  br label %18

; <label>:6      		; preds = %0
  %7 = load i16, i16* %a, align 2
  %8 = add i16 %7, 1
  store i16 %8, i16* %a, align 2
  br label %18

; <label>:9       		; preds = %0
  %10 = load i16, i16* %a, align 2
  %11 = add i16 %10, 1
  store i16 %11, i16* %a, align 2
  br label %18

; <label>:12      		; preds = %0
  %13 = load i16, i16* %a, align 2
  %14 = add i16 %13, 1
  store i16 %14, i16* %a, align 2
  br label %18

; <label>:15      		; preds = %0
  %16 = load i16, i16* %a, align 2
  %17 = add i16 %16, 1
  store i16 %17, i16* %a, align 2
  br label %18

; <label>:18		; preds = %3, %6, %9, %12, %15
  ret void
}
