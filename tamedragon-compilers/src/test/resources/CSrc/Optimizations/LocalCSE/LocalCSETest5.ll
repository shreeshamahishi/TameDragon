define i32 @foo(i32 %a, i32 %b) nounwind {
  %arr = alloca [3 x [2 x [1 x i32]]], align 4
  %1 = getelementptr inbounds [3 x [2 x [1 x i32]]], [3 x [2 x [1 x i32]]]* %arr, i32 0, i32 0
  %2 = getelementptr inbounds [2 x [1 x i32]], [2 x [1 x i32]]* %1, i32 0, i32 0
  %3 = getelementptr inbounds [1 x i32], [1 x i32]* %2, i32 0, i32 0
  store i32 %a, i32* %3, align 4
  ret i32 %a
}
