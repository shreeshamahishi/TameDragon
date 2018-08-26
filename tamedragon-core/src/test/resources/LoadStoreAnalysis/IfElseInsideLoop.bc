define i32 @bar(i32 %m, i32 %threshold, i32 %MAX) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  store i32 %threshold, i32* %2, align 4
  store i32 %MAX, i32* %3, align 4
  store i32 21, i32* %a, align 4
  store i32 0, i32* %b, align 4
  br label %4


; <label>:4		; preds = %0, %26
  %5 = load i32* %1, align 4
  %6 = load i32* %3, align 4
  %7 = icmp slt i32 %5, %6
  br i1 %7, label %8, label %32


; <label>:8      		; preds = %4
  %9 = load i32* %a, align 4
  %10 = add i32 %9, 1
  store i32 %10, i32* %a, align 4
  %11 = load i32* %1, align 4
  %12 = load i32* %2, align 4
  %13 = icmp slt i32 %11, %12
  br i1 %13, label %14, label %20


; <label>:14     		; preds = %8
  %15 = load i32* %a, align 4
  %16 = add i32 %15, 3
  store i32 %16, i32* %a, align 4
  %17 = load i32* %a, align 4
  %18 = load i32* %1, align 4
  %19 = add i32 %17, %18
  store i32 %19, i32* %b, align 4
  br label %26


; <label>:20     		; preds = %8
  %21 = load i32* %a, align 4
  %22 = add i32 %21, 2
  store i32 %22, i32* %a, align 4
  %23 = load i32* %a, align 4
  %24 = load i32* %1, align 4
  %25 = sub i32 %23, %24
  store i32 %25, i32* %b, align 4
  br label %26


; <label>:26		; preds = %14, %20
  %27 = load i32* %1, align 4
  %28 = add i32 %27, 1
  store i32 %28, i32* %1, align 4
  %29 = load i32* %b, align 4
  %30 = load i32* %a, align 4
  %31 = add i32 %29, %30
  store i32 %31, i32* %b, align 4
  br label %4


; <label>:32 		; preds = %4
  %33 = load i32* %a, align 4
  %34 = load i32* %b, align 4
  %35 = add i32 %33, %34
  ret i32 %35
}

