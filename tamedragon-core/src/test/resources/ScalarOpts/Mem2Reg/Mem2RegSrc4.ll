define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 4, i32* %c, align 4
  store i32 9, i32* %d, align 4
  %3 = load i32, i32* %1, align 4
  %4 = load i32, i32* %2, align 4
  %5 = icmp sgt i32 %3, %4
  br i1 %5, label %6, label %22

; <label>:6                                       ; preds = %0
  %7 = load i32, i32* %d, align 4
  %8 = add i32 %7, 9
  store i32 %8, i32* %c, align 4
  %9 = load i32, i32* %1, align 4
  %10 = load i32, i32* %c, align 4
  %11 = icmp sgt i32 %9, %10
  br i1 %11, label %12, label %15

; <label>:12                                      ; preds = %6
  %13 = load i32, i32* %c, align 4
  %14 = add i32 %13, 56
  store i32 %14, i32* %d, align 4
  br label %18

; <label>:15                                      ; preds = %6
  %16 = load i32, i32* %d, align 4
  %17 = add i32 %16, 21
  store i32 %17, i32* %d, align 4
  br label %18

; <label>:18                                      ; preds = %15, %12
  %19 = load i32, i32* %1, align 4
  %20 = load i32, i32* %d, align 4
  %21 = add i32 %19, %20
  store i32 %21, i32* %d, align 4
  br label %26

; <label>:22                                      ; preds = %0
  %23 = load i32, i32* %1, align 4
  %24 = load i32, i32* %2, align 4
  %25 = add i32 %23, %24
  store i32 %25, i32* %d, align 4
  br label %26

; <label>:26                                      ; preds = %22, %18
  %27 = load i32, i32* %d, align 4
  ret i32 %27
}