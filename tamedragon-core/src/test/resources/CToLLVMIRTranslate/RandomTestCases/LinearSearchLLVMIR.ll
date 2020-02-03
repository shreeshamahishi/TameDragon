@.str = private unnamed_addr constant [39 x i8] c"Enter the number of elements in array\0A\00", align 16
@.str1 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str2 = private unnamed_addr constant [18 x i8] c"Enter %d numbers\0A\00", align 16
@.str3 = private unnamed_addr constant [28 x i8] c"Enter the number to search\0A\00", align 16
@.str4 = private unnamed_addr constant [31 x i8] c"%d is present at location %d.\0A\00", align 16
@.str5 = private unnamed_addr constant [29 x i8] c"%d is not present in array.\0A\00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %array = alloca [100 x i32], align 16
  %search = alloca i32, align 4
  %c = alloca i32, align 4
  %number = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [39 x i8], [39 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %number)
  %6 = getelementptr inbounds [18 x i8], [18 x i8]* @.str2, i32 0, i32 0
  %7 = load i32, i32* %number, align 4
  %8 = call i32 (i8*, ...)* @printf(i8* %6, i32 %7)
  store i32 0, i32* %c, align 4
  br label %9

; <label>:9       		; preds = %0, %18
  %10 = load i32, i32* %c, align 4
  %11 = load i32, i32* %number, align 4
  %12 = icmp slt i32 %10, %11
  br i1 %12, label %13, label %21

; <label>:13                          		; preds = %9
  %14 = load i32, i32* %c, align 4
  %15 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 %14
  %16 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %17 = call i32 (i8*, ...)* @scanf(i8* %16, i32* %15)
  br label %18

; <label>:18     		; preds = %13
  %19 = load i32, i32* %c, align 4
  %20 = add i32 %19, 1
  store i32 %20, i32* %c, align 4
  br label %9

; <label>:21                                             		; preds = %9
  %22 = getelementptr inbounds [28 x i8], [28 x i8]* @.str3, i32 0, i32 0
  %23 = call i32 (i8*, ...)* @printf(i8* %22)
  %24 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %25 = call i32 (i8*, ...)* @scanf(i8* %24, i32* %search)
  store i32 0, i32* %c, align 4
  br label %26

; <label>:26     		; preds = %21, %43
  %27 = load i32, i32* %c, align 4
  %28 = load i32, i32* %number, align 4
  %29 = icmp slt i32 %27, %28
  br i1 %29, label %30, label %46

; <label>:30      		; preds = %26
  %31 = load i32, i32* %c, align 4
  %32 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 %31
  %33 = load i32, i32* %search, align 4
  %34 = load i32, i32* %32, align 4
  %35 = icmp eq i32 %34, %33
  br i1 %35, label %36, label %42

; <label>:36                                            		; preds = %30
  %37 = getelementptr inbounds [31 x i8], [31 x i8]* @.str4, i32 0, i32 0
  %38 = load i32, i32* %search, align 4
  %39 = load i32, i32* %c, align 4
  %40 = add i32 %39, 1
  %41 = call i32 (i8*, ...)* @printf(i8* %37, i32 %38, i32 %40)
  br label %46

; <label>:42		; preds = %30
  br label %43

; <label>:43     		; preds = %42
  %44 = load i32, i32* %c, align 4
  %45 = add i32 %44, 1
  store i32 %45, i32* %c, align 4
  br label %26

; <label>:46     		; preds = %26, %36
  %47 = load i32, i32* %c, align 4
  %48 = load i32, i32* %number, align 4
  %49 = icmp eq i32 %47, %48
  br i1 %49, label %50, label %54

; <label>:50                                            		; preds = %46
  %51 = getelementptr inbounds [29 x i8], [29 x i8]* @.str5, i32 0, i32 0
  %52 = load i32, i32* %search, align 4
  %53 = call i32 (i8*, ...)* @printf(i8* %51, i32 %52)
  br label %54

; <label>:54		; preds = %46, %50
  ret i32 0
}
