define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %arr = alloca [3 x [2 x [1 x i32]]], align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = getelementptr inbounds [3 x [2 x [1 x i32]]], [3 x [2 x [1 x i32]]]* %arr, i32 0, i32 0
  %4 = getelementptr inbounds [2 x [1 x i32]], [2 x [1 x i32]]* %3, i32 0, i32 0
  %5 = getelementptr inbounds [1 x i32], [1 x i32]* %4, i32 0, i32 0
  %6 = load i32, i32* %1, align 4
  store i32 %6, i32* %5, align 4
  %7 = getelementptr inbounds [3 x [2 x [1 x i32]]], [3 x [2 x [1 x i32]]]* %arr, i32 0, i32 0
  %8 = getelementptr inbounds [2 x [1 x i32]], [2 x [1 x i32]]* %7, i32 0, i32 0
  %9 = getelementptr inbounds [1 x i32], [1 x i32]* %8, i32 0, i32 0
  %10 = load i32, i32* %9, align 4
  ret i32 %10
}
