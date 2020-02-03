@.str = private unnamed_addr constant [37 x i8] c"The non-trivial factors of %d are: \0A\00", align 16
@.str1 = private unnamed_addr constant [5 x i8] c"\09%d\0A\00", align 1
@.str2 = private unnamed_addr constant [13 x i8] c"%d is prime\0A\00", align 1

declare i32 @printf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %n = alloca i32, align 4
  %lcv = alloca i32, align 4
  %flag = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 11, i32* %n, align 4
  store i32 2, i32* %lcv, align 4
  store i32 1, i32* %flag, align 4
  br label %2

; <label>:2   		; preds = %0, %24
  %3 = load i32, i32* %lcv, align 4
  %4 = load i32, i32* %n, align 4
  %5 = sdiv i32 %4, 2
  %6 = icmp sle i32 %3, %5
  br i1 %6, label %7, label %27

; <label>:7        		; preds = %2
  %8 = load i32, i32* %n, align 4
  %9 = load i32, i32* %lcv, align 4
  %10 = srem i32 %8, %9
  %11 = icmp eq i32 %10, 0
  br i1 %11, label %12, label %23

; <label>:12         		; preds = %7
  %13 = load i32, i32* %flag, align 4
  %14 = icmp ne i32 %13, 0
  br i1 %14, label %15, label %19

; <label>:15                                           		; preds = %12
  %16 = getelementptr inbounds [37 x i8], [37 x i8]* @.str, i32 0, i32 0
  %17 = load i32, i32* %n, align 4
  %18 = call i32 (i8*, ...)* @printf(i8* %16, i32 %17)
  br label %19

; <label>:19                    		; preds = %12, %15
  store i32 0, i32* %flag, align 4
  %20 = getelementptr inbounds [5 x i8], [5 x i8]* @.str1, i32 0, i32 0
  %21 = load i32, i32* %lcv, align 4
  %22 = call i32 (i8*, ...)* @printf(i8* %20, i32 %21)
  br label %23

; <label>:23		; preds = %7, %19
  br label %24

; <label>:24       		; preds = %23
  %25 = load i32, i32* %lcv, align 4
  %26 = add i32 %25, 1
  store i32 %26, i32* %lcv, align 4
  br label %2

; <label>:27         		; preds = %2
  %28 = load i32, i32* %flag, align 4
  %29 = icmp ne i32 %28, 0
  br i1 %29, label %30, label %34

; <label>:30                                            		; preds = %27
  %31 = getelementptr inbounds [13 x i8], [13 x i8]* @.str2, i32 0, i32 0
  %32 = load i32, i32* %n, align 4
  %33 = call i32 (i8*, ...)* @printf(i8* %31, i32 %32)
  br label %34

; <label>:34		; preds = %27, %30
  %35 = load i32, i32* %1, align 4
  ret i32 %35
}
