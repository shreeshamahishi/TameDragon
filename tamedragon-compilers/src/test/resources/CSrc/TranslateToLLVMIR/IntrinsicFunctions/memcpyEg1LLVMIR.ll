@main.arr = internal constant [2 x i8] c"ab", align 1

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %arr = alloca [2 x i8], align 1
  store i32 0, i32* %1, align 4
  %2 = bitcast [2 x i8]* %arr to i8*
  %3 = getelementptr inbounds [2 x i8], [2 x i8]* @main.arr, i32 0, i32 0
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %2, i8* %3, i64 2, i32 1, i1 false)
  ret i32 0
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind
