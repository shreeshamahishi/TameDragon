define i32 @func(i32 %m, i32 %threshold, i32 %MAX) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %a = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  store i32 %threshold, i32* %2, align 4
  store i32 %MAX, i32* %3, align 4
  store i32 43, i32* %a, align 4
  %4 = load i32* %1, align 4
  %5 = load i32* %2, align 4
  %6 = icmp slt i32 %4, %5
  br i1 %6, label %7, label %18


; <label>:7     		; preds = %0
  %8 = load i32* %a, align 4
  %9 = add i32 %8, 1
  store i32 %9, i32* %a, align 4
  br label %10


; <label>:10		; preds = %7, %14
  %11 = load i32* %1, align 4
  %12 = load i32* %3, align 4
  %13 = icmp slt i32 %11, %12
  br i1 %13, label %14, label %17


; <label>:14    		; preds = %10
  %15 = load i32* %a, align 4
  %16 = add i32 %15, 3
  store i32 %16, i32* %a, align 4
  br label %10


; <label>:17		; preds = %10
  br label %29


; <label>:18     		; preds = %0
  %19 = load i32* %a, align 4
  %20 = sub i32 %19, 1
  store i32 %20, i32* %a, align 4
  br label %21


; <label>:21		; preds = %18, %25
  %22 = load i32* %1, align 4
  %23 = load i32* %2, align 4
  %24 = icmp slt i32 %22, %23
  br i1 %24, label %25, label %28


; <label>:25    		; preds = %21
  %26 = load i32* %a, align 4
  %27 = add i32 %26, 2
  store i32 %27, i32* %a, align 4
  br label %21


; <label>:28		; preds = %21
  br label %29


; <label>:29		; preds = %17, %28
  %30 = load i32* %a, align 4
  %31 = add i32 %30, 4
  store i32 %31, i32* %a, align 4
  %32 = load i32* %a, align 4
  ret i32 %32
}

