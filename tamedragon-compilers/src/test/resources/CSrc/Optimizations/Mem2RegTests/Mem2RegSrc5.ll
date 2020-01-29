define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca i32, align 4
  %i = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 43, i32* %c, align 4
  store i32 98, i32* %d, align 4
  store i32 3, i32* %i, align 4
  store i32 0, i32* %i, align 4
  br label %3

; <label>:3                                       ; preds = %28, %0
  %4 = load i32* %i, align 4
  %5 = load i32* %1, align 4
  %6 = icmp slt i32 %4, %5
  br i1 %6, label %7, label %31

; <label>:7                                       ; preds = %3
  %8 = load i32* %2, align 4
  %9 = load i32* %i, align 4
  %10 = load i32* %c, align 4
  %11 = add i32 %9, %10
  %12 = icmp sgt i32 %8, %11
  br i1 %12, label %13, label %19

; <label>:13                                      ; preds = %7
  %14 = load i32* %1, align 4
  %15 = load i32* %i, align 4
  %16 = add i32 %14, %15
  %17 = load i32* %d, align 4
  %18 = add i32 %17, %16
  store i32 %18, i32* %d, align 4
  br label %25

; <label>:19                                      ; preds = %7
  %20 = load i32* %2, align 4
  %21 = load i32* %i, align 4
  %22 = mul i32 %20, %21
  %23 = load i32* %d, align 4
  %24 = add i32 %23, %22
  store i32 %24, i32* %d, align 4
  br label %25

; <label>:25                                      ; preds = %19, %13
  %26 = load i32* %d, align 4
  %27 = add i32 %26, 3
  store i32 %27, i32* %d, align 4
  br label %28

; <label>:28                                      ; preds = %25
  %29 = load i32* %i, align 4
  %30 = add i32 %29, 1
  store i32 %30, i32* %i, align 4
  br label %3

; <label>:31                                      ; preds = %3
  %32 = load i32* %d, align 4
  %33 = sub i32 %32, 4
  store i32 %33, i32* %d, align 4
  %34 = load i32* %d, align 4
  ret i32 %34
}