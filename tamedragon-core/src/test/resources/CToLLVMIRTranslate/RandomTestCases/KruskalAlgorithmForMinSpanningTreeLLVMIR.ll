@.str = private unnamed_addr constant [4 x i8] c"%d\09\00", align 1
@.str1 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
@.str2 = private unnamed_addr constant [25 x i8] c"spanning tree not exist\0A\00", align 16
@.str3 = private unnamed_addr constant [24 x i8] c"Adges Spanning tree is\0A\00", align 16
@.str4 = private unnamed_addr constant [11 x i8] c" %d -> %d \00", align 1
@.str5 = private unnamed_addr constant [13 x i8] c"\0Acost = %d \0A\00", align 1
@.str6 = private unnamed_addr constant [30 x i8] c"enter the number of vertices\0A\00", align 16
@.str7 = private unnamed_addr constant [3 x i8] c"%d\00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

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

; <label>:3 		; preds = %0, %53
  %4 = load i32, i32* %i, align 4
  %5 = load i32, i32* %2, align 4
  %6 = icmp slt i32 %4, %5
  br i1 %6, label %7, label %56

; <label>:7    		; preds = %3
  store i32 0, i32* %j, align 4
  br label %8

; <label>:8  		; preds = %7, %44
  %9 = load i32, i32* %j, align 4
  %10 = load i32, i32* %i, align 4
  %11 = icmp slt i32 %9, %10
  br i1 %11, label %12, label %47

; <label>:12       		; preds = %8
  %13 = call i32 @rand() nounwind
  %14 = srem i32 %13, 50
  %15 = load [100 x i32]*, [100 x i32]** %1, align 8
  %16 = load i32, i32* %j, align 4
  %17 = getelementptr inbounds [100 x i32], [100 x i32]* %15, i32 %16
  %18 = load i32, i32* %i, align 4
  %19 = getelementptr inbounds [100 x i32], [100 x i32]* %17, i32 0, i32 %18
  store i32 %14, i32* %19, align 4
  %20 = load [100 x i32]*, [100 x i32]** %1, align 8
  %21 = load i32, i32* %i, align 4
  %22 = getelementptr inbounds [100 x i32], [100 x i32]* %20, i32 %21
  %23 = load i32, i32* %j, align 4
  %24 = getelementptr inbounds [100 x i32], [100 x i32]* %22, i32 0, i32 %23
  store i32 %14, i32* %24, align 4
  %25 = load [100 x i32]*, [100 x i32]** %1, align 8
  %26 = load i32, i32* %i, align 4
  %27 = getelementptr inbounds [100 x i32], [100 x i32]* %25, i32 %26
  %28 = load i32, i32* %j, align 4
  %29 = getelementptr inbounds [100 x i32], [100 x i32]* %27, i32 0, i32 %28
  %30 = load i32, i32* %29, align 4
  %31 = icmp sgt i32 %30, 40
  br i1 %31, label %32, label %43

; <label>:32                                               		; preds = %12
  %33 = load [100 x i32]*, [100 x i32]** %1, align 8
  %34 = load i32, i32* %j, align 4
  %35 = getelementptr inbounds [100 x i32], [100 x i32]* %33, i32 %34
  %36 = load i32, i32* %i, align 4
  %37 = getelementptr inbounds [100 x i32], [100 x i32]* %35, i32 0, i32 %36
  store i32 999, i32* %37, align 4
  %38 = load [100 x i32]*, [100 x i32]** %1, align 8
  %39 = load i32, i32* %i, align 4
  %40 = getelementptr inbounds [100 x i32], [100 x i32]* %38, i32 %39
  %41 = load i32, i32* %j, align 4
  %42 = getelementptr inbounds [100 x i32], [100 x i32]* %40, i32 0, i32 %41
  store i32 999, i32* %42, align 4
  br label %43

; <label>:43		; preds = %12, %32
  br label %44

; <label>:44     		; preds = %43
  %45 = load i32, i32* %j, align 4
  %46 = add i32 %45, 1
  store i32 %46, i32* %j, align 4
  br label %8

; <label>:47                                                		; preds = %8
  %48 = load [100 x i32]*, [100 x i32]** %1, align 8
  %49 = load i32, i32* %i, align 4
  %50 = getelementptr inbounds [100 x i32], [100 x i32]* %48, i32 %49
  %51 = load i32, i32* %i, align 4
  %52 = getelementptr inbounds [100 x i32], [100 x i32]* %50, i32 0, i32 %51
  store i32 999, i32* %52, align 4
  br label %53

