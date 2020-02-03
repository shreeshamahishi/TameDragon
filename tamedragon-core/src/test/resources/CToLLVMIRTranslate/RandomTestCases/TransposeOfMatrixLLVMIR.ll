@.str = private unnamed_addr constant [48 x i8] c"Enter the number of rows and columns of matrix \00", align 16
@.str1 = private unnamed_addr constant [5 x i8] c"%d%d\00", align 1
@.str2 = private unnamed_addr constant [31 x i8] c"Enter the elements of matrix \0A\00", align 16
@.str3 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str4 = private unnamed_addr constant [32 x i8] c"Transpose of entered matrix :-\0A\00", align 16
@.str5 = private unnamed_addr constant [4 x i8] c"%d\09\00", align 1
@.str6 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %m = alloca i32, align 4
  %n = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca i32, align 4
  %matrix = alloca [10 x [10 x i32]], align 16
  %transpose = alloca [10 x [10 x i32]], align 16
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [48 x i8], [48 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [5 x i8], [5 x i8]* @.str1, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %m, i32* %n)
  %6 = getelementptr inbounds [31 x i8], [31 x i8]* @.str2, i32 0, i32 0
  %7 = call i32 (i8*, ...)* @printf(i8* %6)
  store i32 0, i32* %c, align 4
  br label %8

; <label>:8  		; preds = %0, %28
  %9 = load i32, i32* %c, align 4
  %10 = load i32, i32* %m, align 4
  %11 = icmp slt i32 %9, %10
  br i1 %11, label %12, label %31

; <label>:12   		; preds = %8
  store i32 0, i32* %d, align 4
  br label %13

; <label>:13		; preds = %12, %24
  %14 = load i32, i32* %d, align 4
  %15 = load i32, i32* %n, align 4
  %16 = icmp slt i32 %14, %15
  br i1 %16, label %17, label %27

; <label>:17                         		; preds = %13
  %18 = load i32, i32* %c, align 4
  %19 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* %matrix, i32 0, i32 %18
  %20 = load i32, i32* %d, align 4
  %21 = getelementptr inbounds [10 x i32], [10 x i32]* %19, i32 0, i32 %20
  %22 = getelementptr inbounds [3 x i8], [3 x i8]* @.str3, i32 0, i32 0
  %23 = call i32 (i8*, ...)* @scanf(i8* %22, i32* %21)
  br label %24

; <label>:24     		; preds = %17
  %25 = load i32, i32* %d, align 4
  %26 = add i32 %25, 1
  store i32 %26, i32* %d, align 4
  br label %13

; <label>:27		; preds = %13
  br label %28

; <label>:28     		; preds = %27
  %29 = load i32, i32* %c, align 4
  %30 = add i32 %29, 1
  store i32 %30, i32* %c, align 4
  br label %8

; <label>:31   		; preds = %8
  store i32 0, i32* %c, align 4
  br label %32

; <label>:32		; preds = %31, %55
  %33 = load i32, i32* %c, align 4
  %34 = load i32, i32* %m, align 4
  %35 = icmp slt i32 %33, %34
  br i1 %35, label %36, label %58

; <label>:36  		; preds = %32
  store i32 0, i32* %d, align 4
  br label %37

; <label>:37		; preds = %36, %51
  %38 = load i32, i32* %d, align 4
  %39 = load i32, i32* %n, align 4
  %40 = icmp slt i32 %38, %39
  br i1 %40, label %41, label %54

; <label>:41      		; preds = %37
  %42 = load i32, i32* %c, align 4
  %43 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* %matrix, i32 0, i32 %42
  %44 = load i32, i32* %d, align 4
  %45 = getelementptr inbounds [10 x i32], [10 x i32]* %43, i32 0, i32 %44
  %46 = load i32, i32* %d, align 4
  %47 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* %transpose, i32 0, i32 %46
  %48 = load i32, i32* %c, align 4
  %49 = getelementptr inbounds [10 x i32], [10 x i32]* %47, i32 0, i32 %48
  %50 = load i32, i32* %45, align 4
  store i32 %50, i32* %49, align 4
  br label %51

; <label>:51     		; preds = %41
  %52 = load i32, i32* %d, align 4
  %53 = add i32 %52, 1
  store i32 %53, i32* %d, align 4
  br label %37

; <label>:54		; preds = %37
  br label %55

; <label>:55     		; preds = %54
  %56 = load i32, i32* %c, align 4
  %57 = add i32 %56, 1
  store i32 %57, i32* %c, align 4
  br label %32

; <label>:58                                            		; preds = %32
  %59 = getelementptr inbounds [32 x i8], [32 x i8]* @.str4, i32 0, i32 0
  %60 = call i32 (i8*, ...)* @printf(i8* %59)
  store i32 0, i32* %c, align 4
  br label %61

; <label>:61		; preds = %58, %84
  %62 = load i32, i32* %c, align 4
  %63 = load i32, i32* %n, align 4
  %64 = icmp slt i32 %62, %63
  br i1 %64, label %65, label %87

; <label>:65  		; preds = %61
  store i32 0, i32* %d, align 4
  br label %66

; <label>:66		; preds = %65, %78
  %67 = load i32, i32* %d, align 4
  %68 = load i32, i32* %m, align 4
  %69 = icmp slt i32 %67, %68
  br i1 %69, label %70, label %81

; <label>:70                         		; preds = %66
  %71 = load i32, i32* %c, align 4
  %72 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* %transpose, i32 0, i32 %71
  %73 = load i32, i32* %d, align 4
  %74 = getelementptr inbounds [10 x i32], [10 x i32]* %72, i32 0, i32 %73
  %75 = getelementptr inbounds [4 x i8], [4 x i8]* @.str5, i32 0, i32 0
  %76 = load i32, i32* %74, align 4
  %77 = call i32 (i8*, ...)* @printf(i8* %75, i32 %76)
  br label %78

; <label>:78     		; preds = %70
  %79 = load i32, i32* %d, align 4
  %80 = add i32 %79, 1
  store i32 %80, i32* %d, align 4
  br label %66

; <label>:81                                          		; preds = %66
  %82 = getelementptr inbounds [2 x i8], [2 x i8]* @.str6, i32 0, i32 0
  %83 = call i32 (i8*, ...)* @printf(i8* %82)
  br label %84

; <label>:84     		; preds = %81
  %85 = load i32, i32* %c, align 4
  %86 = add i32 %85, 1
  store i32 %86, i32* %c, align 4
  br label %61

; <label>:87		; preds = %61
  ret i32 0
}
