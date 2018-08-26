define i32 @foo(i32 %m, i32 %n, i32 %MAX1, i32 %MAX2) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %a = alloca i32, align 4
  %result = alloca i32, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  store i32 %n, i32* %2, align 4
  store i32 %MAX1, i32* %3, align 4
  store i32 %MAX2, i32* %4, align 4
  store i32 12, i32* %a, align 4
  store i32 0, i32* %i, align 4
  br label %5


; <label>:5		; preds = %0, %26
  %6 = load i32* %i, align 4
  %7 = load i32* %3, align 4
  %8 = icmp slt i32 %6, %7
  br i1 %8, label %9, label %29


; <label>:9    		; preds = %5
  %10 = load i32* %a, align 4
  %11 = add i32 %10, 1
  store i32 %11, i32* %a, align 4
  store i32 0, i32* %j, align 4
  br label %12


; <label>:12		; preds = %9, %19
  %13 = load i32* %i, align 4
  %14 = load i32* %4, align 4
  %15 = icmp slt i32 %13, %14
  br i1 %15, label %16, label %22


; <label>:16    		; preds = %12
  %17 = load i32* %a, align 4
  %18 = add i32 %17, 3
  store i32 %18, i32* %a, align 4
  br label %19


; <label>:19    		; preds = %16
  %20 = load i32* %i, align 4
  %21 = add i32 %20, 1
  store i32 %21, i32* %i, align 4
  br label %12


; <label>:22    		; preds = %12
  %23 = load i32* %a, align 4
  %24 = load i32* %2, align 4
  %25 = mul i32 %23, %24
  store i32 %25, i32* %a, align 4
  br label %26


; <label>:26    		; preds = %22
  %27 = load i32* %i, align 4
  %28 = add i32 %27, 1
  store i32 %28, i32* %i, align 4
  br label %5


; <label>:29     		; preds = %5
  %30 = load i32* %a, align 4
  %31 = add i32 %30, 1
  store i32 %31, i32* %a, align 4
  ret i32 %30
}

