@main.arr = internal constant [2 x [2 x i32]] [[2 x i32] [i32 1, i32 2], [2 x i32] [i32 3, i32 4]], align 4
@.str = private unnamed_addr constant [16 x i8] c"arr[0][0] = %d\0A\00", align 16

declare i32 @printf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %arr = alloca [2 x [2 x i32]], align 4
  store i32 0, i32* %1, align 4
  %2 = bitcast [2 x [2 x i32]]* %arr to i8*
  %3 = bitcast [2 x [2 x i32]]* @main.arr to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %2, i8* %3, i64 16, i32 4, i1 false)
  %4 = getelementptr inbounds [2 x [2 x i32]], [2 x [2 x i32]]* %arr, i32 0, i32 0
  %5 = getelementptr inbounds [2 x i32], [2 x i32]* %4, i32 0, i32 0
  %6 = getelementptr inbounds [16 x i8], [16 x i8]* @.str, i32 0, i32 0
  %7 = load i32, i32* %5, align 4
  %8 = call i32 (i8*, ...)* @printf(i8* %6, i32 %7)
  ret i32 0
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind
