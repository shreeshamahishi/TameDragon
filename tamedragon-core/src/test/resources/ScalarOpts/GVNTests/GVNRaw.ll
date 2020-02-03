define i32 @foo(i32 %m) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca i32, align 4
  %e = alloca i32, align 4
  %f = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  store i32 10, i32* %a, align 4
  store i32 10, i32* %b, align 4
  %3 = load i32* %a, align 4
  %4 = sub i32 %3, 5
  store i32 %4, i32* %c, align 4
  %5 = load i32* %b, align 4
  %6 = sub i32 %5, 5
  store i32 %6, i32* %d, align 4
  %7 = load i32* %c, align 4
  %8 = add i32 %7, 1
  store i32 %8, i32* %c, align 4
  store i32 %7, i32* %e, align 4
  %9 = load i32* %d, align 4
  %10 = add i32 %9, 1
  store i32 %10, i32* %d, align 4
  store i32 %9, i32* %f, align 4
  %11 = load i32* %1, align 4
  %12 = icmp slt i32 %11, 100
  br i1 %12, label %13, label %15

; <label>:13     		; preds = %0
  %14 = load i32* %c, align 4
  store i32 %14, i32* %2, align 4
  br label %17

; <label>:15     		; preds = %0
  %16 = load i32* %d, align 4
  store i32 %16, i32* %2, align 4
  br label %17

; <label>:17		; preds = %15, %13
  %18 = load i32* %2, align 4
  ret i32 %18
}