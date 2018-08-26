define i32 @func(i32 %m, i32 %n, i32 %threshold, i32 %MAX) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  %i = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  store i32 %n, i32* %2, align 4
  store i32 %threshold, i32* %3, align 4
  store i32 %MAX, i32* %4, align 4
  store i32 0, i32* %a, align 4
  store i32 0, i32* %b, align 4
  %5 = load i32* %1, align 4
  %6 = load i32* %3, align 4
  %7 = icmp slt i32 %5, %6
  br i1 %7, label %8, label %43


; <label>:8    		; preds = %0
  %9 = load i32* %1, align 4
  store i32 %9, i32* %a, align 4
  store i32 0, i32* %i, align 4
  br label %10


; <label>:10		; preds = %8, %39
  %11 = load i32* %i, align 4
  %12 = load i32* %2, align 4
  %13 = icmp slt i32 %11, %12
  br i1 %13, label %14, label %42


; <label>:14    		; preds = %10
  %15 = load i32* %a, align 4
  %16 = add i32 %15, 1
  store i32 %16, i32* %a, align 4
  %17 = load i32* %a, align 4
  %18 = load i32* %4, align 4
  %19 = icmp slt i32 %17, %18
  br i1 %19, label %20, label %35


; <label>:20    		; preds = %14
  %21 = load i32* %3, align 4
  %22 = sub i32 %21, 1
  %23 = load i32* %a, align 4
  %24 = add i32 %23, %22
  store i32 %24, i32* %a, align 4
  br label %25


; <label>:25		; preds = %20, %29
  %26 = load i32* %b, align 4
  %27 = load i32* %4, align 4
  %28 = icmp slt i32 %26, %27
  br i1 %28, label %29, label %34


; <label>:29    		; preds = %25
  %30 = load i32* %a, align 4
  %31 = add i32 %30, 1
  store i32 %31, i32* %a, align 4
  %32 = load i32* %b, align 4
  %33 = add i32 %32, 1
  store i32 %33, i32* %b, align 4
  br label %25


; <label>:34		; preds = %25
  br label %38


; <label>:35    		; preds = %14
  %36 = load i32* %a, align 4
  %37 = add i32 %36, 4
  store i32 %37, i32* %a, align 4
  br label %38


; <label>:38		; preds = %34, %35
  br label %39


; <label>:39    		; preds = %38
  %40 = load i32* %i, align 4
  %41 = add i32 %40, 1
  store i32 %41, i32* %i, align 4
  br label %10


; <label>:42		; preds = %10
  br label %44


; <label>:43     		; preds = %0
  store i32 100, i32* %a, align 4
  br label %44


; <label>:44		; preds = %42, %43
  %45 = load i32* %a, align 4
  %46 = mul i32 %45, 2
  store i32 %46, i32* %a, align 4
  %47 = load i32* %a, align 4
  ret i32 %47
}

