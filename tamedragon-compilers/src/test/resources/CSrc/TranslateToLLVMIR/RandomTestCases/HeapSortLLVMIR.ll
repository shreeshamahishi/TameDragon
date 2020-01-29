@heap = common global [1000000 x i32] zeroinitializer, align 16
@heapSize = common global i32 0, align 4
@.str = private unnamed_addr constant [30 x i8] c"Enter the nos. of Elements : \00", align 16
@.str1 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str2 = private unnamed_addr constant [4 x i8] c"%d \00", align 1
@.str3 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define void @Init() nounwind {
  store i32 0, i32* @heapSize, align 4
  %1 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 0
  store i32 -2147483647, i32* %1, align 4
  ret void
}

define void @Insert(i32 %element) nounwind {
  %1 = alloca i32, align 4
  %now = alloca i32, align 4
  store i32 %element, i32* %1, align 4
  %2 = load i32, i32* @heapSize, align 4
  %3 = add i32 %2, 1
  store i32 %3, i32* @heapSize, align 4
  %4 = load i32, i32* @heapSize, align 4
  %5 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %4
  %6 = load i32, i32* %1, align 4
  store i32 %6, i32* %5, align 4
  %7 = load i32, i32* @heapSize, align 4
  store i32 %7, i32* %now, align 4
  br label %8

; <label>:8                                                      		; preds = %0, %15
  %9 = load i32, i32* %now, align 4
  %10 = sdiv i32 %9, 2
  %11 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %10
  %12 = load i32, i32* %1, align 4
  %13 = load i32, i32* %11, align 4
  %14 = icmp sgt i32 %13, %12
  br i1 %14, label %15, label %24

; <label>:15                                                          		; preds = %8
  %16 = load i32, i32* %now, align 4
  %17 = sdiv i32 %16, 2
  %18 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %17
  %19 = load i32, i32* %now, align 4
  %20 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %19
  %21 = load i32, i32* %18, align 4
  store i32 %21, i32* %20, align 4
  %22 = load i32, i32* %now, align 4
  %23 = sdiv i32 %22, 2
  store i32 %23, i32* %now, align 4
  br label %8

; <label>:24                                                          		; preds = %8
  %25 = load i32, i32* %now, align 4
  %26 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %25
  %27 = load i32, i32* %1, align 4
  store i32 %27, i32* %26, align 4
  ret void
}

define i32 @DeleteMin() nounwind {
  %minElement = alloca i32, align 4
  %lastElement = alloca i32, align 4
  %child = alloca i32, align 4
  %now = alloca i32, align 4
  %1 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 1
  %2 = load i32, i32* %1, align 4
  store i32 %2, i32* %minElement, align 4
  %3 = load i32, i32* @heapSize, align 4
  %4 = sub i32 %3, 1
  store i32 %4, i32* @heapSize, align 4
  %5 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %3
  %6 = load i32, i32* %5, align 4
  store i32 %6, i32* %lastElement, align 4
  store i32 1, i32* %now, align 4
  br label %7

; <label>:7         		; preds = %0, %46
  %8 = load i32, i32* %now, align 4
  %9 = mul i32 %8, 2
  %10 = load i32, i32* @heapSize, align 4
  %11 = icmp sle i32 %9, %10
  br i1 %11, label %12, label %48

; <label>:12             		; preds = %7
  %13 = load i32, i32* %now, align 4
  %14 = mul i32 %13, 2
  store i32 %14, i32* %child, align 4
  %15 = load i32, i32* %child, align 4
  %16 = load i32, i32* @heapSize, align 4
  %17 = icmp ne i32 %15, %16
  br i1 %17, label %18, label %27

; <label>:18                                                         		; preds = %12
  %19 = load i32, i32* %child, align 4
  %20 = add i32 %19, 1
  %21 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %20
  %22 = load i32, i32* %child, align 4
  %23 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %22
  %24 = load i32, i32* %21, align 4
  %25 = load i32, i32* %23, align 4
  %26 = icmp slt i32 %24, %25
  br label %27

; <label>:27         		; preds = %12, %18
  %28 = phi i1 [ false, %12 ], [ %26, %18 ]
  br i1 %28, label %29, label %32

; <label>:29         		; preds = %27
  %30 = load i32, i32* %child, align 4
  %31 = add i32 %30, 1
  store i32 %31, i32* %child, align 4
  br label %32

; <label>:32                                                    		; preds = %27, %29
  %33 = load i32, i32* %lastElement, align 4
  %34 = load i32, i32* %child, align 4
  %35 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %34
  %36 = load i32, i32* %35, align 4
  %37 = icmp sgt i32 %33, %36
  br i1 %37, label %38, label %44

; <label>:38                                                         		; preds = %32
  %39 = load i32, i32* %child, align 4
  %40 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %39
  %41 = load i32, i32* %now, align 4
  %42 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %41
  %43 = load i32, i32* %40, align 4
  store i32 %43, i32* %42, align 4
  br label %45

; <label>:44		; preds = %32
  br label %48

; <label>:45		; preds = %38
  br label %46

; <label>:46         		; preds = %45
  %47 = load i32, i32* %child, align 4
  store i32 %47, i32* %now, align 4
  br label %7

; <label>:48          		; preds = %7, %44
  %49 = load i32, i32* %now, align 4
  %50 = getelementptr inbounds [1000000 x i32], [1000000 x i32]* @heap, i32 0, i32 %49
  %51 = load i32, i32* %lastElement, align 4
  store i32 %51, i32* %50, align 4
  %52 = load i32, i32* %minElement, align 4
  ret i32 %52
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %number_of_elements = alloca i32, align 4
  %iter = alloca i32, align 4
  %element = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [30 x i8], [30 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %number_of_elements)
  call void @Init()
  store i32 0, i32* %iter, align 4
  br label %6

; <label>:6                  		; preds = %0, %14
  %7 = load i32, i32* %iter, align 4
  %8 = load i32, i32* %number_of_elements, align 4
  %9 = icmp slt i32 %7, %8
  br i1 %9, label %10, label %17

; <label>:10                                           		; preds = %6
  %11 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %12 = call i32 (i8*, ...)* @scanf(i8* %11, i32* %element)
  %13 = load i32, i32* %element, align 4
  call void @Insert(i32 %13)
  br label %14

; <label>:14        		; preds = %10
  %15 = load i32, i32* %iter, align 4
  %16 = add i32 %15, 1
  store i32 %16, i32* %iter, align 4
  br label %6

; <label>:17      		; preds = %6
  store i32 0, i32* %iter, align 4
  br label %18

; <label>:18                 		; preds = %17, %26
  %19 = load i32, i32* %iter, align 4
  %20 = load i32, i32* %number_of_elements, align 4
  %21 = icmp slt i32 %19, %20
  br i1 %21, label %22, label %29

; <label>:22                         		; preds = %18
  %23 = call i32 @DeleteMin()
  %24 = getelementptr inbounds [4 x i8], [4 x i8]* @.str2, i32 0, i32 0
  %25 = call i32 (i8*, ...)* @printf(i8* %24, i32 %23)
  br label %26

; <label>:26        		; preds = %22
  %27 = load i32, i32* %iter, align 4
  %28 = add i32 %27, 1
  store i32 %28, i32* %iter, align 4
  br label %18

; <label>:29                                          		; preds = %18
  %30 = getelementptr inbounds [2 x i8], [2 x i8]* @.str3, i32 0, i32 0
  %31 = call i32 (i8*, ...)* @printf(i8* %30)
  ret i32 0
}
