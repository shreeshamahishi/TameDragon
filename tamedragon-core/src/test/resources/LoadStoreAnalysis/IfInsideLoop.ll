define i32 @bar(i32 %m, i32 %n, i32 %threshold, i32 %MAX) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  store i32 %n, i32* %2, align 4
  store i32 %threshold, i32* %3, align 4
  store i32 %MAX, i32* %4, align 4
  store i32 21, i32* %a, align 4
  store i32 0, i32* %b, align 4
  br label %5


; <label>:5		; preds = %0, %21
  %6 = load i32* %1, align 4
  %7 = load i32* %4, align 4
  %8 = icmp slt i32 %6, %7
  br i1 %8, label %9, label %27


; <label>:9      		; preds = %5
  %10 = load i32* %a, align 4
  %11 = add i32 %10, 1
  store i32 %11, i32* %a, align 4
  %12 = load i32* %1, align 4
  %13 = load i32* %3, align 4
  %14 = icmp slt i32 %12, %13
  br i1 %14, label %15, label %21


; <label>:15     		; preds = %9
  %16 = load i32* %a, align 4
  %17 = add i32 %16, 3
  store i32 %17, i32* %a, align 4
  %18 = load i32* %a, align 4
  %19 = load i32* %1, align 4
  %20 = add i32 %18, %19
  store i32 %20, i32* %b, align 4
  br label %21


; <label>:21		; preds = %9, %15
  %22 = load i32* %1, align 4
  %23 = add i32 %22, 1
  store i32 %23, i32* %1, align 4
  %24 = load i32* %b, align 4
  %25 = load i32* %a, align 4
  %26 = add i32 %24, %25
  store i32 %26, i32* %b, align 4
  br label %5


; <label>:27 		; preds = %5
  %28 = load i32* %a, align 4
  %29 = load i32* %b, align 4
  %30 = add i32 %28, %29
  ret i32 %30
}

