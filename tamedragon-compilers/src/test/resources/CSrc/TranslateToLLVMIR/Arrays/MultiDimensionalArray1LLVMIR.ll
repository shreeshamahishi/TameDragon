define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %x = alloca [20 x [2 x i32]], align 4
  store i32 0, i32* %1, align 4
  %2 = bitcast [20 x [2 x i32]]* %x to i8*
  call void @llvm.memset.p0i8.i64(i8* %2, i8 0, i64 160, i32 4, i1 0)
  %3 = getelementptr inbounds [20 x [2 x i32]], [20 x [2 x i32]]* %x, i32 0, i32 0
  %4 = getelementptr inbounds [2 x i32], [2 x i32]* %3, i32 0, i32 0
  store i32 1, i32* %4, align 4
  %5 = getelementptr inbounds [2 x i32], [2 x i32]* %3, i32 0, i32 1
  store i32 2, i32* %5, align 4
  %6 = getelementptr inbounds [20 x [2 x i32]], [20 x [2 x i32]]* %x, i32 0, i32 1
  %7 = getelementptr inbounds [2 x i32], [2 x i32]* %6, i32 0, i32 0
  store i32 3, i32* %7, align 4
  %8 = getelementptr inbounds [2 x i32], [2 x i32]* %6, i32 0, i32 1
  store i32 4, i32* %8, align 4
  ret i32 0
}

declare void @llvm.memset.p0i8.i64(i8* nocapture, i8, i64, i32, i1) nounwind
