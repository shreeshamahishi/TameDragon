define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %arr = alloca [3 x [3 x i32]], align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = getelementptr inbounds [3 x [3 x i32]], [3 x [3 x i32]]* %arr, i32 0, i32 0
  %4 = getelementptr inbounds [3 x i32], [3 x i32]* %3, i32 0, i32 0
  %5 = load i32, i32* %1, align 4
  store i32 %5, i32* %4, align 4
  %6 = getelementptr inbounds [3 x [3 x i32]], [3 x [3 x i32]]* %arr, i32 0, i32 0
  %7 = getelementptr inbounds [3 x i32], [3 x i32]* %6, i32 0, i32 0
  %8 = load i32, i32* %7, align 4
  ret i32 %8
}
