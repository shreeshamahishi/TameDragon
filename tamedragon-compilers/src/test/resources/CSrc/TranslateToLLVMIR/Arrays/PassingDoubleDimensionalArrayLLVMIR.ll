@.str = private unnamed_addr constant [9 x i8] c"sum = %d\00", align 1
@main.a = internal constant [3 x [3 x i32]] [[3 x i32] [i32 1, i32 2, i32 3], [3 x i32] [i32 4, i32 5, i32 6], [3 x i32] [i32 7, i32 8, i32 9]], align 4

declare i32 @printf(i8*, ...) 

define void @boo([3 x i32]* %a) nounwind {
  %1 = alloca [3 x i32]*, align 8
  %sum = alloca i32, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  store [3 x i32]* %a, [3 x i32]** %1, align 8
  store i32 0, i32* %i, align 4
  br label %2

; <label>:2 		; preds = %0, %22
  %3 = load i32, i32* %i, align 4
  %4 = icmp slt i32 %3, 3
  br i1 %4, label %5, label %25

; <label>:5    		; preds = %2
  store i32 0, i32* %j, align 4
  br label %6

; <label>:6 		; preds = %5, %18
  %7 = load i32, i32* %j, align 4
  %8 = icmp slt i32 %7, 3
  br i1 %8, label %9, label %21

; <label>:9                                             		; preds = %6
  %10 = load [3 x i32]*, [3 x i32]** %1, align 8
  %11 = load i32, i32* %i, align 4
  %12 = getelementptr inbounds [3 x i32], [3 x i32]* %10, i32 %11
  %13 = load i32, i32* %j, align 4
  %14 = getelementptr inbounds [3 x i32], [3 x i32]* %12, i32 0, i32 %13
  %15 = load i32, i32* %sum, align 4
  %16 = load i32, i32* %14, align 4
  %17 = add i32 %15, %16
  store i32 %17, i32* %sum, align 4
  br label %18

; <label>:18      		; preds = %9
  %19 = load i32, i32* %j, align 4
  %20 = add i32 %19, 1
  store i32 %20, i32* %j, align 4
  br label %6

; <label>:21		; preds = %6
  br label %22

; <label>:22     		; preds = %21
  %23 = load i32, i32* %i, align 4
  %24 = add i32 %23, 1
  store i32 %24, i32* %i, align 4
  br label %2

; <label>:25                                          		; preds = %2
  %26 = getelementptr inbounds [9 x i8], [9 x i8]* @.str, i32 0, i32 0
  %27 = load i32, i32* %sum, align 4
  %28 = call i32 (i8*, ...)* @printf(i8* %26, i32 %27)
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %a = alloca [3 x [3 x i32]], align 4
  store i32 0, i32* %1, align 4
  %2 = bitcast [3 x [3 x i32]]* %a to i8*
  %3 = bitcast [3 x [3 x i32]]* @main.a to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %2, i8* %3, i64 36, i32 4, i1 0)
  %4 = getelementptr inbounds [3 x [3 x i32]], [3 x [3 x i32]]* %a, i32 0, i32 0
  call void @boo([3 x i32]* %4)
  %5 = load i32, i32* %1, align 4
  ret i32 %5
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind
