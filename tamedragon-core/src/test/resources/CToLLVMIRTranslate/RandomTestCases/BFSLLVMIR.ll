@a = common global [20 x [20 x i32]] zeroinitializer, align 16
@q = common global [20 x i32] zeroinitializer, align 16
@visited = common global [20 x i32] zeroinitializer, align 16
@n = common global i32 0, align 4
@i = common global i32 0, align 4
@j = common global i32 0, align 4
@f = global i32 0, align 4
@r = global i32 -1, align 4
@.str = private unnamed_addr constant [32 x i8] c"\0A Enter the number of vertices:\00", align 16
@.str1 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str2 = private unnamed_addr constant [36 x i8] c"\0A Enter graph data in matrix form:\0A\00", align 16
@.str3 = private unnamed_addr constant [29 x i8] c"\0A Enter the starting vertex:\00", align 16
@.str4 = private unnamed_addr constant [37 x i8] c"\0A The node which are reachable are:\0A\00", align 16
@.str5 = private unnamed_addr constant [4 x i8] c"%d\09\00", align 1
@.str6 = private unnamed_addr constant [22 x i8] c"\0A Bfs is not possible\00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define void @bfs(i32 %v) nounwind {
  %1 = alloca i32, align 4
  store i32 %v, i32* %1, align 4
  store i32 1, i32* @i, align 4
  br label %2

; <label>:2 		; preds = %0, %29
  %3 = load i32, i32* @i, align 4
  %4 = load i32, i32* @n, align 4
  %5 = icmp sle i32 %3, %4
  br i1 %5, label %6, label %32

; <label>:6        		; preds = %2
  %7 = load i32, i32* %1, align 4
  %8 = getelementptr inbounds [20 x [20 x i32]], [20 x [20 x i32]]* @a, i32 0, i32 %7
  %9 = load i32, i32* @i, align 4
  %10 = getelementptr inbounds [20 x i32], [20 x i32]* %8, i32 0, i32 %9
  %11 = load i32, i32* %10, align 4
  %12 = icmp ne i32 %11, 0
  br i1 %12, label %13, label %21

; <label>:13       		; preds = %6
  %14 = load i32, i32* @i, align 4
  %15 = getelementptr inbounds [20 x i32], [20 x i32]* @visited, i32 0, i32 %14
  %16 = load i32, i32* %15, align 4
  %17 = icmp ne i32 %16, 0
  %18 = xor i1 %17, true
  %19 = zext i1 %18 to i32
  %20 = icmp ne i32 %19, 0
  br label %21

; <label>:21         		; preds = %6, %13
  %22 = phi i1 [ false, %6 ], [ %20, %13 ]
  br i1 %22, label %23, label %28

; <label>:23                                            		; preds = %21
  %24 = load i32, i32* @r, align 4
  %25 = add i32 %24, 1
  store i32 %25, i32* @r, align 4
  %26 = getelementptr inbounds [20 x i32], [20 x i32]* @q, i32 0, i32 %25
  %27 = load i32, i32* @i, align 4
  store i32 %27, i32* %26, align 4
  br label %28

; <label>:28		; preds = %21, %23
  br label %29

; <label>:29     		; preds = %28
  %30 = load i32, i32* @i, align 4
  %31 = add i32 %30, 1
  store i32 %31, i32* @i, align 4
  br label %2

; <label>:32      		; preds = %2
  %33 = load i32, i32* @f, align 4
  %34 = load i32, i32* @r, align 4
  %35 = icmp sle i32 %33, %34
  br i1 %35, label %36, label %45

; <label>:36      		; preds = %32
  %37 = load i32, i32* @f, align 4
  %38 = getelementptr inbounds [20 x i32], [20 x i32]* @q, i32 0, i32 %37
  %39 = load i32, i32* %38, align 4
  %40 = getelementptr inbounds [20 x i32], [20 x i32]* @visited, i32 0, i32 %39
  store i32 1, i32* %40, align 4
  %41 = load i32, i32* @f, align 4
  %42 = add i32 %41, 1
  store i32 %42, i32* @f, align 4
  %43 = getelementptr inbounds [20 x i32], [20 x i32]* @q, i32 0, i32 %41
  %44 = load i32, i32* %43, align 4
  call void @bfs(i32 %44)
  br label %45

; <label>:45		; preds = %32, %36
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %v = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [32 x i8], [32 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* @n)
  store i32 1, i32* @i, align 4
  br label %6

; <label>:6 		; preds = %0, %15
  %7 = load i32, i32* @i, align 4
  %8 = load i32, i32* @n, align 4
  %9 = icmp sle i32 %7, %8
  br i1 %9, label %10, label %18

; <label>:10                                                   		; preds = %6
  %11 = load i32, i32* @i, align 4
  %12 = getelementptr inbounds [20 x i32], [20 x i32]* @q, i32 0, i32 %11
  store i32 0, i32* %12, align 4
  %13 = load i32, i32* @i, align 4
  %14 = getelementptr inbounds [20 x i32], [20 x i32]* @visited, i32 0, i32 %13
  store i32 0, i32* %14, align 4
  br label %15

; <label>:15     		; preds = %10
  %16 = load i32, i32* @i, align 4
  %17 = add i32 %16, 1
  store i32 %17, i32* @i, align 4
  br label %6

; <label>:18                                             		; preds = %6
  %19 = getelementptr inbounds [36 x i8], [36 x i8]* @.str2, i32 0, i32 0
  %20 = call i32 (i8*, ...)* @printf(i8* %19)
  store i32 1, i32* @i, align 4
  br label %21

; <label>:21		; preds = %18, %41
  %22 = load i32, i32* @i, align 4
  %23 = load i32, i32* @n, align 4
  %24 = icmp sle i32 %22, %23
  br i1 %24, label %25, label %44

; <label>:25  		; preds = %21
  store i32 1, i32* @j, align 4
  br label %26

; <label>:26		; preds = %25, %37
  %27 = load i32, i32* @j, align 4
  %28 = load i32, i32* @n, align 4
  %29 = icmp sle i32 %27, %28
  br i1 %29, label %30, label %40

; <label>:30                         		; preds = %26
  %31 = load i32, i32* @i, align 4
  %32 = getelementptr inbounds [20 x [20 x i32]], [20 x [20 x i32]]* @a, i32 0, i32 %31
  %33 = load i32, i32* @j, align 4
  %34 = getelementptr inbounds [20 x i32], [20 x i32]* %32, i32 0, i32 %33
  %35 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %36 = call i32 (i8*, ...)* @scanf(i8* %35, i32* %34)
  br label %37

; <label>:37     		; preds = %30
  %38 = load i32, i32* @j, align 4
  %39 = add i32 %38, 1
  store i32 %39, i32* @j, align 4
  br label %26

; <label>:40		; preds = %26
  br label %41

; <label>:41     		; preds = %40
  %42 = load i32, i32* @i, align 4
  %43 = add i32 %42, 1
  store i32 %43, i32* @i, align 4
  br label %21

; <label>:44                                            		; preds = %21
  %45 = getelementptr inbounds [29 x i8], [29 x i8]* @.str3, i32 0, i32 0
  %46 = call i32 (i8*, ...)* @printf(i8* %45)
  %47 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %48 = call i32 (i8*, ...)* @scanf(i8* %47, i32* %v)
  %49 = load i32, i32* %v, align 4
  call void @bfs(i32 %49)
  %50 = getelementptr inbounds [37 x i8], [37 x i8]* @.str4, i32 0, i32 0
  %51 = call i32 (i8*, ...)* @printf(i8* %50)
  store i32 1, i32* @i, align 4
  br label %52

; <label>:52		; preds = %44, %69
  %53 = load i32, i32* @i, align 4
  %54 = load i32, i32* @n, align 4
  %55 = icmp sle i32 %53, %54
  br i1 %55, label %56, label %72

; <label>:56      		; preds = %52
  %57 = load i32, i32* @i, align 4
  %58 = getelementptr inbounds [20 x i32], [20 x i32]* @visited, i32 0, i32 %57
  %59 = load i32, i32* %58, align 4
  %60 = icmp ne i32 %59, 0
  br i1 %60, label %61, label %65

; <label>:61                                          		; preds = %56
  %62 = getelementptr inbounds [4 x i8], [4 x i8]* @.str5, i32 0, i32 0
  %63 = load i32, i32* @i, align 4
  %64 = call i32 (i8*, ...)* @printf(i8* %62, i32 %63)
  br label %68

; <label>:65                                            		; preds = %56
  %66 = getelementptr inbounds [22 x i8], [22 x i8]* @.str6, i32 0, i32 0
  %67 = call i32 (i8*, ...)* @printf(i8* %66)
  br label %68

; <label>:68		; preds = %61, %65
  br label %69

; <label>:69     		; preds = %68
  %70 = load i32, i32* @i, align 4
  %71 = add i32 %70, 1
  store i32 %71, i32* @i, align 4
  br label %52

; <label>:72     		; preds = %52
  %73 = load i32, i32* %1, align 4
  ret i32 %73
}
