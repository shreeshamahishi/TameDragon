define i32 @foo(i32 %a, i32 %b) nounwind {
  %arr = alloca [3 x [3 x i32]], align 4
  %1 = getelementptr inbounds [3 x [3 x i32]], [3 x [3 x i32]]* %arr, i32 0, i32 0
  %2 = getelementptr inbounds [3 x i32], [3 x i32]* %1, i32 0, i32 0
  %3 = getelementptr inbounds [3 x [3 x i32]], [3 x [3 x i32]]* %arr, i32 0, i32 0
  %4 = getelementptr inbounds [3 x i32], [3 x i32]* %3, i32 0, i32 0
  store i32 %a, i32* %4, align 4
  ret i32 %a
}