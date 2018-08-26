define i32 @foo(i32 %a) nounwind {
  %1 = alloca i32, align 4
  %arr = alloca [3 x i32], align 4
  %i = alloca i32, align 4
  %result = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  %2 = getelementptr inbounds [3 x i32]* %arr, i32 0, i32 0
  store i32 1, i32* %2
  %3 = getelementptr inbounds [3 x i32]* %arr, i32 0, i32 1
  store i32 2, i32* %3
  %4 = getelementptr inbounds [3 x i32]* %arr, i32 0, i32 2
  store i32 3, i32* %4
  store i32 0, i32* %i, align 4
  br label %5

; <label>:5                                       ; preds = %15, %0
  %6 = load i32* %i, align 4
  %7 = load i32* %1, align 4
  %8 = icmp slt i32 %6, %7
  br i1 %8, label %9, label %18

; <label>:9                                       ; preds = %5
  %10 = load i32* %i, align 4
  %11 = getelementptr inbounds [3 x i32]* %arr, i32 0, i32 %10
  %12 = load i32* %11
  %13 = load i32* %result, align 4
  %14 = add nsw i32 %13, %12
  store i32 %14, i32* %result, align 4
  br label %15

; <label>:15                                      ; preds = %9
  %16 = load i32* %i, align 4
  %17 = add nsw i32 %16, 1
  store i32 %17, i32* %i, align 4
  br label %5

; <label>:18                                      ; preds = %5
  %19 = load i32* %result, align 4
  ret i32 %19
}