@.str = private unnamed_addr constant [26 x i8] c"Enter number of vertices \00", align 16
@.str1 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str2 = private unnamed_addr constant [24 x i8] c"\0AEnter number of edges \00", align 16
@.str3 = private unnamed_addr constant [16 x i8] c"\0AEnter vertex1 \00", align 16
@.str4 = private unnamed_addr constant [16 x i8] c"\0AEnter vertex2 \00", align 16
@.str5 = private unnamed_addr constant [19 x i8] c"\0AEnter the weight \00", align 16
@.str6 = private unnamed_addr constant [53 x i8] c"The shortest distance between %d and %d is Infinity\0A\00", align 16
@.str7 = private unnamed_addr constant [47 x i8] c"The shortest distance between %d and %d is %d\0A\00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define i32 @min(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %ret = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = load i32, i32* %1, align 4
  %4 = load i32, i32* %2, align 4
  %5 = icmp slt i32 %3, %4
  br i1 %5, label %6, label %8

; <label>:6      		; preds = %0
  %7 = load i32, i32* %1, align 4
  br label %10

; <label>:8      		; preds = %0
  %9 = load i32, i32* %2, align 4
  br label %10

; <label>:10      		; preds = %6, %8
  %11 = phi i32 [ %7, %6 ], [ %9, %8 ]
  store i32 %11, i32* %ret, align 4
  %12 = load i32, i32* %ret, align 4
  ret i32 %12
}

define void @init([100 x i32]* %distance) nounwind {
  %1 = alloca [100 x i32]*, align 8
  %iter = alloca i32, align 4
  %jter = alloca i32, align 4
  store [100 x i32]* %distance, [100 x i32]** %1, align 8
  store i32 0, i32* %iter, align 4
  br label %2

; <label>:2    		; preds = %0, %30
  %3 = load i32, i32* %iter, align 4
  %4 = icmp slt i32 %3, 100
  br i1 %4, label %5, label %33

; <label>:5       		; preds = %2
  store i32 0, i32* %jter, align 4
  br label %6

; <label>:6    		; preds = %5, %26
  %7 = load i32, i32* %jter, align 4
  %8 = icmp slt i32 %7, 100
  br i1 %8, label %9, label %29

; <label>:9          		; preds = %6
  %10 = load i32, i32* %iter, align 4
  %11 = load i32, i32* %jter, align 4
  %12 = icmp eq i32 %10, %11
  br i1 %12, label %13, label %19

; <label>:13                                                		; preds = %9
  %14 = load [100 x i32]*, [100 x i32]** %1, align 8
  %15 = load i32, i32* %iter, align 4
  %16 = getelementptr inbounds [100 x i32], [100 x i32]* %14, i32 %15
  %17 = load i32, i32* %jter, align 4
  %18 = getelementptr inbounds [100 x i32], [100 x i32]* %16, i32 0, i32 %17
  store i32 0, i32* %18, align 4
  br label %25

; <label>:19                                                		; preds = %9
  %20 = load [100 x i32]*, [100 x i32]** %1, align 8
  %21 = load i32, i32* %iter, align 4
  %22 = getelementptr inbounds [100 x i32], [100 x i32]* %20, i32 %21
  %23 = load i32, i32* %jter, align 4
  %24 = getelementptr inbounds [100 x i32], [100 x i32]* %22, i32 0, i32 %23
  store i32 123456789, i32* %24, align 4
  br label %25

; <label>:25		; preds = %13, %19
  br label %26

; <label>:26        		; preds = %25
  %27 = load i32, i32* %jter, align 4
  %28 = add i32 %27, 1
  store i32 %28, i32* %jter, align 4
  br label %6

; <label>:29		; preds = %6
  br label %30

; <label>:30        		; preds = %29
  %31 = load i32, i32* %iter, align 4
  %32 = add i32 %31, 1
  store i32 %32, i32* %iter, align 4
  br label %2

; <label>:33		; preds = %2
  ret void
}

define void @FloydWarshall([100 x i32]* %distance, i32 %vertices) nounwind {
  %1 = alloca [100 x i32]*, align 8
  %2 = alloca i32, align 4
  %from = alloca i32, align 4
  %to = alloca i32, align 4
  %via = alloca i32, align 4
  store [100 x i32]* %distance, [100 x i32]** %1, align 8
  store i32 %vertices, i32* %2, align 4
  store i32 0, i32* %from, align 4
  br label %3

; <label>:3    		; preds = %0, %51
  %4 = load i32, i32* %from, align 4
  %5 = load i32, i32* %2, align 4
  %6 = icmp slt i32 %4, %5
  br i1 %6, label %7, label %54

; <label>:7     		; preds = %3
  store i32 0, i32* %to, align 4
  br label %8

; <label>:8  		; preds = %7, %47
  %9 = load i32, i32* %to, align 4
  %10 = load i32, i32* %2, align 4
  %11 = icmp slt i32 %9, %10
  br i1 %11, label %12, label %50

; <label>:12     		; preds = %8
  store i32 0, i32* %via, align 4
  br label %13

; <label>:13  		; preds = %12, %43
  %14 = load i32, i32* %via, align 4
  %15 = load i32, i32* %2, align 4
  %16 = icmp slt i32 %14, %15
  br i1 %16, label %17, label %46

; <label>:17                                               		; preds = %13
  %18 = load [100 x i32]*, [100 x i32]** %1, align 8
  %19 = load i32, i32* %from, align 4
  %20 = getelementptr inbounds [100 x i32], [100 x i32]* %18, i32 %19
  %21 = load i32, i32* %via, align 4
  %22 = getelementptr inbounds [100 x i32], [100 x i32]* %20, i32 0, i32 %21
  %23 = load [100 x i32]*, [100 x i32]** %1, align 8
  %24 = load i32, i32* %via, align 4
  %25 = getelementptr inbounds [100 x i32], [100 x i32]* %23, i32 %24
  %26 = load i32, i32* %to, align 4
  %27 = getelementptr inbounds [100 x i32], [100 x i32]* %25, i32 0, i32 %26
  %28 = load i32, i32* %22, align 4
  %29 = load i32, i32* %27, align 4
  %30 = add i32 %28, %29
  %31 = load [100 x i32]*, [100 x i32]** %1, align 8
  %32 = load i32, i32* %from, align 4
  %33 = getelementptr inbounds [100 x i32], [100 x i32]* %31, i32 %32
  %34 = load i32, i32* %to, align 4
  %35 = getelementptr inbounds [100 x i32], [100 x i32]* %33, i32 0, i32 %34
  %36 = load i32, i32* %35, align 4
  %37 = call i32 @min(i32 %36, i32 %30)
  %38 = load [100 x i32]*, [100 x i32]** %1, align 8
  %39 = load i32, i32* %from, align 4
  %40 = getelementptr inbounds [100 x i32], [100 x i32]* %38, i32 %39
  %41 = load i32, i32* %to, align 4
  %42 = getelementptr inbounds [100 x i32], [100 x i32]* %40, i32 0, i32 %41
  store i32 %37, i32* %42, align 4
  br label %43

; <label>:43       		; preds = %17
  %44 = load i32, i32* %via, align 4
  %45 = add i32 %44, 1
  store i32 %45, i32* %via, align 4
  br label %13

; <label>:46		; preds = %13
  br label %47

; <label>:47      		; preds = %46
  %48 = load i32, i32* %to, align 4
  %49 = add i32 %48, 1
  store i32 %49, i32* %to, align 4
  br label %8

; <label>:50		; preds = %8
  br label %51

; <label>:51        		; preds = %50
  %52 = load i32, i32* %from, align 4
  %53 = add i32 %52, 1
  store i32 %53, i32* %from, align 4
  br label %3

; <label>:54		; preds = %3
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %graph = alloca [100 x [100 x i32]], align 16
  %size = alloca [100 x i32], align 16
  %visited = alloca [100 x i32], align 16
  %distance = alloca [100 x [100 x i32]], align 16
  %vertices = alloca i32, align 4
  %edges = alloca i32, align 4
  %iter = alloca i32, align 4
  %jter = alloca i32, align 4
  %vertex1 = alloca i32, align 4
  %vertex2 = alloca i32, align 4
  %weight = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = bitcast [100 x i32]* %size to i8*
  call void @llvm.memset.p0i8.i64(i8* %2, i8 0, i64 400, i32 16, i1 false)
  %3 = getelementptr inbounds [100 x i32], [100 x i32]* %size, i32 0, i32 0
  store i32 0, i32* %3, align 4
  %4 = bitcast [100 x i32]* %visited to i8*
  call void @llvm.memset.p0i8.i64(i8* %4, i8 0, i64 400, i32 16, i1 false)
  %5 = getelementptr inbounds [100 x i32], [100 x i32]* %visited, i32 0, i32 0
  store i32 0, i32* %5, align 4
  %6 = getelementptr inbounds [26 x i8], [26 x i8]* @.str, i32 0, i32 0
  %7 = call i32 (i8*, ...)* @printf(i8* %6)
  %8 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %9 = call i32 (i8*, ...)* @scanf(i8* %8, i32* %vertices)
  %10 = getelementptr inbounds [24 x i8], [24 x i8]* @.str2, i32 0, i32 0
  %11 = call i32 (i8*, ...)* @printf(i8* %10)
  %12 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %13 = call i32 (i8*, ...)* @scanf(i8* %12, i32* %edges)
  %14 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %distance, i32 0, i32 0
  call void @init([100 x i32]* %14)
  store i32 0, i32* %iter, align 4
  br label %15

; <label>:15     		; preds = %0, %42
  %16 = load i32, i32* %iter, align 4
  %17 = load i32, i32* %edges, align 4
  %18 = icmp slt i32 %16, %17
  br i1 %18, label %19, label %45

; <label>:19                                               		; preds = %15
  %20 = getelementptr inbounds [16 x i8], [16 x i8]* @.str3, i32 0, i32 0
  %21 = call i32 (i8*, ...)* @printf(i8* %20)
  %22 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %23 = call i32 (i8*, ...)* @scanf(i8* %22, i32* %vertex1)
  %24 = getelementptr inbounds [16 x i8], [16 x i8]* @.str4, i32 0, i32 0
  %25 = call i32 (i8*, ...)* @printf(i8* %24)
  %26 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %27 = call i32 (i8*, ...)* @scanf(i8* %26, i32* %vertex2)
  %28 = getelementptr inbounds [19 x i8], [19 x i8]* @.str5, i32 0, i32 0
  %29 = call i32 (i8*, ...)* @printf(i8* %28)
  %30 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %31 = call i32 (i8*, ...)* @scanf(i8* %30, i32* %weight)
  %32 = load i32, i32* %vertex1, align 4
  %33 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %graph, i32 0, i32 %32
  %34 = load i32, i32* %vertex2, align 4
  %35 = getelementptr inbounds [100 x i32], [100 x i32]* %33, i32 0, i32 %34
  %36 = load i32, i32* %weight, align 4
  store i32 %36, i32* %35, align 4
  %37 = load i32, i32* %vertex1, align 4
  %38 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %distance, i32 0, i32 %37
  %39 = load i32, i32* %vertex2, align 4
  %40 = getelementptr inbounds [100 x i32], [100 x i32]* %38, i32 0, i32 %39
  %41 = load i32, i32* %weight, align 4
  store i32 %41, i32* %40, align 4
  br label %42

; <label>:42        		; preds = %19
  %43 = load i32, i32* %iter, align 4
  %44 = add i32 %43, 1
  store i32 %44, i32* %iter, align 4
  br label %15

; <label>:45                                                                   		; preds = %15
  %46 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %distance, i32 0, i32 0
  %47 = load i32, i32* %vertices, align 4
  call void @FloydWarshall([100 x i32]* %46, i32 %47)
  store i32 0, i32* %iter, align 4
  br label %48

; <label>:48       		; preds = %45, %84
  %49 = load i32, i32* %iter, align 4
  %50 = load i32, i32* %vertices, align 4
  %51 = icmp slt i32 %49, %50
  br i1 %51, label %52, label %87

; <label>:52     		; preds = %48
  store i32 0, i32* %jter, align 4
  br label %53

; <label>:53       		; preds = %52, %80
  %54 = load i32, i32* %jter, align 4
  %55 = load i32, i32* %vertices, align 4
  %56 = icmp slt i32 %54, %55
  br i1 %56, label %57, label %83

; <label>:57                                               		; preds = %53
  %58 = load i32, i32* %iter, align 4
  %59 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %distance, i32 0, i32 %58
  %60 = load i32, i32* %jter, align 4
  %61 = getelementptr inbounds [100 x i32], [100 x i32]* %59, i32 0, i32 %60
  %62 = load i32, i32* %61, align 4
  %63 = icmp sge i32 %62, 123456789
  br i1 %63, label %64, label %69

; <label>:64                                            		; preds = %57
  %65 = getelementptr inbounds [53 x i8], [53 x i8]* @.str6, i32 0, i32 0
  %66 = load i32, i32* %iter, align 4
  %67 = load i32, i32* %jter, align 4
  %68 = call i32 (i8*, ...)* @printf(i8* %65, i32 %66, i32 %67)
  br label %79

; <label>:69                                           		; preds = %57
  %70 = load i32, i32* %iter, align 4
  %71 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %distance, i32 0, i32 %70
  %72 = load i32, i32* %jter, align 4
  %73 = getelementptr inbounds [100 x i32], [100 x i32]* %71, i32 0, i32 %72
  %74 = getelementptr inbounds [47 x i8], [47 x i8]* @.str7, i32 0, i32 0
  %75 = load i32, i32* %iter, align 4
  %76 = load i32, i32* %jter, align 4
  %77 = load i32, i32* %73, align 4
  %78 = call i32 (i8*, ...)* @printf(i8* %74, i32 %75, i32 %76, i32 %77)
  br label %79

; <label>:79		; preds = %64, %69
  br label %80

; <label>:80        		; preds = %79
  %81 = load i32, i32* %jter, align 4
  %82 = add i32 %81, 1
  store i32 %82, i32* %jter, align 4
  br label %53

; <label>:83		; preds = %53
  br label %84

; <label>:84        		; preds = %83
  %85 = load i32, i32* %iter, align 4
  %86 = add i32 %85, 1
  store i32 %86, i32* %iter, align 4
  br label %48

; <label>:87		; preds = %48
  ret i32 0
}

declare void @llvm.memset.p0i8.i64(i8* nocapture, i8, i64, i32, i1) nounwind
