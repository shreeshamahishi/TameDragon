@.str = private unnamed_addr constant [35 x i8] c"The shortest distance to %d is %d\0A\00", align 16
@.str1 = private unnamed_addr constant [26 x i8] c"Enter number of vertices \00", align 16
@.str2 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str3 = private unnamed_addr constant [24 x i8] c"\0AEnter number of edges \00", align 16
@.str4 = private unnamed_addr constant [16 x i8] c"\0AEnter vertex1 \00", align 16
@.str5 = private unnamed_addr constant [16 x i8] c"\0AEnter vertex2 \00", align 16
@.str6 = private unnamed_addr constant [19 x i8] c"\0AEnter the weight \00", align 16
@.str7 = private unnamed_addr constant [15 x i8] c"\0AEnter Source \00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define void @BellmanFord([100 x i32]* %graph, [100 x i32]* %cost, i32* %size, i32 %source, i32 %vertices) nounwind {
  %1 = alloca [100 x i32]*, align 8
  %2 = alloca [100 x i32]*, align 8
  %3 = alloca i32*, align 8
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  %6 = alloca i8*, align 8
  %iter = alloca i32, align 4
  %jter = alloca i32, align 4
  %from = alloca i32, align 4
  %to = alloca i32, align 4
  store [100 x i32]* %graph, [100 x i32]** %1, align 8
  store [100 x i32]* %cost, [100 x i32]** %2, align 8
  store i32* %size, i32** %3, align 8
  store i32 %source, i32* %4, align 4
  store i32 %vertices, i32* %5, align 4
  %7 = call i8* @llvm.stacksave()
  store i8* %7, i8** %6, align 8
  %8 = load i32, i32* %5, align 4
  %9 = mul i32 4, %8
  %10 = alloca i8, i32 %9, align 1
  %11 = bitcast i8* %10 to i32*
  store i32 0, i32* %iter, align 4
  br label %12

; <label>:12    		; preds = %0, %19
  %13 = load i32, i32* %iter, align 4
  %14 = load i32, i32* %5, align 4
  %15 = icmp slt i32 %13, %14
  br i1 %15, label %16, label %22

; <label>:16           		; preds = %12
  %17 = load i32, i32* %iter, align 4
  %18 = getelementptr inbounds i32, i32* %11, i32 %17
  store i32 123456789, i32* %18, align 4
  br label %19

; <label>:19        		; preds = %16
  %20 = load i32, i32* %iter, align 4
  %21 = add i32 %20, 1
  store i32 %21, i32* %iter, align 4
  br label %12

; <label>:22                        		; preds = %12
  %23 = load i32, i32* %4, align 4
  %24 = getelementptr inbounds i32, i32* %11, i32 %23
  store i32 0, i32* %24, align 4
  store i32 0, i32* %iter, align 4
  br label %25

; <label>:25   		; preds = %22, %86
  %26 = load i32, i32* %iter, align 4
  %27 = load i32, i32* %5, align 4
  %28 = sub i32 %27, 1
  %29 = icmp slt i32 %26, %28
  br i1 %29, label %30, label %89

; <label>:30     		; preds = %25
  store i32 0, i32* %from, align 4
  br label %31

; <label>:31   		; preds = %30, %82
  %32 = load i32, i32* %from, align 4
  %33 = load i32, i32* %5, align 4
  %34 = icmp slt i32 %32, %33
  br i1 %34, label %35, label %85

; <label>:35     		; preds = %31
  store i32 0, i32* %jter, align 4
  br label %36

; <label>:36                   		; preds = %35, %78
  %37 = load i32, i32* %jter, align 4
  %38 = load i32*, i32** %3, align 8
  %39 = load i32, i32* %from, align 4
  %40 = getelementptr inbounds i32, i32* %38, i32 %39
  %41 = load i32, i32* %40, align 4
  %42 = icmp slt i32 %37, %41
  br i1 %42, label %43, label %81

; <label>:43                        		; preds = %36
  %44 = load [100 x i32]*, [100 x i32]** %1, align 8
  %45 = load i32, i32* %from, align 4
  %46 = getelementptr inbounds [100 x i32], [100 x i32]* %44, i32 %45
  %47 = load i32, i32* %jter, align 4
  %48 = getelementptr inbounds [100 x i32], [100 x i32]* %46, i32 0, i32 %47
  %49 = load i32, i32* %48, align 4
  store i32 %49, i32* %to, align 4
  %50 = load i32, i32* %from, align 4
  %51 = getelementptr inbounds i32, i32* %11, i32 %50
  %52 = load [100 x i32]*, [100 x i32]** %2, align 8
  %53 = load i32, i32* %from, align 4
  %54 = getelementptr inbounds [100 x i32], [100 x i32]* %52, i32 %53
  %55 = load i32, i32* %jter, align 4
  %56 = getelementptr inbounds [100 x i32], [100 x i32]* %54, i32 0, i32 %55
  %57 = load i32, i32* %51, align 4
  %58 = load i32, i32* %56, align 4
  %59 = add i32 %57, %58
  %60 = load i32, i32* %to, align 4
  %61 = getelementptr inbounds i32, i32* %11, i32 %60
  %62 = load i32, i32* %61, align 4
  %63 = icmp slt i32 %59, %62
  br i1 %63, label %64, label %77

; <label>:64                        		; preds = %43
  %65 = load i32, i32* %from, align 4
  %66 = getelementptr inbounds i32, i32* %11, i32 %65
  %67 = load [100 x i32]*, [100 x i32]** %2, align 8
  %68 = load i32, i32* %from, align 4
  %69 = getelementptr inbounds [100 x i32], [100 x i32]* %67, i32 %68
  %70 = load i32, i32* %jter, align 4
  %71 = getelementptr inbounds [100 x i32], [100 x i32]* %69, i32 0, i32 %70
  %72 = load i32, i32* %66, align 4
  %73 = load i32, i32* %71, align 4
  %74 = add i32 %72, %73
  %75 = load i32, i32* %to, align 4
  %76 = getelementptr inbounds i32, i32* %11, i32 %75
  store i32 %74, i32* %76, align 4
  br label %77

; <label>:77		; preds = %43, %64
  br label %78

; <label>:78        		; preds = %77
  %79 = load i32, i32* %jter, align 4
  %80 = add i32 %79, 1
  store i32 %80, i32* %jter, align 4
  br label %36

; <label>:81		; preds = %36
  br label %82

; <label>:82        		; preds = %81
  %83 = load i32, i32* %from, align 4
  %84 = add i32 %83, 1
  store i32 %84, i32* %from, align 4
  br label %31

; <label>:85		; preds = %31
  br label %86

; <label>:86        		; preds = %85
  %87 = load i32, i32* %iter, align 4
  %88 = add i32 %87, 1
  store i32 %88, i32* %iter, align 4
  br label %25

; <label>:89     		; preds = %25
  store i32 0, i32* %iter, align 4
  br label %90

; <label>:90  		; preds = %89, %101
  %91 = load i32, i32* %iter, align 4
  %92 = load i32, i32* %5, align 4
  %93 = icmp slt i32 %91, %92
  br i1 %93, label %94, label %104

; <label>:94                                   		; preds = %90
  %95 = load i32, i32* %iter, align 4
  %96 = getelementptr inbounds i32, i32* %11, i32 %95
  %97 = getelementptr inbounds [35 x i8], [35 x i8]* @.str, i32 0, i32 0
  %98 = load i32, i32* %iter, align 4
  %99 = load i32, i32* %96, align 4
  %100 = call i32 (i8*, ...)* @printf(i8* %97, i32 %98, i32 %99)
  br label %101

; <label>:101        		; preds = %94
  %102 = load i32, i32* %iter, align 4
  %103 = add i32 %102, 1
  store i32 %103, i32* %iter, align 4
  br label %90

; <label>:104          		; preds = %90
  %105 = load i8*, i8** %6, align 8
  call void @llvm.stackrestore(i8* %105)
  ret void
}

declare i8* @llvm.stacksave() nounwind

declare void @llvm.stackrestore(i8*) nounwind

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %graph = alloca [100 x [100 x i32]], align 16
  %size = alloca [100 x i32], align 16
  %visited = alloca [100 x i32], align 16
  %cost = alloca [100 x [100 x i32]], align 16
  %vertices = alloca i32, align 4
  %edges = alloca i32, align 4
  %iter = alloca i32, align 4
  %jter = alloca i32, align 4
  %vertex1 = alloca i32, align 4
  %vertex2 = alloca i32, align 4
  %weight = alloca i32, align 4
  %source = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = bitcast [100 x i32]* %size to i8*
  call void @llvm.memset.p0i8.i64(i8* %2, i8 0, i64 400, i32 16, i1 false)
  %3 = getelementptr inbounds [100 x i32], [100 x i32]* %size, i32 0, i32 0
  store i32 0, i32* %3, align 4
  %4 = bitcast [100 x i32]* %visited to i8*
  call void @llvm.memset.p0i8.i64(i8* %4, i8 0, i64 400, i32 16, i1 false)
  %5 = getelementptr inbounds [100 x i32], [100 x i32]* %visited, i32 0, i32 0
  store i32 0, i32* %5, align 4
  %6 = getelementptr inbounds [26 x i8], [26 x i8]* @.str1, i32 0, i32 0
  %7 = call i32 (i8*, ...)* @printf(i8* %6)
  %8 = getelementptr inbounds [3 x i8], [3 x i8]* @.str2, i32 0, i32 0
  %9 = call i32 (i8*, ...)* @scanf(i8* %8, i32* %vertices)
  %10 = getelementptr inbounds [24 x i8], [24 x i8]* @.str3, i32 0, i32 0
  %11 = call i32 (i8*, ...)* @printf(i8* %10)
  %12 = getelementptr inbounds [3 x i8], [3 x i8]* @.str2, i32 0, i32 0
  %13 = call i32 (i8*, ...)* @scanf(i8* %12, i32* %edges)
  store i32 0, i32* %iter, align 4
  br label %14

