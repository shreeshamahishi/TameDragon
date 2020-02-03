@data = common global [8 x i32] zeroinitializer, align 16
@up = global i32 1, align 4
@down = global i32 0, align 4
@.str = private unnamed_addr constant [16 x i8] c"\0AEnter the data\00", align 16
@.str1 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str2 = private unnamed_addr constant [4 x i8] c"%d \00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define void @compare(i32 %i, i32 %j, i32 %dir) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %t = alloca i32, align 4
  store i32 %i, i32* %1, align 4
  store i32 %j, i32* %2, align 4
  store i32 %dir, i32* %3, align 4
  %4 = load i32, i32* %3, align 4
  %5 = load i32, i32* %1, align 4
  %6 = getelementptr inbounds [8 x i32], [8 x i32]* @data, i32 0, i32 %5
  %7 = load i32, i32* %2, align 4
  %8 = getelementptr inbounds [8 x i32], [8 x i32]* @data, i32 0, i32 %7
  %9 = load i32, i32* %6, align 4
  %10 = load i32, i32* %8, align 4
  %11 = icmp sgt i32 %9, %10
  %12 = trunc i32 %4 to i1
  %13 = icmp eq i1 %12, %11
  br i1 %13, label %14, label %26

; <label>:14                                              		; preds = %0
  %15 = load i32, i32* %1, align 4
  %16 = getelementptr inbounds [8 x i32], [8 x i32]* @data, i32 0, i32 %15
  %17 = load i32, i32* %16, align 4
  store i32 %17, i32* %t, align 4
  %18 = load i32, i32* %2, align 4
  %19 = getelementptr inbounds [8 x i32], [8 x i32]* @data, i32 0, i32 %18
  %20 = load i32, i32* %1, align 4
  %21 = getelementptr inbounds [8 x i32], [8 x i32]* @data, i32 0, i32 %20
  %22 = load i32, i32* %19, align 4
  store i32 %22, i32* %21, align 4
  %23 = load i32, i32* %2, align 4
  %24 = getelementptr inbounds [8 x i32], [8 x i32]* @data, i32 0, i32 %23
  %25 = load i32, i32* %t, align 4
  store i32 %25, i32* %24, align 4
  br label %26

; <label>:26		; preds = %0, %14
  ret void
}

define void @bitonicmerge(i32 %low, i32 %c, i32 %dir) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %k = alloca i32, align 4
  %i = alloca i32, align 4
  store i32 %low, i32* %1, align 4
  store i32 %c, i32* %2, align 4
  store i32 %dir, i32* %3, align 4
  %4 = load i32, i32* %2, align 4
  %5 = icmp sgt i32 %4, 1
  br i1 %5, label %6, label %34

; <label>:6      		; preds = %0
  %7 = load i32, i32* %2, align 4
  %8 = sdiv i32 %7, 2
  store i32 %8, i32* %k, align 4
  %9 = load i32, i32* %1, align 4
  store i32 %9, i32* %i, align 4
  br label %10

; <label>:10 		; preds = %6, %22
  %11 = load i32, i32* %i, align 4
  %12 = load i32, i32* %1, align 4
  %13 = load i32, i32* %k, align 4
  %14 = add i32 %12, %13
  %15 = icmp slt i32 %11, %14
  br i1 %15, label %16, label %25

; <label>:16                  		; preds = %10
  %17 = load i32, i32* %i, align 4
  %18 = load i32, i32* %i, align 4
  %19 = load i32, i32* %k, align 4
  %20 = add i32 %18, %19
  %21 = load i32, i32* %3, align 4
  call void @compare(i32 %17, i32 %20, i32 %21)
  br label %22

; <label>:22     		; preds = %16
  %23 = load i32, i32* %i, align 4
  %24 = add i32 %23, 1
  store i32 %24, i32* %i, align 4
  br label %10

