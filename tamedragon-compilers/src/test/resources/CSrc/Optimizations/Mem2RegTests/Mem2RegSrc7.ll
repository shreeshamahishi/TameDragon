define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 32, i32* %d, align 4
  %3 = load i32* %1, align 4
  %4 = load i32* %2, align 4
  %5 = icmp sgt i32 %3, %4
  br i1 %5, label %6, label %10

; <label>:6                                       ; preds = %0
  %7 = load i32* %c, align 4
  %8 = load i32* %d, align 4
  %9 = add i32 %8, %7
  store i32 %9, i32* %d, align 4
  br label %14

; <label>:10                                      ; preds = %0
  %11 = load i32* %2, align 4
  %12 = load i32* %d, align 4
  %13 = add i32 %12, %11
  store i32 %13, i32* %d, align 4
  br label %14

; <label>:14                                      ; preds = %10, %6
  %15 = load i32* %1, align 4
  %16 = load i32* %2, align 4
  %17 = add i32 %15, %16
  %18 = load i32* %c, align 4
  %19 = add i32 %17, %18
  %20 = load i32* %d, align 4
  %21 = add i32 %20, %19
  store i32 %21, i32* %d, align 4
  %22 = load i32* %d, align 4
  ret i32 %22
}