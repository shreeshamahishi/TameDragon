define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %arr = alloca [3 x float], align 4
  %i = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 0, i32* %i, align 4
  br label %3

; <label>:3 		; preds = %0, %11
  %4 = load i32, i32* %i, align 4
  %5 = icmp slt i32 %4, 3
  br i1 %5, label %6, label %14

; <label>:6          		; preds = %3
  %7 = load i32, i32* %i, align 4
  %8 = getelementptr inbounds [3 x float], [3 x float]* %arr, i32 0, i32 %7
  %9 = load i32, i32* %i, align 4
  %10 = sitofp i32 %9 to float
  store float %10, float* %8, align 4
  br label %11

; <label>:11      		; preds = %6
  %12 = load i32, i32* %i, align 4
  %13 = add i32 %12, 1
  store i32 %13, i32* %i, align 4
  br label %3

; <label>:14                                               		; preds = %3
  %15 = getelementptr inbounds [3 x float], [3 x float]* %arr, i32 0, i32 0
  %16 = getelementptr inbounds float, float* %15, i32 2
  %17 = load float, float* %16, align 4
  %18 = fptosi float %17 to i32
  ret i32 %18
}