; <label>:25                       		; preds = %10
  %26 = load i32, i32* %1, align 4
  %27 = load i32, i32* %k, align 4
  %28 = load i32, i32* %3, align 4
  call void @bitonicmerge(i32 %26, i32 %27, i32 %28)
  %29 = load i32, i32* %1, align 4
  %30 = load i32, i32* %k, align 4
  %31 = add i32 %29, %30
  %32 = load i32, i32* %k, align 4
  %33 = load i32, i32* %3, align 4
  call void @bitonicmerge(i32 %31, i32 %32, i32 %33)
  br label %34

; <label>:34		; preds = %0, %25
  ret void
}

define void @recbitonic(i32 %low, i32 %c, i32 %dir) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %k = alloca i32, align 4
  store i32 %low, i32* %1, align 4
  store i32 %c, i32* %2, align 4
  store i32 %dir, i32* %3, align 4
  %4 = load i32, i32* %2, align 4
  %5 = icmp sgt i32 %4, 1
  br i1 %5, label %6, label %20

; <label>:6                         		; preds = %0
  %7 = load i32, i32* %2, align 4
  %8 = sdiv i32 %7, 2
  store i32 %8, i32* %k, align 4
  %9 = load i32, i32* %1, align 4
  %10 = load i32, i32* %k, align 4
  %11 = load i32, i32* @up, align 4
  call void @recbitonic(i32 %9, i32 %10, i32 %11)
  %12 = load i32, i32* %1, align 4
  %13 = load i32, i32* %k, align 4
  %14 = add i32 %12, %13
  %15 = load i32, i32* %k, align 4
  %16 = load i32, i32* @down, align 4
  call void @recbitonic(i32 %14, i32 %15, i32 %16)
  %17 = load i32, i32* %1, align 4
  %18 = load i32, i32* %2, align 4
  %19 = load i32, i32* %3, align 4
  call void @bitonicmerge(i32 %17, i32 %18, i32 %19)
  br label %20

; <label>:20		; preds = %0, %6
  ret void
}

define void @sort() nounwind {
  %1 = load i32, i32* @up, align 4
  call void @recbitonic(i32 0, i32 8, i32 %1)
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %i = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [16 x i8], [16 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  store i32 0, i32* %i, align 4
  br label %4

; <label>:4 		; preds = %0, %12
  %5 = load i32, i32* %i, align 4
  %6 = icmp slt i32 %5, 8
  br i1 %6, label %7, label %15

; <label>:7                          		; preds = %4
  %8 = load i32, i32* %i, align 4
  %9 = getelementptr inbounds [8 x i32], [8 x i32]* @data, i32 0, i32 %8
  %10 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %11 = call i32 (i8*, ...)* @scanf(i8* %10, i32* %9)
  br label %12

; <label>:12      		; preds = %7
  %13 = load i32, i32* %i, align 4
  %14 = add i32 %13, 1
  store i32 %14, i32* %i, align 4
  br label %4

; <label>:15   		; preds = %4
  call void @sort()
  store i32 0, i32* %i, align 4
  br label %16

; <label>:16		; preds = %15, %25
  %17 = load i32, i32* %i, align 4
  %18 = icmp slt i32 %17, 8
  br i1 %18, label %19, label %28

; <label>:19                         		; preds = %16
  %20 = load i32, i32* %i, align 4
  %21 = getelementptr inbounds [8 x i32], [8 x i32]* @data, i32 0, i32 %20
  %22 = getelementptr inbounds [4 x i8], [4 x i8]* @.str2, i32 0, i32 0
  %23 = load i32, i32* %21, align 4
  %24 = call i32 (i8*, ...)* @printf(i8* %22, i32 %23)
  br label %25

; <label>:25     		; preds = %19
  %26 = load i32, i32* %i, align 4
  %27 = add i32 %26, 1
  store i32 %27, i32* %i, align 4
  br label %16

; <label>:28     		; preds = %16
  %29 = load i32, i32* %1, align 4
  ret i32 %29
}
