@.str = private unnamed_addr constant [5 x i8] c"\09%d \00", align 1
@.str1 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
@.str2 = private unnamed_addr constant [35 x i8] c"Enter integer [%d to terminate] : \00", align 16
@.str3 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str4 = private unnamed_addr constant [15 x i8] c"array is full\0A\00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define void @printIntArray(i32* %a, i32 %n) nounwind {
  %1 = alloca i32*, align 8
  %2 = alloca i32, align 4
  %i = alloca i32, align 4
  store i32* %a, i32** %1, align 8
  store i32 %n, i32* %2, align 4
  store i32 0, i32* %i, align 4
  br label %3

; <label>:3 		; preds = %0, %21
  %4 = load i32, i32* %i, align 4
  %5 = load i32, i32* %2, align 4
  %6 = icmp slt i32 %4, %5
  br i1 %6, label %7, label %22

; <label>:7       		; preds = %3
  %8 = load i32, i32* %i, align 4
  %9 = add i32 %8, 1
  store i32 %9, i32* %i, align 4
  %10 = load i32*, i32** %1, align 8
  %11 = getelementptr inbounds i32, i32* %10, i32 %8
  %12 = getelementptr inbounds [5 x i8], [5 x i8]* @.str, i32 0, i32 0
  %13 = load i32, i32* %11, align 4
  %14 = call i32 (i8*, ...)* @printf(i8* %12, i32 %13)
  %15 = load i32, i32* %i, align 4
  %16 = srem i32 %15, 5
  %17 = icmp eq i32 %16, 0
  br i1 %17, label %18, label %21

; <label>:18                                           		; preds = %7
  %19 = getelementptr inbounds [2 x i8], [2 x i8]* @.str1, i32 0, i32 0
  %20 = call i32 (i8*, ...)* @printf(i8* %19)
  br label %21

; <label>:21		; preds = %7, %18
  br label %3

; <label>:22                                           		; preds = %3
  %23 = getelementptr inbounds [2 x i8], [2 x i8]* @.str1, i32 0, i32 0
  %24 = call i32 (i8*, ...)* @printf(i8* %23)
  ret void
}

define i32 @getIntArray(i32* %a, i32 %nmax, i32 %sentinel) nounwind {
  %1 = alloca i32*, align 8
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %n = alloca i32, align 4
  %temp = alloca i32, align 4
  store i32* %a, i32** %1, align 8
  store i32 %nmax, i32* %2, align 4
  store i32 %sentinel, i32* %3, align 4
  store i32 0, i32* %n, align 4
  br label %4

; <label>:4                                        		; preds = %0, %28
  %5 = getelementptr inbounds [35 x i8], [35 x i8]* @.str2, i32 0, i32 0
  %6 = load i32, i32* %3, align 4
  %7 = call i32 (i8*, ...)* @printf(i8* %5, i32 %6)
  %8 = getelementptr inbounds [3 x i8], [3 x i8]* @.str3, i32 0, i32 0
  %9 = call i32 (i8*, ...)* @scanf(i8* %8, i32* %temp)
  %10 = load i32, i32* %temp, align 4
  %11 = load i32, i32* %3, align 4
  %12 = icmp eq i32 %10, %11
  br i1 %12, label %13, label %14

; <label>:13		; preds = %4
  br label %29

; <label>:14      		; preds = %4
  %15 = load i32, i32* %n, align 4
  %16 = load i32, i32* %2, align 4
  %17 = icmp eq i32 %15, %16
  br i1 %17, label %18, label %21

; <label>:18                                            		; preds = %14
  %19 = getelementptr inbounds [15 x i8], [15 x i8]* @.str4, i32 0, i32 0
  %20 = call i32 (i8*, ...)* @printf(i8* %19)
  br label %27

; <label>:21        		; preds = %14
  %22 = load i32, i32* %n, align 4
  %23 = add i32 %22, 1
  store i32 %23, i32* %n, align 4
  %24 = load i32*, i32** %1, align 8
  %25 = getelementptr inbounds i32, i32* %24, i32 %22
  %26 = load i32, i32* %temp, align 4
  store i32 %26, i32* %25, align 4
  br label %27

; <label>:27		; preds = %18, %21
  br label %28

; <label>:28    		; preds = %27
  br i1 true, label %4, label %29

; <label>:29		; preds = %13, %28
  %30 = load i32, i32* %n, align 4
  ret i32 %30
}

define void @selectionSort(i32* %a, i32 %n) nounwind {
  %1 = alloca i32*, align 8
  %2 = alloca i32, align 4
  %lcv = alloca i32, align 4
  %rh = alloca i32, align 4
  %where = alloca i32, align 4
  %temp = alloca i32, align 4
  store i32* %a, i32** %1, align 8
  store i32 %n, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = sub i32 %3, 1
  store i32 %4, i32* %rh, align 4
  br label %5

; <label>:5  		; preds = %0, %45
  %6 = load i32, i32* %rh, align 4
  %7 = icmp sgt i32 %6, 0
  br i1 %7, label %8, label %48

; <label>:8        		; preds = %5
  store i32 0, i32* %where, align 4
  store i32 1, i32* %lcv, align 4
  br label %9

; <label>:9    		; preds = %8, %26
  %10 = load i32, i32* %lcv, align 4
  %11 = load i32, i32* %rh, align 4
  %12 = icmp sle i32 %10, %11
  br i1 %12, label %13, label %29

; <label>:13                         		; preds = %9
  %14 = load i32*, i32** %1, align 8
  %15 = load i32, i32* %lcv, align 4
  %16 = getelementptr inbounds i32, i32* %14, i32 %15
  %17 = load i32*, i32** %1, align 8
  %18 = load i32, i32* %where, align 4
  %19 = getelementptr inbounds i32, i32* %17, i32 %18
  %20 = load i32, i32* %16, align 4
  %21 = load i32, i32* %19, align 4
  %22 = icmp sgt i32 %20, %21
  br i1 %22, label %23, label %25

; <label>:23        		; preds = %13
  %24 = load i32, i32* %lcv, align 4
  store i32 %24, i32* %where, align 4
  br label %25

; <label>:25		; preds = %13, %23
  br label %26

; <label>:26       		; preds = %25
  %27 = load i32, i32* %lcv, align 4
  %28 = add i32 %27, 1
  store i32 %28, i32* %lcv, align 4
  br label %9

; <label>:29         		; preds = %9
  %30 = load i32*, i32** %1, align 8
  %31 = load i32, i32* %where, align 4
  %32 = getelementptr inbounds i32, i32* %30, i32 %31
  %33 = load i32, i32* %32, align 4
  store i32 %33, i32* %temp, align 4
  %34 = load i32*, i32** %1, align 8
  %35 = load i32, i32* %rh, align 4
  %36 = getelementptr inbounds i32, i32* %34, i32 %35
  %37 = load i32*, i32** %1, align 8
  %38 = load i32, i32* %where, align 4
  %39 = getelementptr inbounds i32, i32* %37, i32 %38
  %40 = load i32, i32* %36, align 4
  store i32 %40, i32* %39, align 4
  %41 = load i32*, i32** %1, align 8
  %42 = load i32, i32* %rh, align 4
  %43 = getelementptr inbounds i32, i32* %41, i32 %42
  %44 = load i32, i32* %temp, align 4
  store i32 %44, i32* %43, align 4
  br label %45

; <label>:45      		; preds = %29
  %46 = load i32, i32* %rh, align 4
  %47 = sub i32 %46, 1
  store i32 %47, i32* %rh, align 4
  br label %5

; <label>:48		; preds = %5
  ret void
}
