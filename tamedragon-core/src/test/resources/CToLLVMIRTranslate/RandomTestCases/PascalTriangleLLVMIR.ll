@.str = private unnamed_addr constant [61 x i8] c"Enter the number of rows you wish to see in pascal triangle\0A\00", align 16
@.str1 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str2 = private unnamed_addr constant [2 x i8] c" \00", align 1
@.str3 = private unnamed_addr constant [5 x i8] c"%ld \00", align 1
@.str4 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define i64 @factorial(i32 %n) nounwind {
  %1 = alloca i32, align 4
  %c = alloca i32, align 4
  %result = alloca i64, align 8
  store i32 %n, i32* %1, align 4
  store i64 1, i64* %result, align 8
  store i32 1, i32* %c, align 4
  br label %2

; <label>:2 		; preds = %0, %12
  %3 = load i32, i32* %c, align 4
  %4 = load i32, i32* %1, align 4
  %5 = icmp sle i32 %3, %4
  br i1 %5, label %6, label %15

; <label>:6           		; preds = %2
  %7 = load i64, i64* %result, align 8
  %8 = load i32, i32* %c, align 4
  %9 = trunc i64 %7 to i32
  %10 = mul i32 %9, %8
  %11 = sext i32 %10 to i64
  store i64 %11, i64* %result, align 8
  br label %12

; <label>:12      		; preds = %6
  %13 = load i32, i32* %c, align 4
  %14 = add i32 %13, 1
  store i32 %14, i32* %c, align 4
  br label %2

; <label>:15           		; preds = %2
  %16 = load i64, i64* %result, align 8
  ret i64 %16
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %i = alloca i32, align 4
  %n = alloca i32, align 4
  %c = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [61 x i8], [61 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %n)
  store i32 0, i32* %i, align 4
  br label %6

; <label>:6 		; preds = %0, %48
  %7 = load i32, i32* %i, align 4
  %8 = load i32, i32* %n, align 4
  %9 = icmp slt i32 %7, %8
  br i1 %9, label %10, label %51

; <label>:10   		; preds = %6
  store i32 0, i32* %c, align 4
  br label %11

; <label>:11		; preds = %10, %21
  %12 = load i32, i32* %c, align 4
  %13 = load i32, i32* %n, align 4
  %14 = load i32, i32* %i, align 4
  %15 = sub i32 %13, %14
  %16 = sub i32 %15, 2
  %17 = icmp sle i32 %12, %16
  br i1 %17, label %18, label %24

; <label>:18                                          		; preds = %11
  %19 = getelementptr inbounds [2 x i8], [2 x i8]* @.str2, i32 0, i32 0
  %20 = call i32 (i8*, ...)* @printf(i8* %19)
  br label %21

; <label>:21     		; preds = %18
  %22 = load i32, i32* %c, align 4
  %23 = add i32 %22, 1
  store i32 %23, i32* %c, align 4
  br label %11

; <label>:24  		; preds = %11
  store i32 0, i32* %c, align 4
  br label %25

; <label>:25		; preds = %24, %42
  %26 = load i32, i32* %c, align 4
  %27 = load i32, i32* %i, align 4
  %28 = icmp sle i32 %26, %27
  br i1 %28, label %29, label %45

; <label>:29                                          		; preds = %25
  %30 = getelementptr inbounds [5 x i8], [5 x i8]* @.str3, i32 0, i32 0
  %31 = load i32, i32* %i, align 4
  %32 = call i64 @factorial(i32 %31)
  %33 = load i32, i32* %c, align 4
  %34 = call i64 @factorial(i32 %33)
  %35 = load i32, i32* %i, align 4
  %36 = load i32, i32* %c, align 4
  %37 = sub i32 %35, %36
  %38 = call i64 @factorial(i32 %37)
  %39 = mul i64 %34, %38
  %40 = sdiv i64 %32, %39
  %41 = call i32 (i8*, ...)* @printf(i8* %30, i64 %40)
  br label %42

; <label>:42     		; preds = %29
  %43 = load i32, i32* %c, align 4
  %44 = add i32 %43, 1
  store i32 %44, i32* %c, align 4
  br label %25

; <label>:45                                          		; preds = %25
  %46 = getelementptr inbounds [2 x i8], [2 x i8]* @.str4, i32 0, i32 0
  %47 = call i32 (i8*, ...)* @printf(i8* %46)
  br label %48

; <label>:48     		; preds = %45
  %49 = load i32, i32* %i, align 4
  %50 = add i32 %49, 1
  store i32 %50, i32* %i, align 4
  br label %6

; <label>:51		; preds = %6
  ret i32 0
}
