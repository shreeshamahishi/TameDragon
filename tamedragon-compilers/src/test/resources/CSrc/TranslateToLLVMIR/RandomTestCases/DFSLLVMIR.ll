@r = common global i32 0, align 4
@.str = private unnamed_addr constant [4 x i8] c"%d\09\00", align 1
@.str1 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
@.str2 = private unnamed_addr constant [28 x i8] c"Enter source less than %d: \00", align 16
@.str3 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str4 = private unnamed_addr constant [8 x i8] c" -> %d \00", align 1
@.str5 = private unnamed_addr constant [30 x i8] c"Enter the number of vertices\0A\00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

declare noalias i8* @malloc(i64) nounwind

declare i32 @rand() nounwind

define void @printArray([100 x i32]* %a, i32 %n) nounwind {
  %1 = alloca [100 x i32]*, align 8
  %2 = alloca i32, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  store [100 x i32]* %a, [100 x i32]** %1, align 8
  store i32 %n, i32* %2, align 4
  store i32 0, i32* %i, align 4
  br label %3

; <label>:3 		; preds = %0, %27
  %4 = load i32, i32* %i, align 4
  %5 = load i32, i32* %2, align 4
  %6 = icmp slt i32 %4, %5
  br i1 %6, label %7, label %30

; <label>:7    		; preds = %3
  store i32 0, i32* %j, align 4
  br label %8

; <label>:8  		; preds = %7, %21
  %9 = load i32, i32* %j, align 4
  %10 = load i32, i32* %2, align 4
  %11 = icmp slt i32 %9, %10
  br i1 %11, label %12, label %24

; <label>:12                          		; preds = %8
  %13 = load [100 x i32]*, [100 x i32]** %1, align 8
  %14 = load i32, i32* %i, align 4
  %15 = getelementptr inbounds [100 x i32], [100 x i32]* %13, i32 %14
  %16 = load i32, i32* %j, align 4
  %17 = getelementptr inbounds [100 x i32], [100 x i32]* %15, i32 0, i32 %16
  %18 = getelementptr inbounds [4 x i8], [4 x i8]* @.str, i32 0, i32 0
  %19 = load i32, i32* %17, align 4
  %20 = call i32 (i8*, ...)* @printf(i8* %18, i32 %19)
  br label %21

; <label>:21     		; preds = %12
  %22 = load i32, i32* %j, align 4
  %23 = add i32 %22, 1
  store i32 %23, i32* %j, align 4
  br label %8

; <label>:24                                           		; preds = %8
  %25 = getelementptr inbounds [2 x i8], [2 x i8]* @.str1, i32 0, i32 0
  %26 = call i32 (i8*, ...)* @printf(i8* %25)
  br label %27

; <label>:27     		; preds = %24
  %28 = load i32, i32* %i, align 4
  %29 = add i32 %28, 1
  store i32 %29, i32* %i, align 4
  br label %3

; <label>:30		; preds = %3
  ret void
}

define void @AdjacencyMatrix([100 x i32]* %a, i32 %n) nounwind {
  %1 = alloca [100 x i32]*, align 8
  %2 = alloca i32, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  store [100 x i32]* %a, [100 x i32]** %1, align 8
  store i32 %n, i32* %2, align 4
  store i32 0, i32* %i, align 4
  br label %3

; <label>:3 		; preds = %0, %36
  %4 = load i32, i32* %i, align 4
  %5 = load i32, i32* %2, align 4
  %6 = icmp slt i32 %4, %5
  br i1 %6, label %7, label %39

; <label>:7    		; preds = %3
  store i32 0, i32* %j, align 4
  br label %8

; <label>:8  		; preds = %7, %27
  %9 = load i32, i32* %j, align 4
  %10 = load i32, i32* %i, align 4
  %11 = icmp slt i32 %9, %10
  br i1 %11, label %12, label %30

; <label>:12      		; preds = %8
  %13 = call i32 @rand() nounwind
  %14 = srem i32 %13, 2
  %15 = load [100 x i32]*, [100 x i32]** %1, align 8
  %16 = load i32, i32* %i, align 4
  %17 = getelementptr inbounds [100 x i32], [100 x i32]* %15, i32 %16
  %18 = load i32, i32* %j, align 4
  %19 = getelementptr inbounds [100 x i32], [100 x i32]* %17, i32 0, i32 %18
  store i32 %14, i32* %19, align 4
  %20 = call i32 @rand() nounwind
  %21 = srem i32 %20, 2
  %22 = load [100 x i32]*, [100 x i32]** %1, align 8
  %23 = load i32, i32* %j, align 4
  %24 = getelementptr inbounds [100 x i32], [100 x i32]* %22, i32 %23
  %25 = load i32, i32* %i, align 4
  %26 = getelementptr inbounds [100 x i32], [100 x i32]* %24, i32 0, i32 %25
  store i32 %21, i32* %26, align 4
  br label %27

; <label>:27     		; preds = %12
  %28 = load i32, i32* %j, align 4
  %29 = add i32 %28, 1
  store i32 %29, i32* %j, align 4
  br label %8

; <label>:30                                                		; preds = %8
  %31 = load [100 x i32]*, [100 x i32]** %1, align 8
  %32 = load i32, i32* %i, align 4
  %33 = getelementptr inbounds [100 x i32], [100 x i32]* %31, i32 %32
  %34 = load i32, i32* %i, align 4
  %35 = getelementptr inbounds [100 x i32], [100 x i32]* %33, i32 0, i32 %34
  store i32 0, i32* %35, align 4
  br label %36

; <label>:36     		; preds = %30
  %37 = load i32, i32* %i, align 4
  %38 = add i32 %37, 1
  store i32 %38, i32* %i, align 4
  br label %3

; <label>:39                        		; preds = %3
  %40 = load [100 x i32]*, [100 x i32]** %1, align 8
  %41 = load i32, i32* %2, align 4
  call void @printArray([100 x i32]* %40, i32 %41)
  ret void
}

define void @dfs([100 x i32]* %a, i32 %n, i32* %s, i32 %u, i32* %q) nounwind {
  %1 = alloca [100 x i32]*, align 8
  %2 = alloca i32, align 4
  %3 = alloca i32*, align 8
  %4 = alloca i32, align 4
  %5 = alloca i32*, align 8
  %v = alloca i32, align 4
  store [100 x i32]* %a, [100 x i32]** %1, align 8
  store i32 %n, i32* %2, align 4
  store i32* %s, i32** %3, align 8
  store i32 %u, i32* %4, align 4
  store i32* %q, i32** %5, align 8
  %6 = load i32*, i32** %3, align 8
  %7 = load i32, i32* %4, align 4
  %8 = getelementptr inbounds i32, i32* %6, i32 %7
  store i32 1, i32* %8, align 4
  store i32 0, i32* %v, align 4
  br label %9

; <label>:9  		; preds = %0, %41
  %10 = load i32, i32* %v, align 4
  %11 = load i32, i32* %2, align 4
  %12 = icmp slt i32 %10, %11
  br i1 %12, label %13, label %44

; <label>:13                                                		; preds = %9
  %14 = load [100 x i32]*, [100 x i32]** %1, align 8
  %15 = load i32, i32* %4, align 4
  %16 = getelementptr inbounds [100 x i32], [100 x i32]* %14, i32 %15
  %17 = load i32, i32* %v, align 4
  %18 = getelementptr inbounds [100 x i32], [100 x i32]* %16, i32 0, i32 %17
  %19 = load i32, i32* %18, align 4
  %20 = icmp eq i32 %19, 1
  br i1 %20, label %21, label %27

; <label>:21                        		; preds = %13
  %22 = load i32*, i32** %3, align 8
  %23 = load i32, i32* %v, align 4
  %24 = getelementptr inbounds i32, i32* %22, i32 %23
  %25 = load i32, i32* %24, align 4
  %26 = icmp eq i32 %25, 0
  br label %27

; <label>:27         		; preds = %13, %21
  %28 = phi i1 [ false, %13 ], [ %26, %21 ]
  br i1 %28, label %29, label %40

; <label>:29                                           		; preds = %27
  %30 = load i32, i32* @r, align 4
  %31 = add i32 %30, 1
  store i32 %31, i32* @r, align 4
  %32 = load i32*, i32** %5, align 8
  %33 = getelementptr inbounds i32, i32* %32, i32 %31
  %34 = load i32, i32* %v, align 4
  store i32 %34, i32* %33, align 4
  %35 = load [100 x i32]*, [100 x i32]** %1, align 8
  %36 = load i32, i32* %2, align 4
  %37 = load i32*, i32** %3, align 8
  %38 = load i32, i32* %v, align 4
  %39 = load i32*, i32** %5, align 8
  call void @dfs([100 x i32]* %35, i32 %36, i32* %37, i32 %38, i32* %39)
  br label %40

; <label>:40		; preds = %27, %29
  br label %41

; <label>:41     		; preds = %40
  %42 = load i32, i32* %v, align 4
  %43 = add i32 %42, 1
  store i32 %43, i32* %v, align 4
  br label %9

; <label>:44		; preds = %9
  ret void
}

define void @printdfs([100 x i32]* %a, i32 %n, i32* %q) nounwind {
  %1 = alloca [100 x i32]*, align 8
  %2 = alloca i32, align 4
  %3 = alloca i32*, align 8
  %i = alloca i32, align 4
  %sor = alloca i32, align 4
  %s = alloca [100 x i32], align 16
  store [100 x i32]* %a, [100 x i32]** %1, align 8
  store i32 %n, i32* %2, align 4
  store i32* %q, i32** %3, align 8
  %4 = getelementptr inbounds [28 x i8], [28 x i8]* @.str2, i32 0, i32 0
  %5 = load i32, i32* %2, align 4
  %6 = call i32 (i8*, ...)* @printf(i8* %4, i32 %5)
  %7 = getelementptr inbounds [3 x i8], [3 x i8]* @.str3, i32 0, i32 0
  %8 = call i32 (i8*, ...)* @scanf(i8* %7, i32* %sor)
  store i32 0, i32* %i, align 4
  br label %9

; <label>:9  		; preds = %0, %16
  %10 = load i32, i32* %i, align 4
  %11 = load i32, i32* %2, align 4
  %12 = icmp slt i32 %10, %11
  br i1 %12, label %13, label %19

; <label>:13                                               		; preds = %9
  %14 = load i32, i32* %i, align 4
  %15 = getelementptr inbounds [100 x i32], [100 x i32]* %s, i32 0, i32 %14
  store i32 0, i32* %15, align 4
  br label %16

; <label>:16     		; preds = %13
  %17 = load i32, i32* %i, align 4
  %18 = add i32 %17, 1
  store i32 %18, i32* %i, align 4
  br label %9

; <label>:19                                            		; preds = %9
  %20 = load i32*, i32** %3, align 8
  %21 = getelementptr inbounds i32, i32* %20, i32 0
  %22 = load i32, i32* %sor, align 4
  store i32 %22, i32* %21, align 4
  %23 = load [100 x i32]*, [100 x i32]** %1, align 8
  %24 = load i32, i32* %2, align 4
  %25 = getelementptr inbounds [100 x i32], [100 x i32]* %s, i32 0, i32 0
  %26 = load i32, i32* %sor, align 4
  %27 = load i32*, i32** %3, align 8
  call void @dfs([100 x i32]* %23, i32 %24, i32* %25, i32 %26, i32* %27)
  store i32 0, i32* %i, align 4
  br label %28

; <label>:28		; preds = %19, %45
  %29 = load i32, i32* %i, align 4
  %30 = load i32, i32* %2, align 4
  %31 = icmp slt i32 %29, %30
  br i1 %31, label %32, label %48

; <label>:32      		; preds = %28
  %33 = load i32, i32* %i, align 4
  %34 = getelementptr inbounds [100 x i32], [100 x i32]* %s, i32 0, i32 %33
  %35 = load i32, i32* %34, align 4
  %36 = icmp ne i32 %35, 0
  br i1 %36, label %37, label %44

; <label>:37                         		; preds = %32
  %38 = load i32*, i32** %3, align 8
  %39 = load i32, i32* %i, align 4
  %40 = getelementptr inbounds i32, i32* %38, i32 %39
  %41 = getelementptr inbounds [8 x i8], [8 x i8]* @.str4, i32 0, i32 0
  %42 = load i32, i32* %40, align 4
  %43 = call i32 (i8*, ...)* @printf(i8* %41, i32 %42)
  br label %44

; <label>:44		; preds = %32, %37
  br label %45

; <label>:45     		; preds = %44
  %46 = load i32, i32* %i, align 4
  %47 = add i32 %46, 1
  store i32 %47, i32* %i, align 4
  br label %28

; <label>:48		; preds = %28
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %a = alloca [100 x [100 x i32]], align 16
  %n = alloca i32, align 4
  %q = alloca i32*, align 8
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [30 x i8], [30 x i8]* @.str5, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [3 x i8], [3 x i8]* @.str3, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %n)
  %6 = load i32, i32* %n, align 4
  %7 = mul i32 4, %6
  %8 = sext i32 %7 to i64
  %9 = call i8* @malloc(i64 %8) nounwind
  %10 = bitcast i8* %9 to i32*
  store i32* %10, i32** %q, align 8
  %11 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %a, i32 0, i32 0
  %12 = load i32, i32* %n, align 4
  call void @AdjacencyMatrix([100 x i32]* %11, i32 %12)
  %13 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %a, i32 0, i32 0
  %14 = load i32, i32* %n, align 4
  %15 = load i32*, i32** %q, align 8
  call void @printdfs([100 x i32]* %13, i32 %14, i32* %15)
  ret i32 0
}
