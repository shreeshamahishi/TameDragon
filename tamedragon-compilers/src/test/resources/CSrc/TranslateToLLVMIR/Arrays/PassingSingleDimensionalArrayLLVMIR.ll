@.str = private unnamed_addr constant [9 x i8] c"sum = %d\00", align 1
@main.a = internal constant [3 x i32] [i32 1, i32 2, i32 3], align 4

declare i32 @printf(i8*, ...) 

define void @boo(i32* %a) nounwind {
  %1 = alloca i32*, align 8
  %sum = alloca i32, align 4
  %i = alloca i32, align 4
  store i32* %a, i32** %1, align 8
  store i32 0, i32* %i, align 4
  br label %2

; <label>:2 		; preds = %0, %12
  %3 = load i32, i32* %i, align 4
  %4 = icmp slt i32 %3, 3
  br i1 %4, label %5, label %15

; <label>:5                       		; preds = %2
  %6 = load i32*, i32** %1, align 8
  %7 = load i32, i32* %i, align 4
  %8 = getelementptr inbounds i32, i32* %6, i32 %7
  %9 = load i32, i32* %sum, align 4
  %10 = load i32, i32* %8, align 4
  %11 = add i32 %9, %10
  store i32 %11, i32* %sum, align 4
  br label %12

; <label>:12      		; preds = %5
  %13 = load i32, i32* %i, align 4
  %14 = add i32 %13, 1
  store i32 %14, i32* %i, align 4
  br label %2

; <label>:15                                          		; preds = %2
  %16 = getelementptr inbounds [9 x i8], [9 x i8]* @.str, i32 0, i32 0
  %17 = load i32, i32* %sum, align 4
  %18 = call i32 (i8*, ...)* @printf(i8* %16, i32 %17)
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %a = alloca [3 x i32], align 4
  store i32 0, i32* %1, align 4
  %2 = bitcast [3 x i32]* %a to i8*
  %3 = bitcast [3 x i32]* @main.a to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %2, i8* %3, i64 12, i32 4, i1 false)
  %4 = getelementptr inbounds [3 x i32], [3 x i32]* %a, i32 0, i32 0
  call void @boo(i32* %4)
  %5 = load i32, i32* %1, align 4
  ret i32 %5
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind
