define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %arr = alloca [3 x [3 x i32]], align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = getelementptr inbounds [3 x [3 x i32]], [3 x [3 x i32]]* %arr, i32 0, i32 0
  %4 = load i32, i32* %1, align 4
  %5 = load i32, i32* %2, align 4
  %6 = add i32 %4, %5
  %7 = sub i32 0, %6
  %8 = getelementptr inbounds [3 x i32], [3 x i32]* %3, i32 %7
  %9 = getelementptr inbounds [3 x i32], [3 x i32]* %8, i32 0, i32 0
  %10 = ptrtoint i32* %9 to i32
  ret i32 %10
}
