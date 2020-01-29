@main.arr = internal constant [10 x i32] [i32 1, i32 2, i32 3, i32 4, i32 5, i32 6, i32 7, i32 8, i32 9, i32 10], align 16
@.str = private unnamed_addr constant [4 x i8] c"%d,\00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @rand() nounwind

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %arr = alloca [10 x i32], align 16
  %i = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = bitcast [10 x i32]* %arr to i8*
  %3 = bitcast [10 x i32]* @main.arr to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %2, i8* %3, i64 40, i32 16, i1 false)
  store i32 0, i32* %i, align 4
  store i32 0, i32* %i, align 4
  br label %4

; <label>:4 		; preds = %0, %13
  %5 = load i32, i32* %i, align 4
  %6 = icmp slt i32 %5, 10
  br i1 %6, label %7, label %16

; <label>:7                           		; preds = %4
  %8 = load i32, i32* %i, align 4
  %9 = getelementptr inbounds [10 x i32], [10 x i32]* %arr, i32 0, i32 %8
  %10 = getelementptr inbounds [4 x i8], [4 x i8]* @.str, i32 0, i32 0
  %11 = load i32, i32* %9, align 4
  %12 = call i32 (i8*, ...)* @printf(i8* %10, i32 %11)
  br label %13

; <label>:13      		; preds = %7
  %14 = load i32, i32* %i, align 4
  %15 = add i32 %14, 1
  store i32 %15, i32* %i, align 4
  br label %4

; <label>:16      		; preds = %4
  %17 = load i32, i32* %1, align 4
  ret i32 %17
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind

define i32 @random_int(i32 %n) nounwind {
  %1 = alloca i32, align 4
  %limit = alloca i32, align 4
  %rnd = alloca i32, align 4
  store i32 %n, i32* %1, align 4
  %2 = load i32, i32* %1, align 4
  %3 = srem i32 10, %2
  %4 = sub i32 10, %3
  store i32 %4, i32* %limit, align 4
  br label %5

; <label>:5   		; preds = %0, %7
  %6 = call i32 @rand() nounwind
  store i32 %6, i32* %rnd, align 4
  br label %7

; <label>:7          		; preds = %5
  %8 = load i32, i32* %rnd, align 4
  %9 = load i32, i32* %limit, align 4
  %10 = icmp sge i32 %8, %9
  br i1 %10, label %5, label %11

; <label>:11        		; preds = %7
  %12 = load i32, i32* %rnd, align 4
  %13 = load i32, i32* %1, align 4
  %14 = srem i32 %12, %13
  ret i32 %14
}

define void @shuffle(i32* %array, i32 %n) nounwind {
  %1 = alloca i32*, align 8
  %2 = alloca i32, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  %tmp = alloca i32, align 4
  store i32* %array, i32** %1, align 8
  store i32 %n, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = sub i32 %3, 1
  store i32 %4, i32* %i, align 4
  br label %5

; <label>:5 		; preds = %0, %27
  %6 = load i32, i32* %i, align 4
  %7 = icmp sgt i32 %6, 0
  br i1 %7, label %8, label %30

; <label>:8       		; preds = %5
  %9 = load i32, i32* %i, align 4
  %10 = add i32 %9, 1
  %11 = call i32 @random_int(i32 %10)
  store i32 %11, i32* %j, align 4
  %12 = load i32*, i32** %1, align 8
  %13 = load i32, i32* %j, align 4
  %14 = getelementptr inbounds i32, i32* %12, i32 %13
  %15 = load i32, i32* %14, align 4
  store i32 %15, i32* %tmp, align 4
  %16 = load i32*, i32** %1, align 8
  %17 = load i32, i32* %i, align 4
  %18 = getelementptr inbounds i32, i32* %16, i32 %17
  %19 = load i32*, i32** %1, align 8
  %20 = load i32, i32* %j, align 4
  %21 = getelementptr inbounds i32, i32* %19, i32 %20
  %22 = load i32, i32* %18, align 4
  store i32 %22, i32* %21, align 4
  %23 = load i32*, i32** %1, align 8
  %24 = load i32, i32* %i, align 4
  %25 = getelementptr inbounds i32, i32* %23, i32 %24
  %26 = load i32, i32* %tmp, align 4
  store i32 %26, i32* %25, align 4
  br label %27

; <label>:27      		; preds = %8
  %28 = load i32, i32* %i, align 4
  %29 = sub i32 %28, 1
  store i32 %29, i32* %i, align 4
  br label %5

; <label>:30		; preds = %5
  ret void
}
