define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %arr = alloca [3 x [3 x float]], align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = getelementptr inbounds [3 x [3 x float]], [3 x [3 x float]]* %arr, i32 0, i32 0
  %4 = getelementptr inbounds [3 x float], [3 x float]* %3, i32 0, i32 0
  %5 = load i32, i32* %1, align 4
  %6 = sitofp i32 %5 to float
  store float %6, float* %4, align 4
  %7 = load i32, i32* %1, align 4
  ret i32 %7
}