; <label>:53     		; preds = %47
  %54 = load i32, i32* %i, align 4
  %55 = add i32 %54, 1
  store i32 %55, i32* %i, align 4
  br label %3

; <label>:56                        		; preds = %3
  %57 = load [100 x i32]*, [100 x i32]** %1, align 8
  %58 = load i32, i32* %2, align 4
  call void @printArray([100 x i32]* %57, i32 %58)
  ret void
}

define i32 @root(i32 %v, i32* %p) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32*, align 8
  store i32 %v, i32* %1, align 4
  store i32* %p, i32** %2, align 8
  br label %3

; <label>:3                  		; preds = %0, %10
  %4 = load i32*, i32** %2, align 8
  %5 = load i32, i32* %1, align 4
  %6 = getelementptr inbounds i32, i32* %4, i32 %5
  %7 = load i32, i32* %1, align 4
  %8 = load i32, i32* %6, align 4
  %9 = icmp ne i32 %8, %7
  br i1 %9, label %10, label %15

; <label>:10                         		; preds = %3
  %11 = load i32*, i32** %2, align 8
  %12 = load i32, i32* %1, align 4
  %13 = getelementptr inbounds i32, i32* %11, i32 %12
  %14 = load i32, i32* %13, align 4
  store i32 %14, i32* %1, align 4
  br label %3

; <label>:15      		; preds = %3
  %16 = load i32, i32* %1, align 4
  ret i32 %16
}

define void @union_ij(i32 %i, i32 %j, i32* %p) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32*, align 8
  store i32 %i, i32* %1, align 4
  store i32 %j, i32* %2, align 4
  store i32* %p, i32** %3, align 8
  %4 = load i32, i32* %2, align 4
  %5 = load i32, i32* %1, align 4
  %6 = icmp sgt i32 %4, %5
  br i1 %6, label %7, label %12

; <label>:7                        		; preds = %0
  %8 = load i32*, i32** %3, align 8
  %9 = load i32, i32* %2, align 4
  %10 = getelementptr inbounds i32, i32* %8, i32 %9
  %11 = load i32, i32* %1, align 4
  store i32 %11, i32* %10, align 4
  br label %17

; <label>:12                         		; preds = %0
  %13 = load i32*, i32** %3, align 8
  %14 = load i32, i32* %1, align 4
  %15 = getelementptr inbounds i32, i32* %13, i32 %14
  %16 = load i32, i32* %2, align 4
  store i32 %16, i32* %15, align 4
  br label %17

; <label>:17		; preds = %7, %12
  ret void
}

