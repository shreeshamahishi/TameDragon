@main.arr = internal constant [2 x i32] [i32 1, i32 2], align 4

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %arr = alloca [2 x i32], align 4
  store i32 0, i32* %1, align 4
  %2 = bitcast [2 x i32]* %arr to i8*
  %3 = bitcast [2 x i32]* @main.arr to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %2, i8* %3, i64 8, i32 4, i1 false)
  ret i32 0
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind
