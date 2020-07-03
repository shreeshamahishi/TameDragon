define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %arr = alloca [3 x [3 x [3 x i32]]], align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = getelementptr inbounds [3 x [3 x [3 x i32]]], [3 x [3 x [3 x i32]]]* %arr, i32 0, i32 0
  %4 = getelementptr inbounds [3 x [3 x i32]], [3 x [3 x i32]]* %3, i64 2
  %5 = ptrtoint [3 x [3 x i32]]* %4 to i32
  ret i32 %5
}
