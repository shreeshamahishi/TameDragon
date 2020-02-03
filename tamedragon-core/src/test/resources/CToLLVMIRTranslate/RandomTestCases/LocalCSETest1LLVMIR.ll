define i32 @foo(i32 %a, i32 %b, i32 %sum, i32 %sum2) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 %sum, i32* %3, align 4
  store i32 %sum2, i32* %4, align 4
  %6 = load i32, i32* %1, align 4
  %7 = load i32, i32* %2, align 4
  %8 = add i32 %6, %7
  store i32 %8, i32* %3, align 4
  %9 = load i32, i32* %1, align 4
  %10 = load i32, i32* %2, align 4
  %11 = add i32 %9, %10
  store i32 %11, i32* %4, align 4
  %12 = load i32, i32* %1, align 4
  %13 = load i32, i32* %2, align 4
  %14 = add i32 %12, %13
  store i32 %14, i32* %1, align 4
  %15 = load i32, i32* %3, align 4
  %16 = add i32 %15, 1
  store i32 %16, i32* %3, align 4
  %17 = load i32, i32* %4, align 4
  %18 = sub i32 %17, 1
  store i32 %18, i32* %4, align 4
  %19 = load i32, i32* %1, align 4
  %20 = load i32, i32* %2, align 4
  %21 = icmp sgt i32 %19, %20
  br i1 %21, label %22, label %24

; <label>:22      		; preds = %0
  %23 = load i32, i32* %4, align 4
  store i32 %23, i32* %5, align 4
  br label %26

; <label>:24      		; preds = %0
  %25 = load i32, i32* %3, align 4
  store i32 %25, i32* %5, align 4
  br label %26

; <label>:26		; preds = %22, %24
  %27 = load i32, i32* %5, align 4
  ret i32 %27
}
