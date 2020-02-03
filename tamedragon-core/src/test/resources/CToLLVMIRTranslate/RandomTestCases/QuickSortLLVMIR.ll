@.str = private unnamed_addr constant [26 x i8] c"Enter size of the array: \00", align 16
@.str1 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str2 = private unnamed_addr constant [20 x i8] c"Enter %d elements: \00", align 16
@.str3 = private unnamed_addr constant [18 x i8] c"Sorted elements: \00", align 16
@.str4 = private unnamed_addr constant [4 x i8] c" %d\00", align 1
@.str5 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define void @quicksort(i32* %x, i32 %first, i32 %last) nounwind {
  %1 = alloca i32*, align 8
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %pivot = alloca i32, align 4
  %j = alloca i32, align 4
  %temp = alloca i32, align 4
  %i = alloca i32, align 4
  store i32* %x, i32** %1, align 8
  store i32 %first, i32* %2, align 4
  store i32 %last, i32* %3, align 4
  %4 = load i32, i32* %2, align 4
  %5 = load i32, i32* %3, align 4
  %6 = icmp slt i32 %4, %5
  br i1 %6, label %7, label %94

; <label>:7       		; preds = %0
  %8 = load i32, i32* %2, align 4
  store i32 %8, i32* %pivot, align 4
  %9 = load i32, i32* %2, align 4
  store i32 %9, i32* %i, align 4
  %10 = load i32, i32* %3, align 4
  store i32 %10, i32* %j, align 4
  br label %11

; <label>:11 		; preds = %7, %69
  %12 = load i32, i32* %i, align 4
  %13 = load i32, i32* %j, align 4
  %14 = icmp slt i32 %12, %13
  br i1 %14, label %15, label %70

; <label>:15		; preds = %11
  br label %16

; <label>:16                   		; preds = %15, %32
  %17 = load i32*, i32** %1, align 8
  %18 = load i32, i32* %i, align 4
  %19 = getelementptr inbounds i32, i32* %17, i32 %18
  %20 = load i32*, i32** %1, align 8
  %21 = load i32, i32* %pivot, align 4
  %22 = getelementptr inbounds i32, i32* %20, i32 %21
  %23 = load i32, i32* %19, align 4
  %24 = load i32, i32* %22, align 4
  %25 = icmp sle i32 %23, %24
  br i1 %25, label %26, label %30

; <label>:26     		; preds = %16
  %27 = load i32, i32* %i, align 4
  %28 = load i32, i32* %3, align 4
  %29 = icmp slt i32 %27, %28
  br label %30

; <label>:30         		; preds = %16, %26
  %31 = phi i1 [ false, %16 ], [ %29, %26 ]
  br i1 %31, label %32, label %35

; <label>:32     		; preds = %30
  %33 = load i32, i32* %i, align 4
  %34 = add i32 %33, 1
  store i32 %34, i32* %i, align 4
  br label %16

; <label>:35		; preds = %30
  br label %36

; <label>:36                   		; preds = %35, %46
  %37 = load i32*, i32** %1, align 8
  %38 = load i32, i32* %j, align 4
  %39 = getelementptr inbounds i32, i32* %37, i32 %38
  %40 = load i32*, i32** %1, align 8
  %41 = load i32, i32* %pivot, align 4
  %42 = getelementptr inbounds i32, i32* %40, i32 %41
  %43 = load i32, i32* %39, align 4
  %44 = load i32, i32* %42, align 4
  %45 = icmp sgt i32 %43, %44
  br i1 %45, label %46, label %49

; <label>:46     		; preds = %36
  %47 = load i32, i32* %j, align 4
  %48 = sub i32 %47, 1
  store i32 %48, i32* %j, align 4
  br label %36

; <label>:49     		; preds = %36
  %50 = load i32, i32* %i, align 4
  %51 = load i32, i32* %j, align 4
  %52 = icmp slt i32 %50, %51
  br i1 %52, label %53, label %69

; <label>:53        		; preds = %49
  %54 = load i32*, i32** %1, align 8
  %55 = load i32, i32* %i, align 4
  %56 = getelementptr inbounds i32, i32* %54, i32 %55
  %57 = load i32, i32* %56, align 4
  store i32 %57, i32* %temp, align 4
  %58 = load i32*, i32** %1, align 8
  %59 = load i32, i32* %j, align 4
  %60 = getelementptr inbounds i32, i32* %58, i32 %59
  %61 = load i32*, i32** %1, align 8
  %62 = load i32, i32* %i, align 4
  %63 = getelementptr inbounds i32, i32* %61, i32 %62
  %64 = load i32, i32* %60, align 4
  store i32 %64, i32* %63, align 4
  %65 = load i32*, i32** %1, align 8
  %66 = load i32, i32* %j, align 4
  %67 = getelementptr inbounds i32, i32* %65, i32 %66
  %68 = load i32, i32* %temp, align 4
  store i32 %68, i32* %67, align 4
  br label %69

; <label>:69		; preds = %49, %53
  br label %11

; <label>:70                     		; preds = %11
  %71 = load i32*, i32** %1, align 8
  %72 = load i32, i32* %pivot, align 4
  %73 = getelementptr inbounds i32, i32* %71, i32 %72
  %74 = load i32, i32* %73, align 4
  store i32 %74, i32* %temp, align 4
  %75 = load i32*, i32** %1, align 8
  %76 = load i32, i32* %j, align 4
  %77 = getelementptr inbounds i32, i32* %75, i32 %76
  %78 = load i32*, i32** %1, align 8
  %79 = load i32, i32* %pivot, align 4
  %80 = getelementptr inbounds i32, i32* %78, i32 %79
  %81 = load i32, i32* %77, align 4
  store i32 %81, i32* %80, align 4
  %82 = load i32*, i32** %1, align 8
  %83 = load i32, i32* %j, align 4
  %84 = getelementptr inbounds i32, i32* %82, i32 %83
  %85 = load i32, i32* %temp, align 4
  store i32 %85, i32* %84, align 4
  %86 = load i32*, i32** %1, align 8
  %87 = load i32, i32* %2, align 4
  %88 = load i32, i32* %j, align 4
  %89 = sub i32 %88, 1
  call void @quicksort(i32* %86, i32 %87, i32 %89)
  %90 = load i32*, i32** %1, align 8
  %91 = load i32, i32* %j, align 4
  %92 = add i32 %91, 1
  %93 = load i32, i32* %3, align 4
  call void @quicksort(i32* %90, i32 %92, i32 %93)
  br label %94

; <label>:94		; preds = %0, %70
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %x = alloca [20 x i32], align 16
  %size = alloca i32, align 4
  %i = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [26 x i8], [26 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %size)
  %6 = getelementptr inbounds [20 x i8], [20 x i8]* @.str2, i32 0, i32 0
  %7 = load i32, i32* %size, align 4
  %8 = call i32 (i8*, ...)* @printf(i8* %6, i32 %7)
  store i32 0, i32* %i, align 4
  br label %9

; <label>:9     		; preds = %0, %18
  %10 = load i32, i32* %i, align 4
  %11 = load i32, i32* %size, align 4
  %12 = icmp slt i32 %10, %11
  br i1 %12, label %13, label %21

; <label>:13                          		; preds = %9
  %14 = load i32, i32* %i, align 4
  %15 = getelementptr inbounds [20 x i32], [20 x i32]* %x, i32 0, i32 %14
  %16 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %17 = call i32 (i8*, ...)* @scanf(i8* %16, i32* %15)
  br label %18

; <label>:18     		; preds = %13
  %19 = load i32, i32* %i, align 4
  %20 = add i32 %19, 1
  store i32 %20, i32* %i, align 4
  br label %9

; <label>:21                                             		; preds = %9
  %22 = getelementptr inbounds [20 x i32], [20 x i32]* %x, i32 0, i32 0
  %23 = load i32, i32* %size, align 4
  %24 = sub i32 %23, 1
  call void @quicksort(i32* %22, i32 0, i32 %24)
  %25 = getelementptr inbounds [18 x i8], [18 x i8]* @.str3, i32 0, i32 0
  %26 = call i32 (i8*, ...)* @printf(i8* %25)
  store i32 0, i32* %i, align 4
  br label %27

; <label>:27   		; preds = %21, %37
  %28 = load i32, i32* %i, align 4
  %29 = load i32, i32* %size, align 4
  %30 = icmp slt i32 %28, %29
  br i1 %30, label %31, label %40

; <label>:31                         		; preds = %27
  %32 = load i32, i32* %i, align 4
  %33 = getelementptr inbounds [20 x i32], [20 x i32]* %x, i32 0, i32 %32
  %34 = getelementptr inbounds [4 x i8], [4 x i8]* @.str4, i32 0, i32 0
  %35 = load i32, i32* %33, align 4
  %36 = call i32 (i8*, ...)* @printf(i8* %34, i32 %35)
  br label %37

; <label>:37     		; preds = %31
  %38 = load i32, i32* %i, align 4
  %39 = add i32 %38, 1
  store i32 %39, i32* %i, align 4
  br label %27

; <label>:40                                          		; preds = %27
  %41 = getelementptr inbounds [2 x i8], [2 x i8]* @.str5, i32 0, i32 0
  %42 = call i32 (i8*, ...)* @printf(i8* %41)
  ret i32 0
}
