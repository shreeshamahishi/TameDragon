define i32 @foo(i32 %a, i32 %b) nounwind {
  %arr = alloca [3 x [2 x [1 x i32]]], align 4
  %1 = getelementptr inbounds [3 x [2 x [1 x i32]]], [3 x [2 x [1 x i32]]]* %arr, i32 0, i32 0
  %2 = getelementptr inbounds [2 x [1 x i32]], [2 x [1 x i32]]* %1, i32 0, i32 0
  %3 = getelementptr inbounds [1 x i32], [1 x i32]* %2, i32 0, i32 0
  store i32 %a, i32* %3, align 4
  %4 = getelementptr inbounds [3 x [2 x [1 x i32]]], [3 x [2 x [1 x i32]]]* %arr, i32 0, i32 0
  %5 = getelementptr inbounds [2 x [1 x i32]], [2 x [1 x i32]]* %4, i32 0, i32 0
  %6 = getelementptr inbounds [1 x i32], [1 x i32]* %5, i32 0, i32 0
  %7 = load i32, i32* %6, align 4
  ret i32 %7
}