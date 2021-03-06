@glb_val = global i32 5, align 4
@.str = private unnamed_addr constant [2 x i8] c" \00", align 1
@.str1 = private unnamed_addr constant [4 x i8] c"%d \00", align 1
@.str2 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1

declare i32 @printf(i8*, ...) 

define i32 @factorial(i32 %i) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  store i32 %i, i32* %1, align 4
  %3 = load i32, i32* %1, align 4
  %4 = icmp eq i32 %3, 0
  br i1 %4, label %5, label %6

; <label>:5    		; preds = %0
  store i32 1, i32* %2, align 4
  br label %12

; <label>:6        		; preds = %0
  %7 = load i32, i32* %1, align 4
  %8 = load i32, i32* %1, align 4
  %9 = sub i32 %8, 1
  %10 = call i32 @factorial(i32 %9)
  %11 = mul i32 %7, %10
  store i32 %11, i32* %2, align 4
  br label %12

; <label>:12  		; preds = %5, %6
  %13 = load i32, i32* %2, align 4
  ret i32 %13
}

define i32 @main(i32 %argc, i8** %argv) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i8**, align 8
  %3 = alloca i32, align 4
  %n = alloca i32, align 4
  %j = alloca i32, align 4
  %r = alloca i32, align 4
  %n_fact = alloca i32, align 4
  %n_minus_r_fact = alloca i32, align 4
  %r_fact = alloca i32, align 4
  %result = alloca i32, align 4
  store i32 %argc, i32* %1, align 4
  store i8** %argv, i8*** %2, align 8
  store i32 0, i32* %3, align 4
  store i32 0, i32* %n, align 4
  br label %4

; <label>:4       		; preds = %0, %50
  %5 = load i32, i32* %n, align 4
  %6 = load i32, i32* @glb_val, align 4
  %7 = icmp slt i32 %5, %6
  br i1 %7, label %8, label %53

; <label>:8    		; preds = %4
  store i32 0, i32* %j, align 4
  br label %9

; <label>:9        		; preds = %8, %19
  %10 = load i32, i32* %j, align 4
  %11 = load i32, i32* @glb_val, align 4
  %12 = load i32, i32* %n, align 4
  %13 = add i32 %12, 2
  %14 = sub i32 %11, %13
  %15 = icmp sle i32 %10, %14
  br i1 %15, label %16, label %22

; <label>:16                                          		; preds = %9
  %17 = getelementptr inbounds [2 x i8], [2 x i8]* @.str, i32 0, i32 0
  %18 = call i32 (i8*, ...)* @printf(i8* %17)
  br label %19

; <label>:19     		; preds = %16
  %20 = load i32, i32* %j, align 4
  %21 = add i32 %20, 1
  store i32 %21, i32* %j, align 4
  br label %9

; <label>:22   		; preds = %9
  store i32 0, i32* %r, align 4
  br label %23

; <label>:23		; preds = %22, %44
  %24 = load i32, i32* %r, align 4
  %25 = load i32, i32* %n, align 4
  %26 = icmp sle i32 %24, %25
  br i1 %26, label %27, label %47

; <label>:27                         		; preds = %23
  %28 = load i32, i32* %n, align 4
  %29 = call i32 @factorial(i32 %28)
  store i32 %29, i32* %n_fact, align 4
  %30 = load i32, i32* %n, align 4
  %31 = load i32, i32* %r, align 4
  %32 = sub i32 %30, %31
  %33 = call i32 @factorial(i32 %32)
  store i32 %33, i32* %n_minus_r_fact, align 4
  %34 = load i32, i32* %r, align 4
  %35 = call i32 @factorial(i32 %34)
  store i32 %35, i32* %r_fact, align 4
  %36 = load i32, i32* %n_fact, align 4
  %37 = load i32, i32* %n_minus_r_fact, align 4
  %38 = load i32, i32* %r_fact, align 4
  %39 = mul i32 %37, %38
  %40 = sdiv i32 %36, %39
  store i32 %40, i32* %result, align 4
  %41 = getelementptr inbounds [4 x i8], [4 x i8]* @.str1, i32 0, i32 0
  %42 = load i32, i32* %result, align 4
  %43 = call i32 (i8*, ...)* @printf(i8* %41, i32 %42)
  br label %44

; <label>:44     		; preds = %27
  %45 = load i32, i32* %r, align 4
  %46 = add i32 %45, 1
  store i32 %46, i32* %r, align 4
  br label %23

; <label>:47                                          		; preds = %23
  %48 = getelementptr inbounds [2 x i8], [2 x i8]* @.str2, i32 0, i32 0
  %49 = call i32 (i8*, ...)* @printf(i8* %48)
  br label %50

; <label>:50     		; preds = %47
  %51 = load i32, i32* %n, align 4
  %52 = add i32 %51, 1
  store i32 %52, i32* %n, align 4
  br label %4

; <label>:53      		; preds = %4
  %54 = load i32, i32* %3, align 4
  ret i32 %54
}
