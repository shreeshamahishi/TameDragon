@main.a = internal constant [3 x i32] [i32 11, i32 33, i32 95], align 4
@main.b = internal constant [3 x i32] [i32 45, i32 82, i32 94], align 4
@.str = private unnamed_addr constant [33 x i8] c"\0A The first array is: {11,33,95}\00", align 16
@.str1 = private unnamed_addr constant [34 x i8] c"\0A The second array is: {45,82,94}\00", align 16
@.str2 = private unnamed_addr constant [23 x i8] c"\0A The sorted list is: \00", align 16
@.str3 = private unnamed_addr constant [5 x i8] c"\0A %d\00", align 1
@.str4 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1

declare i32 @printf(i8*, ...) 

define void @merge(i32* %left, i32* %right, i32* %result, i32 %nl, i32 %nr) nounwind {
  %1 = alloca i32*, align 8
  %2 = alloca i32*, align 8
  %3 = alloca i32*, align 8
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  %k = alloca i32, align 4
  store i32* %left, i32** %1, align 8
  store i32* %right, i32** %2, align 8
  store i32* %result, i32** %3, align 8
  store i32 %nl, i32* %4, align 4
  store i32 %nr, i32* %5, align 4
  store i32 0, i32* %i, align 4
  store i32 0, i32* %j, align 4
  store i32 0, i32* %k, align 4
  br label %6

; <label>:6 		; preds = %0, %52
  %7 = load i32, i32* %4, align 4
  %8 = icmp sgt i32 %7, 0
  br i1 %8, label %9, label %12

; <label>:9       		; preds = %6
  %10 = load i32, i32* %5, align 4
  %11 = icmp sgt i32 %10, 0
  br label %12

; <label>:12         		; preds = %6, %9
  %13 = phi i1 [ 0, %6 ], [ %11, %9 ]
  br i1 %13, label %14, label %53

; <label>:14                        		; preds = %12
  %15 = load i32*, i32** %1, align 8
  %16 = load i32, i32* %i, align 4
  %17 = getelementptr inbounds i32, i32* %15, i32 %16
  %18 = load i32*, i32** %2, align 8
  %19 = load i32, i32* %j, align 4
  %20 = getelementptr inbounds i32, i32* %18, i32 %19
  %21 = load i32, i32* %17, align 4
  %22 = load i32, i32* %20, align 4
  %23 = icmp sle i32 %21, %22
  br i1 %23, label %24, label %38

; <label>:24                        		; preds = %14
  %25 = load i32*, i32** %1, align 8
  %26 = load i32, i32* %i, align 4
  %27 = getelementptr inbounds i32, i32* %25, i32 %26
  %28 = load i32*, i32** %3, align 8
  %29 = load i32, i32* %k, align 4
  %30 = getelementptr inbounds i32, i32* %28, i32 %29
  %31 = load i32, i32* %27, align 4
  store i32 %31, i32* %30, align 4
  %32 = load i32, i32* %i, align 4
  %33 = add i32 %32, 1
  store i32 %33, i32* %i, align 4
  %34 = load i32, i32* %k, align 4
  %35 = add i32 %34, 1
  store i32 %35, i32* %k, align 4
  %36 = load i32, i32* %4, align 4
  %37 = sub i32 %36, 1
  store i32 %37, i32* %4, align 4
  br label %52

; <label>:38                        		; preds = %14
  %39 = load i32*, i32** %2, align 8
  %40 = load i32, i32* %j, align 4
  %41 = getelementptr inbounds i32, i32* %39, i32 %40
  %42 = load i32*, i32** %3, align 8
  %43 = load i32, i32* %k, align 4
  %44 = getelementptr inbounds i32, i32* %42, i32 %43
  %45 = load i32, i32* %41, align 4
  store i32 %45, i32* %44, align 4
  %46 = load i32, i32* %j, align 4
  %47 = add i32 %46, 1
  store i32 %47, i32* %j, align 4
  %48 = load i32, i32* %k, align 4
  %49 = add i32 %48, 1
  store i32 %49, i32* %k, align 4
  %50 = load i32, i32* %5, align 4
  %51 = sub i32 %50, 1
  store i32 %51, i32* %5, align 4
  br label %52

; <label>:52		; preds = %24, %38
  br label %6

; <label>:53		; preds = %12
  br label %54

; <label>:54		; preds = %53, %57
  %55 = load i32, i32* %4, align 4
  %56 = icmp sgt i32 %55, 0
  br i1 %56, label %57, label %71

; <label>:57                        		; preds = %54
  %58 = load i32*, i32** %1, align 8
  %59 = load i32, i32* %i, align 4
  %60 = getelementptr inbounds i32, i32* %58, i32 %59
  %61 = load i32*, i32** %3, align 8
  %62 = load i32, i32* %k, align 4
  %63 = getelementptr inbounds i32, i32* %61, i32 %62
  %64 = load i32, i32* %60, align 4
  store i32 %64, i32* %63, align 4
  %65 = load i32, i32* %i, align 4
  %66 = add i32 %65, 1
  store i32 %66, i32* %i, align 4
  %67 = load i32, i32* %k, align 4
  %68 = add i32 %67, 1
  store i32 %68, i32* %k, align 4
  %69 = load i32, i32* %4, align 4
  %70 = sub i32 %69, 1
  store i32 %70, i32* %4, align 4
  br label %54

; <label>:71		; preds = %54
  br label %72

; <label>:72		; preds = %71, %75
  %73 = load i32, i32* %5, align 4
  %74 = icmp sgt i32 %73, 0
  br i1 %74, label %75, label %89

; <label>:75                        		; preds = %72
  %76 = load i32*, i32** %2, align 8
  %77 = load i32, i32* %j, align 4
  %78 = getelementptr inbounds i32, i32* %76, i32 %77
  %79 = load i32*, i32** %3, align 8
  %80 = load i32, i32* %k, align 4
  %81 = getelementptr inbounds i32, i32* %79, i32 %80
  %82 = load i32, i32* %78, align 4
  store i32 %82, i32* %81, align 4
  %83 = load i32, i32* %j, align 4
  %84 = add i32 %83, 1
  store i32 %84, i32* %j, align 4
  %85 = load i32, i32* %k, align 4
  %86 = add i32 %85, 1
  store i32 %86, i32* %k, align 4
  %87 = load i32, i32* %5, align 4
  %88 = sub i32 %87, 1
  store i32 %88, i32* %5, align 4
  br label %72

; <label>:89		; preds = %72
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %a = alloca [3 x i32], align 4
  %b = alloca [3 x i32], align 4
  %c = alloca [6 x i32], align 16
  %i = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = bitcast [3 x i32]* %a to i8*
  %3 = bitcast [3 x i32]* @main.a to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %2, i8* %3, i64 12, i32 4, i1 0)
  %4 = bitcast [3 x i32]* %b to i8*
  %5 = bitcast [3 x i32]* @main.b to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %4, i8* %5, i64 12, i32 4, i1 0)
  %6 = getelementptr inbounds [33 x i8], [33 x i8]* @.str, i32 0, i32 0
  %7 = call i32 (i8*, ...)* @printf(i8* %6)
  %8 = getelementptr inbounds [34 x i8], [34 x i8]* @.str1, i32 0, i32 0
  %9 = call i32 (i8*, ...)* @printf(i8* %8)
  %10 = getelementptr inbounds [3 x i32], [3 x i32]* %a, i32 0, i32 0
  %11 = getelementptr inbounds [3 x i32], [3 x i32]* %b, i32 0, i32 0
  %12 = getelementptr inbounds [6 x i32], [6 x i32]* %c, i32 0, i32 0
  call void @merge(i32* %10, i32* %11, i32* %12, i32 3, i32 3)
  %13 = getelementptr inbounds [23 x i8], [23 x i8]* @.str2, i32 0, i32 0
  %14 = call i32 (i8*, ...)* @printf(i8* %13)
  store i32 0, i32* %i, align 4
  br label %15

; <label>:15 		; preds = %0, %24
  %16 = load i32, i32* %i, align 4
  %17 = icmp slt i32 %16, 6
  br i1 %17, label %18, label %27

; <label>:18                         		; preds = %15
  %19 = load i32, i32* %i, align 4
  %20 = getelementptr inbounds [6 x i32], [6 x i32]* %c, i32 0, i32 %19
  %21 = getelementptr inbounds [5 x i8], [5 x i8]* @.str3, i32 0, i32 0
  %22 = load i32, i32* %20, align 4
  %23 = call i32 (i8*, ...)* @printf(i8* %21, i32 %22)
  br label %24

; <label>:24     		; preds = %18
  %25 = load i32, i32* %i, align 4
  %26 = add i32 %25, 1
  store i32 %26, i32* %i, align 4
  br label %15

; <label>:27                                          		; preds = %15
  %28 = getelementptr inbounds [2 x i8], [2 x i8]* @.str4, i32 0, i32 0
  %29 = call i32 (i8*, ...)* @printf(i8* %28)
  %30 = load i32, i32* %1, align 4
  ret i32 %30
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind
