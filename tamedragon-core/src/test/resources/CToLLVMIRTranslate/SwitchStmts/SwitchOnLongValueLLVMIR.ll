define void @foo() nounwind {
  %a = alloca i64, align 8
  store i64 32771, i64* %a, align 8
  %1 = load i64, i64* %a, align 8
  switch i64 %1, label %14 [
    i64 10, label %2
    i64 20, label %5
    i64 30, label %8
    i64 40, label %11 
  ]

; <label>:2      		; preds = %0
  %3 = load i64, i64* %a, align 8
  %4 = add i64 %3, 1
  store i64 %4, i64* %a, align 8
  br label %17

; <label>:5      		; preds = %0
  %6 = load i64, i64* %a, align 8
  %7 = add i64 %6, 1
  store i64 %7, i64* %a, align 8
  br label %17

; <label>:8      		; preds = %0
  %9 = load i64, i64* %a, align 8
  %10 = add i64 %9, 1
  store i64 %10, i64* %a, align 8
  br label %17

; <label>:11      		; preds = %0
  %12 = load i64, i64* %a, align 8
  %13 = add i64 %12, 1
  store i64 %13, i64* %a, align 8
  br label %17

; <label>:14      		; preds = %0
  %15 = load i64, i64* %a, align 8
  %16 = add i64 %15, 1
  store i64 %16, i64* %a, align 8
  br label %17

; <label>:17		; preds = %2, %5, %8, %11, %14
  ret void
}