; <label>:14     		; preds = %0, %49
  %15 = load i32, i32* %iter, align 4
  %16 = load i32, i32* %edges, align 4
  %17 = icmp slt i32 %15, %16
  br i1 %17, label %18, label %52

; <label>:18                                                 		; preds = %14
  %19 = getelementptr inbounds [16 x i8], [16 x i8]* @.str4, i32 0, i32 0
  %20 = call i32 (i8*, ...)* @printf(i8* %19)
  %21 = getelementptr inbounds [3 x i8], [3 x i8]* @.str2, i32 0, i32 0
  %22 = call i32 (i8*, ...)* @scanf(i8* %21, i32* %vertex1)
  %23 = getelementptr inbounds [16 x i8], [16 x i8]* @.str5, i32 0, i32 0
  %24 = call i32 (i8*, ...)* @printf(i8* %23)
  %25 = getelementptr inbounds [3 x i8], [3 x i8]* @.str2, i32 0, i32 0
  %26 = call i32 (i8*, ...)* @scanf(i8* %25, i32* %vertex2)
  %27 = getelementptr inbounds [19 x i8], [19 x i8]* @.str6, i32 0, i32 0
  %28 = call i32 (i8*, ...)* @printf(i8* %27)
  %29 = getelementptr inbounds [3 x i8], [3 x i8]* @.str2, i32 0, i32 0
  %30 = call i32 (i8*, ...)* @scanf(i8* %29, i32* %weight)
  %31 = load i32, i32* %vertex1, align 4
  %32 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %graph, i32 0, i32 %31
  %33 = load i32, i32* %vertex1, align 4
  %34 = getelementptr inbounds [100 x i32], [100 x i32]* %size, i32 0, i32 %33
  %35 = load i32, i32* %34, align 4
  %36 = getelementptr inbounds [100 x i32], [100 x i32]* %32, i32 0, i32 %35
  %37 = load i32, i32* %vertex2, align 4
  store i32 %37, i32* %36, align 4
  %38 = load i32, i32* %vertex1, align 4
  %39 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %cost, i32 0, i32 %38
  %40 = load i32, i32* %vertex1, align 4
  %41 = getelementptr inbounds [100 x i32], [100 x i32]* %size, i32 0, i32 %40
  %42 = load i32, i32* %41, align 4
  %43 = getelementptr inbounds [100 x i32], [100 x i32]* %39, i32 0, i32 %42
  %44 = load i32, i32* %weight, align 4
  store i32 %44, i32* %43, align 4
  %45 = load i32, i32* %vertex1, align 4
  %46 = getelementptr inbounds [100 x i32], [100 x i32]* %size, i32 0, i32 %45
  %47 = load i32, i32* %46, align 4
  %48 = add i32 %47, 1
  store i32 %48, i32* %46, align 4
  br label %49

; <label>:49        		; preds = %18
  %50 = load i32, i32* %iter, align 4
  %51 = add i32 %50, 1
  store i32 %51, i32* %iter, align 4
  br label %14

; <label>:52                                                           		; preds = %14
  %53 = getelementptr inbounds [15 x i8], [15 x i8]* @.str7, i32 0, i32 0
  %54 = call i32 (i8*, ...)* @printf(i8* %53)
  %55 = getelementptr inbounds [3 x i8], [3 x i8]* @.str2, i32 0, i32 0
  %56 = call i32 (i8*, ...)* @scanf(i8* %55, i32* %source)
  %57 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %graph, i32 0, i32 0
  %58 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %cost, i32 0, i32 0
  %59 = getelementptr inbounds [100 x i32], [100 x i32]* %size, i32 0, i32 0
  %60 = load i32, i32* %source, align 4
  %61 = load i32, i32* %vertices, align 4
  call void @BellmanFord([100 x i32]* %57, [100 x i32]* %58, i32* %59, i32 %60, i32 %61)
  ret i32 0
}

declare void @llvm.memset.p0i8.i64(i8* nocapture, i8, i64, i32, i1) nounwind
