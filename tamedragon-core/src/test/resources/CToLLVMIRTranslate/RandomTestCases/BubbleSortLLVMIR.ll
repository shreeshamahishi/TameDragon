@.str = private unnamed_addr constant [62 x i8] c"Enter integer value for total no.s of elements to be sorted: \00", align 16
@.str1 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str2 = private unnamed_addr constant [41 x i8] c"Enter integer value for element no.%d : \00", align 16
@.str3 = private unnamed_addr constant [26 x i8] c"Finally sorted array is: \00", align 16
@.str4 = private unnamed_addr constant [4 x i8] c"%d \00", align 1
@.str5 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define void @bubble(i32* %a, i32 %n) nounwind {
  %1 = alloca i32*, align 8
  %2 = alloca i32, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  %t = alloca i32, align 4
  store i32* %a, i32** %1, align 8
  store i32 %n, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = sub i32 %3, 2
  store i32 %4, i32* %i, align 4
  br label %5

; <label>:5 		; preds = %0, %47
  %6 = load i32, i32* %i, align 4
  %7 = icmp sge i32 %6, 0
  br i1 %7, label %8, label %50

; <label>:8    		; preds = %5
  store i32 0, i32* %j, align 4
  br label %9

; <label>:9  		; preds = %8, %43
  %10 = load i32, i32* %j, align 4
  %11 = load i32, i32* %i, align 4
  %12 = icmp sle i32 %10, %11
  br i1 %12, label %13, label %46

; <label>:13                         		; preds = %9
  %14 = load i32*, i32** %1, align 8
  %15 = load i32, i32* %j, align 4
  %16 = getelementptr inbounds i32, i32* %14, i32 %15
  %17 = load i32*, i32** %1, align 8
  %18 = load i32, i32* %j, align 4
  %19 = add i32 %18, 1
  %20 = getelementptr inbounds i32, i32* %17, i32 %19
  %21 = load i32, i32* %16, align 4
  %22 = load i32, i32* %20, align 4
  %23 = icmp sgt i32 %21, %22
  br i1 %23, label %24, label %42

; <label>:24                        		; preds = %13
  %25 = load i32*, i32** %1, align 8
  %26 = load i32, i32* %j, align 4
  %27 = getelementptr inbounds i32, i32* %25, i32 %26
  %28 = load i32, i32* %27, align 4
  store i32 %28, i32* %t, align 4
  %29 = load i32*, i32** %1, align 8
  %30 = load i32, i32* %j, align 4
  %31 = add i32 %30, 1
  %32 = getelementptr inbounds i32, i32* %29, i32 %31
  %33 = load i32*, i32** %1, align 8
  %34 = load i32, i32* %j, align 4
  %35 = getelementptr inbounds i32, i32* %33, i32 %34
  %36 = load i32, i32* %32, align 4
  store i32 %36, i32* %35, align 4
  %37 = load i32*, i32** %1, align 8
  %38 = load i32, i32* %j, align 4
  %39 = add i32 %38, 1
  %40 = getelementptr inbounds i32, i32* %37, i32 %39
  %41 = load i32, i32* %t, align 4
  store i32 %41, i32* %40, align 4
  br label %42

; <label>:42		; preds = %13, %24
  br label %43

; <label>:43     		; preds = %42
  %44 = load i32, i32* %j, align 4
  %45 = add i32 %44, 1
  store i32 %45, i32* %j, align 4
  br label %9

; <label>:46		; preds = %9
  br label %47

; <label>:47     		; preds = %46
  %48 = load i32, i32* %i, align 4
  %49 = sub i32 %48, 1
  store i32 %49, i32* %i, align 4
  br label %5

; <label>:50		; preds = %5
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %a = alloca [100 x i32], align 16
  %n = alloca i32, align 4
  %i = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [62 x i8], [62 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %n)
  store i32 0, i32* %i, align 4
  br label %6

; <label>:6 		; preds = %0, %20
  %7 = load i32, i32* %i, align 4
  %8 = load i32, i32* %n, align 4
  %9 = sub i32 %8, 1
  %10 = icmp sle i32 %7, %9
  br i1 %10, label %11, label %23

; <label>:11                                               		; preds = %6
  %12 = getelementptr inbounds [41 x i8], [41 x i8]* @.str2, i32 0, i32 0
  %13 = load i32, i32* %i, align 4
  %14 = add i32 %13, 1
  %15 = call i32 (i8*, ...)* @printf(i8* %12, i32 %14)
  %16 = load i32, i32* %i, align 4
  %17 = getelementptr inbounds [100 x i32], [100 x i32]* %a, i32 0, i32 %16
  %18 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %19 = call i32 (i8*, ...)* @scanf(i8* %18, i32* %17)
  br label %20

; <label>:20     		; preds = %11
  %21 = load i32, i32* %i, align 4
  %22 = add i32 %21, 1
  store i32 %22, i32* %i, align 4
  br label %6

; <label>:23                                             		; preds = %6
  %24 = getelementptr inbounds [100 x i32], [100 x i32]* %a, i32 0, i32 0
  %25 = load i32, i32* %n, align 4
  call void @bubble(i32* %24, i32 %25)
  %26 = getelementptr inbounds [26 x i8], [26 x i8]* @.str3, i32 0, i32 0
  %27 = call i32 (i8*, ...)* @printf(i8* %26)
  store i32 0, i32* %i, align 4
  br label %28

; <label>:28		; preds = %23, %39
  %29 = load i32, i32* %i, align 4
  %30 = load i32, i32* %n, align 4
  %31 = sub i32 %30, 1
  %32 = icmp sle i32 %29, %31
  br i1 %32, label %33, label %42

; <label>:33                         		; preds = %28
  %34 = load i32, i32* %i, align 4
  %35 = getelementptr inbounds [100 x i32], [100 x i32]* %a, i32 0, i32 %34
  %36 = getelementptr inbounds [4 x i8], [4 x i8]* @.str4, i32 0, i32 0
  %37 = load i32, i32* %35, align 4
  %38 = call i32 (i8*, ...)* @printf(i8* %36, i32 %37)
  br label %39

; <label>:39     		; preds = %33
  %40 = load i32, i32* %i, align 4
  %41 = add i32 %40, 1
  store i32 %41, i32* %i, align 4
  br label %28

; <label>:42                                          		; preds = %28
  %43 = getelementptr inbounds [2 x i8], [2 x i8]* @.str5, i32 0, i32 0
  %44 = call i32 (i8*, ...)* @printf(i8* %43)
  %45 = load i32, i32* %1, align 4
  ret i32 %45
}
