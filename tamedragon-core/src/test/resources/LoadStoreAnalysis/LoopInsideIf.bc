@MAX = external global i32


define i32 @bar(i32 %m, i32 %threshold) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  %result = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  store i32 %threshold, i32* %2, align 4
  store i32 23, i32* %a, align 4
  store i32 0, i32* %result, align 4
  %3 = load i32* %1, align 4
  %4 = load i32* %2, align 4
  %5 = icmp slt i32 %3, %4
  br i1 %5, label %6, label %20


; <label>:6    		; preds = %0
  %7 = load i32* %a, align 4
  %8 = add i32 %7, 46
  store i32 %8, i32* %a, align 4
  store i32 0, i32* %b, align 4
  br label %9


; <label>:9 		; preds = %6, %16
  %10 = load i32* %b, align 4
  %11 = load i32* @MAX, align 4
  %12 = icmp slt i32 %10, %11
  br i1 %12, label %13, label %19


; <label>:13     		; preds = %9
  %14 = load i32* %a, align 4
  %15 = add i32 %14, 2
  store i32 %15, i32* %a, align 4
  br label %16


; <label>:16    		; preds = %13
  %17 = load i32* %b, align 4
  %18 = add i32 %17, 1
  store i32 %18, i32* %b, align 4
  br label %9


; <label>:19		; preds = %9
  br label %22


; <label>:20     		; preds = %0
  %21 = load i32* %2, align 4
  store i32 %21, i32* %a, align 4
  br label %22


; <label>:22    		; preds = %19, %20
  %23 = load i32* %result, align 4
  %24 = load i32* %a, align 4
  %25 = add i32 %23, %24
  store i32 %25, i32* %result, align 4
  %26 = load i32* %result, align 4
  ret i32 %26
}