define void @kruskal([100 x i32]* %a, i32 %n) nounwind {
  %1 = alloca [100 x i32]*, align 8
  %2 = alloca i32, align 4
  %count = alloca i32, align 4
  %i = alloca i32, align 4
  %p = alloca [100 x i32], align 16
  %min = alloca i32, align 4
  %j = alloca i32, align 4
  %u = alloca i32, align 4
  %v = alloca i32, align 4
  %k = alloca i32, align 4
  %t = alloca [100 x [100 x i32]], align 16
  %sum = alloca i32, align 4
  store [100 x i32]* %a, [100 x i32]** %1, align 8
  store i32 %n, i32* %2, align 4
  store i32 0, i32* %sum, align 4
  store i32 0, i32* %k, align 4
  store i32 0, i32* %count, align 4
  store i32 0, i32* %i, align 4
  br label %3

; <label>:3 		; preds = %0, %11
  %4 = load i32, i32* %i, align 4
  %5 = load i32, i32* %2, align 4
  %6 = icmp slt i32 %4, %5
  br i1 %6, label %7, label %14

; <label>:7       		; preds = %3
  %8 = load i32, i32* %i, align 4
  %9 = getelementptr inbounds [100 x i32], [100 x i32]* %p, i32 0, i32 %8
  %10 = load i32, i32* %i, align 4
  store i32 %10, i32* %9, align 4
  br label %11

; <label>:11      		; preds = %7
  %12 = load i32, i32* %i, align 4
  %13 = add i32 %12, 1
  store i32 %13, i32* %i, align 4
  br label %3

; <label>:14		; preds = %3
  br label %15

; <label>:15    		; preds = %14, %96
  %16 = load i32, i32* %count, align 4
  %17 = load i32, i32* %2, align 4
  %18 = icmp slt i32 %16, %17
  br i1 %18, label %19, label %99

; <label>:19      		; preds = %15
  store i32 999, i32* %min, align 4
  store i32 0, i32* %i, align 4
  br label %20

; <label>:20		; preds = %19, %52
  %21 = load i32, i32* %i, align 4
  %22 = load i32, i32* %2, align 4
  %23 = icmp slt i32 %21, %22
  br i1 %23, label %24, label %55

; <label>:24  		; preds = %20
  store i32 0, i32* %j, align 4
  br label %25

; <label>:25		; preds = %24, %48
  %26 = load i32, i32* %j, align 4
  %27 = load i32, i32* %2, align 4
  %28 = icmp slt i32 %26, %27
  br i1 %28, label %29, label %51

; <label>:29                                               		; preds = %25
  %30 = load [100 x i32]*, [100 x i32]** %1, align 8
  %31 = load i32, i32* %i, align 4
  %32 = getelementptr inbounds [100 x i32], [100 x i32]* %30, i32 %31
  %33 = load i32, i32* %j, align 4
  %34 = getelementptr inbounds [100 x i32], [100 x i32]* %32, i32 0, i32 %33
  %35 = load i32, i32* %min, align 4
  %36 = load i32, i32* %34, align 4
  %37 = icmp slt i32 %36, %35
  br i1 %37, label %38, label %47

; <label>:38                                               		; preds = %29
  %39 = load [100 x i32]*, [100 x i32]** %1, align 8
  %40 = load i32, i32* %i, align 4
  %41 = getelementptr inbounds [100 x i32], [100 x i32]* %39, i32 %40
  %42 = load i32, i32* %j, align 4
  %43 = getelementptr inbounds [100 x i32], [100 x i32]* %41, i32 0, i32 %42
  %44 = load i32, i32* %43, align 4
  store i32 %44, i32* %min, align 4
  %45 = load i32, i32* %i, align 4
  store i32 %45, i32* %u, align 4
  %46 = load i32, i32* %j, align 4
  store i32 %46, i32* %v, align 4
  br label %47

; <label>:47		; preds = %29, %38
  br label %48

; <label>:48     		; preds = %47
  %49 = load i32, i32* %j, align 4
  %50 = add i32 %49, 1
  store i32 %50, i32* %j, align 4
  br label %25

; <label>:51		; preds = %25
  br label %52

; <label>:52     		; preds = %51
  %53 = load i32, i32* %i, align 4
  %54 = add i32 %53, 1
  store i32 %54, i32* %i, align 4
  br label %20

; <label>:55       		; preds = %20
  %56 = load i32, i32* %min, align 4
  %57 = icmp ne i32 %56, 999
  br i1 %57, label %58, label %96

; <label>:58            		; preds = %55
  %59 = load i32, i32* %u, align 4
  %60 = getelementptr inbounds [100 x i32], [100 x i32]* %p, i32 0, i32 0
  %61 = call i32 @root(i32 %59, i32* %60)
  store i32 %61, i32* %i, align 4
  %62 = load i32, i32* %v, align 4
  %63 = getelementptr inbounds [100 x i32], [100 x i32]* %p, i32 0, i32 0
  %64 = call i32 @root(i32 %62, i32* %63)
  store i32 %64, i32* %j, align 4
  %65 = load i32, i32* %i, align 4
  %66 = load i32, i32* %j, align 4
  %67 = icmp ne i32 %65, %66
  br i1 %67, label %68, label %85

; <label>:68                    		; preds = %58
  %69 = load i32, i32* %k, align 4
  %70 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %t, i32 0, i32 %69
  %71 = getelementptr inbounds [100 x i32], [100 x i32]* %70, i32 0, i32 0
  %72 = load i32, i32* %u, align 4
  store i32 %72, i32* %71, align 4
  %73 = load i32, i32* %k, align 4
  %74 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %t, i32 0, i32 %73
  %75 = getelementptr inbounds [100 x i32], [100 x i32]* %74, i32 0, i32 1
  %76 = load i32, i32* %v, align 4
  store i32 %76, i32* %75, align 4
  %77 = load i32, i32* %k, align 4
  %78 = add i32 %77, 1
  store i32 %78, i32* %k, align 4
  %79 = load i32, i32* %sum, align 4
  %80 = load i32, i32* %min, align 4
  %81 = add i32 %79, %80
  store i32 %81, i32* %sum, align 4
  %82 = load i32, i32* %i, align 4
  %83 = load i32, i32* %j, align 4
  %84 = getelementptr inbounds [100 x i32], [100 x i32]* %p, i32 0, i32 0
  call void @union_ij(i32 %82, i32 %83, i32* %84)
  br label %85

; <label>:85                                          		; preds = %58, %68
  %86 = load [100 x i32]*, [100 x i32]** %1, align 8
  %87 = load i32, i32* %v, align 4
  %88 = getelementptr inbounds [100 x i32], [100 x i32]* %86, i32 %87
  %89 = load i32, i32* %u, align 4
  %90 = getelementptr inbounds [100 x i32], [100 x i32]* %88, i32 0, i32 %89
  store i32 999, i32* %90, align 4
  %91 = load [100 x i32]*, [100 x i32]** %1, align 8
  %92 = load i32, i32* %u, align 4
  %93 = getelementptr inbounds [100 x i32], [100 x i32]* %91, i32 %92
  %94 = load i32, i32* %v, align 4
  %95 = getelementptr inbounds [100 x i32], [100 x i32]* %93, i32 0, i32 %94
  store i32 999, i32* %95, align 4
  br label %96

; <label>:96    		; preds = %55, %85
  %97 = load i32, i32* %count, align 4
  %98 = add i32 %97, 1
  store i32 %98, i32* %count, align 4
  br label %15

; <label>:99          		; preds = %15
  %100 = load i32, i32* %count, align 4
  %101 = load i32, i32* %2, align 4
  %102 = icmp ne i32 %100, %101
  br i1 %102, label %103, label %106

; <label>:103                                            		; preds = %99
  %104 = getelementptr inbounds [25 x i8], [25 x i8]* @.str2, i32 0, i32 0
  %105 = call i32 (i8*, ...)* @printf(i8* %104)
  br label %106

; <label>:106   		; preds = %99, %103
  %107 = load i32, i32* %count, align 4
  %108 = load i32, i32* %2, align 4
  %109 = icmp eq i32 %107, %108
  br i1 %109, label %110, label %136

; <label>:110                                           		; preds = %106
  %111 = getelementptr inbounds [24 x i8], [24 x i8]* @.str3, i32 0, i32 0
  %112 = call i32 (i8*, ...)* @printf(i8* %111)
  store i32 0, i32* %k, align 4
  br label %113

; <label>:113		; preds = %110, %129
  %114 = load i32, i32* %k, align 4
  %115 = load i32, i32* %2, align 4
  %116 = sub i32 %115, 1
  %117 = icmp slt i32 %114, %116
  br i1 %117, label %118, label %132

; <label>:118                                    		; preds = %113
  %119 = load i32, i32* %k, align 4
  %120 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %t, i32 0, i32 %119
  %121 = getelementptr inbounds [100 x i32], [100 x i32]* %120, i32 0, i32 1
  %122 = load i32, i32* %k, align 4
  %123 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %t, i32 0, i32 %122
  %124 = getelementptr inbounds [100 x i32], [100 x i32]* %123, i32 0, i32 0
  %125 = getelementptr inbounds [11 x i8], [11 x i8]* @.str4, i32 0, i32 0
  %126 = load i32, i32* %124, align 4
  %127 = load i32, i32* %121, align 4
  %128 = call i32 (i8*, ...)* @printf(i8* %125, i32 %126, i32 %127)
  br label %129

; <label>:129    		; preds = %118
  %130 = load i32, i32* %k, align 4
  %131 = add i32 %130, 1
  store i32 %131, i32* %k, align 4
  br label %113

; <label>:132                                           		; preds = %113
  %133 = getelementptr inbounds [13 x i8], [13 x i8]* @.str5, i32 0, i32 0
  %134 = load i32, i32* %sum, align 4
  %135 = call i32 (i8*, ...)* @printf(i8* %133, i32 %134)
  br label %136

; <label>:136		; preds = %106, %132
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %a = alloca [100 x [100 x i32]], align 16
  %n = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [30 x i8], [30 x i8]* @.str6, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [3 x i8], [3 x i8]* @.str7, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %n)
  %6 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %a, i32 0, i32 0
  %7 = load i32, i32* %n, align 4
  call void @AdjacencyMatrix([100 x i32]* %6, i32 %7)
  %8 = getelementptr inbounds [100 x [100 x i32]], [100 x [100 x i32]]* %a, i32 0, i32 0
  %9 = load i32, i32* %n, align 4
  call void @kruskal([100 x i32]* %8, i32 %9)
  ret i32 0
}
