define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %arr = alloca [3 x [3 x float]], align 4
  %i = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 0, i32* %i, align 4
  br label %3

; <label>:3 		; preds = %0, %12
  %4 = load i32, i32* %i, align 4
  %5 = icmp slt i32 %4, 3
  br i1 %5, label %6, label %15

; <label>:6                                                           		; preds = %3
  %7 = getelementptr inbounds [3 x [3 x float]], [3 x [3 x float]]* %arr, i32 0, i32 0
  %8 = load i32, i32* %i, align 4
  %9 = getelementptr inbounds [3 x float], [3 x float]* %7, i32 0, i32 %8
  %10 = load i32, i32* %i, align 4
  %11 = sitofp i32 %10 to float
  store float %11, float* %9, align 4
  br label %12

; <label>:12      		; preds = %6
  %13 = load i32, i32* %i, align 4
  %14 = add i32 %13, 1
  store i32 %14, i32* %i, align 4
  br label %3

; <label>:15                                                           		; preds = %3
  %16 = getelementptr inbounds [3 x [3 x float]], [3 x [3 x float]]* %arr, i32 0, i32 0
  %17 = getelementptr inbounds [3 x float], [3 x float]* %16, i32 1
  %18 = getelementptr inbounds [3 x float], [3 x float]* %17, i32 0, i32 0
  %19 = ptrtoint float* %18 to i32
  ret i32 %19
}
