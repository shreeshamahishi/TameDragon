@main.arr = internal constant [5 x i32] [i32 25, i32 17, i32 31, i32 13, i32 2], align 16
@.str = private unnamed_addr constant [17 x i8] c"Insertion sort.\0A\00", align 16
@.str1 = private unnamed_addr constant [24 x i8] c"\0AArray before sorting:\0A\00", align 16
@.str2 = private unnamed_addr constant [4 x i8] c"%d \00", align 1
@.str3 = private unnamed_addr constant [24 x i8] c"\0A\0AArray after sorting:\0A\00", align 16
@.str4 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1

declare i32 @printf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %arr = alloca [5 x i32], align 16
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  %k = alloca i32, align 4
  %temp = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = bitcast [5 x i32]* %arr to i8*
  %3 = bitcast [5 x i32]* @main.arr to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %2, i8* %3, i64 20, i32 16, i1 false)
  %4 = getelementptr inbounds [17 x i8], [17 x i8]* @.str, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @printf(i8* %4)
  %6 = getelementptr inbounds [24 x i8], [24 x i8]* @.str1, i32 0, i32 0
  %7 = call i32 (i8*, ...)* @printf(i8* %6)
  store i32 0, i32* %i, align 4
  br label %8

; <label>:8 		; preds = %0, %17
  %9 = load i32, i32* %i, align 4
  %10 = icmp sle i32 %9, 4
  br i1 %10, label %11, label %20

; <label>:11                          		; preds = %8
  %12 = load i32, i32* %i, align 4
  %13 = getelementptr inbounds [5 x i32], [5 x i32]* %arr, i32 0, i32 %12
  %14 = getelementptr inbounds [4 x i8], [4 x i8]* @.str2, i32 0, i32 0
  %15 = load i32, i32* %13, align 4
  %16 = call i32 (i8*, ...)* @printf(i8* %14, i32 %15)
  br label %17

; <label>:17     		; preds = %11
  %18 = load i32, i32* %i, align 4
  %19 = add i32 %18, 1
  store i32 %19, i32* %i, align 4
  br label %8

; <label>:20   		; preds = %8
  store i32 1, i32* %i, align 4
  br label %21

; <label>:21		; preds = %20, %71
  %22 = load i32, i32* %i, align 4
  %23 = icmp sle i32 %22, 4
  br i1 %23, label %24, label %74

; <label>:24  		; preds = %21
  store i32 0, i32* %j, align 4
  br label %25

; <label>:25		; preds = %24, %67
  %26 = load i32, i32* %j, align 4
  %27 = load i32, i32* %i, align 4
  %28 = icmp slt i32 %26, %27
  br i1 %28, label %29, label %70

; <label>:29      		; preds = %25
  %30 = load i32, i32* %j, align 4
  %31 = getelementptr inbounds [5 x i32], [5 x i32]* %arr, i32 0, i32 %30
  %32 = load i32, i32* %i, align 4
  %33 = getelementptr inbounds [5 x i32], [5 x i32]* %arr, i32 0, i32 %32
  %34 = load i32, i32* %31, align 4
  %35 = load i32, i32* %33, align 4
  %36 = icmp sgt i32 %34, %35
  br i1 %36, label %37, label %66

; <label>:37      		; preds = %29
  %38 = load i32, i32* %j, align 4
  %39 = getelementptr inbounds [5 x i32], [5 x i32]* %arr, i32 0, i32 %38
  %40 = load i32, i32* %39, align 4
  store i32 %40, i32* %temp, align 4
  %41 = load i32, i32* %i, align 4
  %42 = getelementptr inbounds [5 x i32], [5 x i32]* %arr, i32 0, i32 %41
  %43 = load i32, i32* %j, align 4
  %44 = getelementptr inbounds [5 x i32], [5 x i32]* %arr, i32 0, i32 %43
  %45 = load i32, i32* %42, align 4
  store i32 %45, i32* %44, align 4
  %46 = load i32, i32* %i, align 4
  store i32 %46, i32* %k, align 4
  br label %47

; <label>:47		; preds = %37, %58
  %48 = load i32, i32* %k, align 4
  %49 = load i32, i32* %j, align 4
  %50 = icmp sgt i32 %48, %49
  br i1 %50, label %51, label %61

; <label>:51      		; preds = %47
  %52 = load i32, i32* %k, align 4
  %53 = sub i32 %52, 1
  %54 = getelementptr inbounds [5 x i32], [5 x i32]* %arr, i32 0, i32 %53
  %55 = load i32, i32* %k, align 4
  %56 = getelementptr inbounds [5 x i32], [5 x i32]* %arr, i32 0, i32 %55
  %57 = load i32, i32* %54, align 4
  store i32 %57, i32* %56, align 4
  br label %58

; <label>:58     		; preds = %51
  %59 = load i32, i32* %k, align 4
  %60 = sub i32 %59, 1
  store i32 %60, i32* %k, align 4
  br label %47

; <label>:61        		; preds = %47
  %62 = load i32, i32* %k, align 4
  %63 = add i32 %62, 1
  %64 = getelementptr inbounds [5 x i32], [5 x i32]* %arr, i32 0, i32 %63
  %65 = load i32, i32* %temp, align 4
  store i32 %65, i32* %64, align 4
  br label %66

; <label>:66		; preds = %29, %61
  br label %67

; <label>:67     		; preds = %66
  %68 = load i32, i32* %j, align 4
  %69 = add i32 %68, 1
  store i32 %69, i32* %j, align 4
  br label %25

; <label>:70		; preds = %25
  br label %71

; <label>:71     		; preds = %70
  %72 = load i32, i32* %i, align 4
  %73 = add i32 %72, 1
  store i32 %73, i32* %i, align 4
  br label %21

; <label>:74                                            		; preds = %21
  %75 = getelementptr inbounds [24 x i8], [24 x i8]* @.str3, i32 0, i32 0
  %76 = call i32 (i8*, ...)* @printf(i8* %75)
  store i32 0, i32* %i, align 4
  br label %77

; <label>:77		; preds = %74, %86
  %78 = load i32, i32* %i, align 4
  %79 = icmp sle i32 %78, 4
  br i1 %79, label %80, label %89

; <label>:80                         		; preds = %77
  %81 = load i32, i32* %i, align 4
  %82 = getelementptr inbounds [5 x i32], [5 x i32]* %arr, i32 0, i32 %81
  %83 = getelementptr inbounds [4 x i8], [4 x i8]* @.str2, i32 0, i32 0
  %84 = load i32, i32* %82, align 4
  %85 = call i32 (i8*, ...)* @printf(i8* %83, i32 %84)
  br label %86

; <label>:86     		; preds = %80
  %87 = load i32, i32* %i, align 4
  %88 = add i32 %87, 1
  store i32 %88, i32* %i, align 4
  br label %77

; <label>:89                                          		; preds = %77
  %90 = getelementptr inbounds [2 x i8], [2 x i8]* @.str4, i32 0, i32 0
  %91 = call i32 (i8*, ...)* @printf(i8* %90)
  %92 = load i32, i32* %1, align 4
  ret i32 %92
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind
