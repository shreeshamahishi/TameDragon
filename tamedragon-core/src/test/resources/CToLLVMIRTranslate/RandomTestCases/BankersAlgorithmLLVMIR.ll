@m = common global i32 0, align 4
@n = common global i32 0, align 4
@i = common global i32 0, align 4
@j = common global i32 0, align 4
@count = global i32 0, align 4
@process = common global i32 0, align 4
@max = common global [10 x [10 x i32]] zeroinitializer, align 16
@alloc = common global [10 x [10 x i32]] zeroinitializer, align 16
@need = common global [10 x [10 x i32]] zeroinitializer, align 16
@c = common global [10 x i32] zeroinitializer, align 16
@avail = common global [10 x i32] zeroinitializer, align 16
@finish = common global [10 x i32] zeroinitializer, align 16
@.str = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str1 = private unnamed_addr constant [4 x i8] c"\09%d\00", align 1
@.str2 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
@.str3 = private unnamed_addr constant [29 x i8] c"enter the number of process\0A\00", align 16
@.str4 = private unnamed_addr constant [31 x i8] c"enter the number of resources\0A\00", align 16
@.str5 = private unnamed_addr constant [23 x i8] c"enter the claim table\0A\00", align 16
@.str6 = private unnamed_addr constant [28 x i8] c"enter the allocation table\0A\00", align 16
@.str7 = private unnamed_addr constant [38 x i8] c"enter the max units of each resource\0A\00", align 16
@.str8 = private unnamed_addr constant [19 x i8] c"current status is\0A\00", align 16
@.str9 = private unnamed_addr constant [27 x i8] c"system is in unsafe state\0A\00", align 16
@.str10 = private unnamed_addr constant [24 x i8] c"system is in safe state\00", align 16
@.str11 = private unnamed_addr constant [23 x i8] c"excuting process is %d\00", align 16
@.str12 = private unnamed_addr constant [32 x i8] c"\0A all proces executed correctly\00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

declare void @exit(i32) 

define void @readtable([10 x i32]* %t) nounwind {
  %1 = alloca [10 x i32]*, align 8
  store [10 x i32]* %t, [10 x i32]** %1, align 8
  store i32 0, i32* @i, align 4
  br label %2

; <label>:2 		; preds = %0, %23
  %3 = load i32, i32* @i, align 4
  %4 = load i32, i32* @m, align 4
  %5 = icmp slt i32 %3, %4
  br i1 %5, label %6, label %26

; <label>:6    		; preds = %2
  store i32 0, i32* @j, align 4
  br label %7

; <label>:7 		; preds = %6, %19
  %8 = load i32, i32* @j, align 4
  %9 = load i32, i32* @n, align 4
  %10 = icmp slt i32 %8, %9
  br i1 %10, label %11, label %22

; <label>:11                          		; preds = %7
  %12 = load [10 x i32]*, [10 x i32]** %1, align 8
  %13 = load i32, i32* @i, align 4
  %14 = getelementptr inbounds [10 x i32], [10 x i32]* %12, i32 %13
  %15 = load i32, i32* @j, align 4
  %16 = getelementptr inbounds [10 x i32], [10 x i32]* %14, i32 0, i32 %15
  %17 = getelementptr inbounds [3 x i8], [3 x i8]* @.str, i32 0, i32 0
  %18 = call i32 (i8*, ...)* @scanf(i8* %17, i32* %16)
  br label %19

; <label>:19     		; preds = %11
  %20 = load i32, i32* @j, align 4
  %21 = add i32 %20, 1
  store i32 %21, i32* @j, align 4
  br label %7

; <label>:22		; preds = %7
  br label %23

; <label>:23     		; preds = %22
  %24 = load i32, i32* @i, align 4
  %25 = add i32 %24, 1
  store i32 %25, i32* @i, align 4
  br label %2

; <label>:26		; preds = %2
  ret void
}

define void @printtable([10 x i32]* %t) nounwind {
  %1 = alloca [10 x i32]*, align 8
  store [10 x i32]* %t, [10 x i32]** %1, align 8
  store i32 0, i32* @i, align 4
  br label %2

; <label>:2 		; preds = %0, %26
  %3 = load i32, i32* @i, align 4
  %4 = load i32, i32* @m, align 4
  %5 = icmp slt i32 %3, %4
  br i1 %5, label %6, label %29

; <label>:6    		; preds = %2
  store i32 0, i32* @j, align 4
  br label %7

; <label>:7 		; preds = %6, %20
  %8 = load i32, i32* @j, align 4
  %9 = load i32, i32* @n, align 4
  %10 = icmp slt i32 %8, %9
  br i1 %10, label %11, label %23

; <label>:11                          		; preds = %7
  %12 = load [10 x i32]*, [10 x i32]** %1, align 8
  %13 = load i32, i32* @i, align 4
  %14 = getelementptr inbounds [10 x i32], [10 x i32]* %12, i32 %13
  %15 = load i32, i32* @j, align 4
  %16 = getelementptr inbounds [10 x i32], [10 x i32]* %14, i32 0, i32 %15
  %17 = getelementptr inbounds [4 x i8], [4 x i8]* @.str1, i32 0, i32 0
  %18 = load i32, i32* %16, align 4
  %19 = call i32 (i8*, ...)* @printf(i8* %17, i32 %18)
  br label %20

; <label>:20     		; preds = %11
  %21 = load i32, i32* @j, align 4
  %22 = add i32 %21, 1
  store i32 %22, i32* @j, align 4
  br label %7

; <label>:23                                           		; preds = %7
  %24 = getelementptr inbounds [2 x i8], [2 x i8]* @.str2, i32 0, i32 0
  %25 = call i32 (i8*, ...)* @printf(i8* %24)
  br label %26

; <label>:26     		; preds = %23
  %27 = load i32, i32* @i, align 4
  %28 = add i32 %27, 1
  store i32 %28, i32* @i, align 4
  br label %2

; <label>:29		; preds = %2
  ret void
}

define void @readvector(i32* %v) nounwind {
  %1 = alloca i32*, align 8
  store i32* %v, i32** %1, align 8
  store i32 0, i32* @j, align 4
  br label %2

; <label>:2 		; preds = %0, %12
  %3 = load i32, i32* @j, align 4
  %4 = load i32, i32* @n, align 4
  %5 = icmp slt i32 %3, %4
  br i1 %5, label %6, label %15

; <label>:6                          		; preds = %2
  %7 = load i32*, i32** %1, align 8
  %8 = load i32, i32* @j, align 4
  %9 = getelementptr inbounds i32, i32* %7, i32 %8
  %10 = getelementptr inbounds [3 x i8], [3 x i8]* @.str, i32 0, i32 0
  %11 = call i32 (i8*, ...)* @scanf(i8* %10, i32* %9)
  br label %12

; <label>:12      		; preds = %6
  %13 = load i32, i32* @j, align 4
  %14 = add i32 %13, 1
  store i32 %14, i32* @j, align 4
  br label %2

; <label>:15		; preds = %2
  ret void
}

define void @printvector(i32* %v) nounwind {
  %1 = alloca i32*, align 8
  store i32* %v, i32** %1, align 8
  store i32 0, i32* @j, align 4
  br label %2

; <label>:2 		; preds = %0, %13
  %3 = load i32, i32* @j, align 4
  %4 = load i32, i32* @n, align 4
  %5 = icmp slt i32 %3, %4
  br i1 %5, label %6, label %16

; <label>:6                           		; preds = %2
  %7 = load i32*, i32** %1, align 8
  %8 = load i32, i32* @j, align 4
  %9 = getelementptr inbounds i32, i32* %7, i32 %8
  %10 = getelementptr inbounds [4 x i8], [4 x i8]* @.str1, i32 0, i32 0
  %11 = load i32, i32* %9, align 4
  %12 = call i32 (i8*, ...)* @printf(i8* %10, i32 %11)
  br label %13

; <label>:13      		; preds = %6
  %14 = load i32, i32* @j, align 4
  %15 = add i32 %14, 1
  store i32 %15, i32* @j, align 4
  br label %2

; <label>:16		; preds = %2
  ret void
}

define void @init() nounwind {
  %1 = getelementptr inbounds [29 x i8], [29 x i8]* @.str3, i32 0, i32 0
  %2 = call i32 (i8*, ...)* @printf(i8* %1)
  %3 = getelementptr inbounds [3 x i8], [3 x i8]* @.str, i32 0, i32 0
  %4 = call i32 (i8*, ...)* @scanf(i8* %3, i32* @m)
  %5 = getelementptr inbounds [31 x i8], [31 x i8]* @.str4, i32 0, i32 0
  %6 = call i32 (i8*, ...)* @printf(i8* %5)
  %7 = getelementptr inbounds [3 x i8], [3 x i8]* @.str, i32 0, i32 0
  %8 = call i32 (i8*, ...)* @scanf(i8* %7, i32* @n)
  %9 = getelementptr inbounds [23 x i8], [23 x i8]* @.str5, i32 0, i32 0
  %10 = call i32 (i8*, ...)* @printf(i8* %9)
  %11 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* @max, i32 0, i32 0
  call void @readtable([10 x i32]* %11)
  %12 = getelementptr inbounds [28 x i8], [28 x i8]* @.str6, i32 0, i32 0
  %13 = call i32 (i8*, ...)* @printf(i8* %12)
  %14 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* @alloc, i32 0, i32 0
  call void @readtable([10 x i32]* %14)
  %15 = getelementptr inbounds [38 x i8], [38 x i8]* @.str7, i32 0, i32 0
  %16 = call i32 (i8*, ...)* @printf(i8* %15)
  %17 = getelementptr inbounds [10 x i32], [10 x i32]* @c, i32 0, i32 0
  call void @readvector(i32* %17)
  store i32 0, i32* @i, align 4
  br label %18

; <label>:18 		; preds = %0, %25
  %19 = load i32, i32* @i, align 4
  %20 = load i32, i32* @n, align 4
  %21 = icmp slt i32 %19, %20
  br i1 %21, label %22, label %28

; <label>:22                                                 		; preds = %18
  %23 = load i32, i32* @i, align 4
  %24 = getelementptr inbounds [10 x i32], [10 x i32]* @finish, i32 0, i32 %23
  store i32 0, i32* %24, align 4
  br label %25

; <label>:25     		; preds = %22
  %26 = load i32, i32* @i, align 4
  %27 = add i32 %26, 1
  store i32 %27, i32* @i, align 4
  br label %18

; <label>:28		; preds = %18
  ret void
}

define void @findavail() nounwind {
  %sum = alloca i32, align 4
  store i32 0, i32* @j, align 4
  br label %1

; <label>:1 		; preds = %0, %29
  %2 = load i32, i32* @j, align 4
  %3 = load i32, i32* @n, align 4
  %4 = icmp slt i32 %2, %3
  br i1 %4, label %5, label %32

; <label>:5      		; preds = %1
  store i32 0, i32* %sum, align 4
  store i32 0, i32* @i, align 4
  br label %6

; <label>:6 		; preds = %5, %18
  %7 = load i32, i32* @i, align 4
  %8 = load i32, i32* @m, align 4
  %9 = icmp slt i32 %7, %8
  br i1 %9, label %10, label %21

; <label>:10                                              		; preds = %6
  %11 = load i32, i32* %sum, align 4
  %12 = load i32, i32* @i, align 4
  %13 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* @alloc, i32 0, i32 %12
  %14 = load i32, i32* @j, align 4
  %15 = getelementptr inbounds [10 x i32], [10 x i32]* %13, i32 0, i32 %14
  %16 = load i32, i32* %15, align 4
  %17 = add i32 %11, %16
  store i32 %17, i32* %sum, align 4
  br label %18

; <label>:18     		; preds = %10
  %19 = load i32, i32* @i, align 4
  %20 = add i32 %19, 1
  store i32 %20, i32* @i, align 4
  br label %6

; <label>:21                                                 		; preds = %6
  %22 = load i32, i32* @j, align 4
  %23 = getelementptr inbounds [10 x i32], [10 x i32]* @c, i32 0, i32 %22
  %24 = load i32, i32* %sum, align 4
  %25 = load i32, i32* %23, align 4
  %26 = sub i32 %25, %24
  %27 = load i32, i32* @j, align 4
  %28 = getelementptr inbounds [10 x i32], [10 x i32]* @avail, i32 0, i32 %27
  store i32 %26, i32* %28, align 4
  br label %29

; <label>:29     		; preds = %21
  %30 = load i32, i32* @j, align 4
  %31 = add i32 %30, 1
  store i32 %31, i32* @j, align 4
  br label %1

; <label>:32		; preds = %1
  ret void
}

define void @findneed() nounwind {
  store i32 0, i32* @i, align 4
  br label %1

; <label>:1 		; preds = %0, %30
  %2 = load i32, i32* @i, align 4
  %3 = load i32, i32* @m, align 4
  %4 = icmp slt i32 %2, %3
  br i1 %4, label %5, label %33

; <label>:5    		; preds = %1
  store i32 0, i32* @j, align 4
  br label %6

; <label>:6 		; preds = %5, %26
  %7 = load i32, i32* @j, align 4
  %8 = load i32, i32* @n, align 4
  %9 = icmp slt i32 %7, %8
  br i1 %9, label %10, label %29

; <label>:10                                              		; preds = %6
  %11 = load i32, i32* @i, align 4
  %12 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* @max, i32 0, i32 %11
  %13 = load i32, i32* @j, align 4
  %14 = getelementptr inbounds [10 x i32], [10 x i32]* %12, i32 0, i32 %13
  %15 = load i32, i32* @i, align 4
  %16 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* @alloc, i32 0, i32 %15
  %17 = load i32, i32* @j, align 4
  %18 = getelementptr inbounds [10 x i32], [10 x i32]* %16, i32 0, i32 %17
  %19 = load i32, i32* %14, align 4
  %20 = load i32, i32* %18, align 4
  %21 = sub i32 %19, %20
  %22 = load i32, i32* @i, align 4
  %23 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* @need, i32 0, i32 %22
  %24 = load i32, i32* @j, align 4
  %25 = getelementptr inbounds [10 x i32], [10 x i32]* %23, i32 0, i32 %24
  store i32 %21, i32* %25, align 4
  br label %26

; <label>:26     		; preds = %10
  %27 = load i32, i32* @j, align 4
  %28 = add i32 %27, 1
  store i32 %28, i32* @j, align 4
  br label %6

; <label>:29		; preds = %6
  br label %30

; <label>:30     		; preds = %29
  %31 = load i32, i32* @i, align 4
  %32 = add i32 %31, 1
  store i32 %32, i32* @i, align 4
  br label %1

; <label>:33		; preds = %1
  ret void
}

define void @selectprocess() nounwind {
  %flag = alloca i32, align 4
  store i32 0, i32* @i, align 4
  br label %1

; <label>:1 		; preds = %0, %41
  %2 = load i32, i32* @i, align 4
  %3 = load i32, i32* @m, align 4
  %4 = icmp slt i32 %2, %3
  br i1 %4, label %5, label %44

; <label>:5    		; preds = %1
  store i32 0, i32* @j, align 4
  br label %6

; <label>:6 		; preds = %5, %23
  %7 = load i32, i32* @j, align 4
  %8 = load i32, i32* @n, align 4
  %9 = icmp slt i32 %7, %8
  br i1 %9, label %10, label %26

; <label>:10       		; preds = %6
  %11 = load i32, i32* @i, align 4
  %12 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* @need, i32 0, i32 %11
  %13 = load i32, i32* @j, align 4
  %14 = getelementptr inbounds [10 x i32], [10 x i32]* %12, i32 0, i32 %13
  %15 = load i32, i32* @j, align 4
  %16 = getelementptr inbounds [10 x i32], [10 x i32]* @avail, i32 0, i32 %15
  %17 = load i32, i32* %14, align 4
  %18 = load i32, i32* %16, align 4
  %19 = icmp sle i32 %17, %18
  br i1 %19, label %20, label %21

; <label>:20     		; preds = %10
  store i32 1, i32* %flag, align 4
  br label %22

; <label>:21     		; preds = %10
  store i32 0, i32* %flag, align 4
  br label %26

; <label>:22		; preds = %20
  br label %23

; <label>:23     		; preds = %22
  %24 = load i32, i32* @j, align 4
  %25 = add i32 %24, 1
  store i32 %25, i32* @j, align 4
  br label %6

; <label>:26    		; preds = %6, %21
  %27 = load i32, i32* %flag, align 4
  %28 = icmp eq i32 %27, 1
  br i1 %28, label %29, label %34

; <label>:29      		; preds = %26
  %30 = load i32, i32* @i, align 4
  %31 = getelementptr inbounds [10 x i32], [10 x i32]* @finish, i32 0, i32 %30
  %32 = load i32, i32* %31, align 4
  %33 = icmp eq i32 %32, 0
  br label %34

; <label>:34         		; preds = %26, %29
  %35 = phi i1 [ false, %26 ], [ %33, %29 ]
  br i1 %35, label %36, label %40

; <label>:36        		; preds = %34
  %37 = load i32, i32* @i, align 4
  store i32 %37, i32* @process, align 4
  %38 = load i32, i32* @count, align 4
  %39 = add i32 %38, 1
  store i32 %39, i32* @count, align 4
  br label %44

; <label>:40		; preds = %34
  br label %41

; <label>:41     		; preds = %40
  %42 = load i32, i32* @i, align 4
  %43 = add i32 %42, 1
  store i32 %43, i32* @i, align 4
  br label %1

; <label>:44                                                        		; preds = %1, %36
  %45 = getelementptr inbounds [19 x i8], [19 x i8]* @.str8, i32 0, i32 0
  %46 = call i32 (i8*, ...)* @printf(i8* %45)
  %47 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* @alloc, i32 0, i32 0
  call void @printtable([10 x i32]* %47)
  %48 = load i32, i32* %flag, align 4
  %49 = icmp eq i32 %48, 0
  br i1 %49, label %50, label %53

; <label>:50                                            		; preds = %44
  %51 = getelementptr inbounds [27 x i8], [27 x i8]* @.str9, i32 0, i32 0
  %52 = call i32 (i8*, ...)* @printf(i8* %51)
  call void @exit(i32 1) noreturn
  unreachable

; <label>:53                                             		; preds = %44
  %54 = getelementptr inbounds [24 x i8], [24 x i8]* @.str10, i32 0, i32 0
  %55 = call i32 (i8*, ...)* @printf(i8* %54)
  ret void
}

define void @executeprocess(i32 %p) nounwind {
  %1 = alloca i32, align 4
  store i32 %p, i32* %1, align 4
  %2 = getelementptr inbounds [23 x i8], [23 x i8]* @.str11, i32 0, i32 0
  %3 = load i32, i32* %1, align 4
  %4 = call i32 (i8*, ...)* @printf(i8* %2, i32 %3)
  %5 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* @alloc, i32 0, i32 0
  call void @printtable([10 x i32]* %5)
  ret void
}

define void @releaseresource() nounwind {
  store i32 0, i32* @j, align 4
  br label %1

; <label>:1 		; preds = %0, %17
  %2 = load i32, i32* @j, align 4
  %3 = load i32, i32* @n, align 4
  %4 = icmp slt i32 %2, %3
  br i1 %4, label %5, label %20

; <label>:5       		; preds = %1
  %6 = load i32, i32* @j, align 4
  %7 = getelementptr inbounds [10 x i32], [10 x i32]* @avail, i32 0, i32 %6
  %8 = load i32, i32* @process, align 4
  %9 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* @alloc, i32 0, i32 %8
  %10 = load i32, i32* @j, align 4
  %11 = getelementptr inbounds [10 x i32], [10 x i32]* %9, i32 0, i32 %10
  %12 = load i32, i32* %7, align 4
  %13 = load i32, i32* %11, align 4
  %14 = add i32 %12, %13
  %15 = load i32, i32* @j, align 4
  %16 = getelementptr inbounds [10 x i32], [10 x i32]* @avail, i32 0, i32 %15
  store i32 %14, i32* %16, align 4
  br label %17

; <label>:17      		; preds = %5
  %18 = load i32, i32* @j, align 4
  %19 = add i32 %18, 1
  store i32 %19, i32* @j, align 4
  br label %1

; <label>:20   		; preds = %1
  store i32 0, i32* @j, align 4
  br label %21

; <label>:21		; preds = %20, %34
  %22 = load i32, i32* @j, align 4
  %23 = load i32, i32* @n, align 4
  %24 = icmp slt i32 %22, %23
  br i1 %24, label %25, label %37

; <label>:25                                             		; preds = %21
  %26 = load i32, i32* @process, align 4
  %27 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* @alloc, i32 0, i32 %26
  %28 = load i32, i32* @j, align 4
  %29 = getelementptr inbounds [10 x i32], [10 x i32]* %27, i32 0, i32 %28
  store i32 0, i32* %29, align 4
  %30 = load i32, i32* @process, align 4
  %31 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* @need, i32 0, i32 %30
  %32 = load i32, i32* @j, align 4
  %33 = getelementptr inbounds [10 x i32], [10 x i32]* %31, i32 0, i32 %32
  store i32 0, i32* %33, align 4
  br label %34

; <label>:34     		; preds = %25
  %35 = load i32, i32* @j, align 4
  %36 = add i32 %35, 1
  store i32 %36, i32* @j, align 4
  br label %21

; <label>:37		; preds = %21
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  call void @init()
  call void @findavail()
  call void @findneed()
  br label %2

; <label>:2		; preds = %0, %6
  call void @selectprocess()
  %3 = load i32, i32* @process, align 4
  %4 = getelementptr inbounds [10 x i32], [10 x i32]* @finish, i32 0, i32 %3
  store i32 1, i32* %4, align 4
  %5 = load i32, i32* @process, align 4
  call void @executeprocess(i32 %5)
  call void @releaseresource()
  br label %6

; <label>:6          		; preds = %2
  %7 = load i32, i32* @count, align 4
  %8 = load i32, i32* @m, align 4
  %9 = icmp slt i32 %7, %8
  br i1 %9, label %2, label %10

; <label>:10                                              		; preds = %6
  %11 = getelementptr inbounds [32 x i8], [32 x i8]* @.str12, i32 0, i32 0
  %12 = call i32 (i8*, ...)* @printf(i8* %11)
  %13 = load i32, i32* %1, align 4
  ret i32 %13
}
