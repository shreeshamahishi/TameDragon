@.str = private unnamed_addr constant [26 x i8] c"Enter the no of vertices:\00", align 16
@.str1 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str2 = private unnamed_addr constant [28 x i8] c"Enter the adjacency matrix:\00", align 16
@.str3 = private unnamed_addr constant [26 x i8] c"The topological order is:\00", align 16
@.str4 = private unnamed_addr constant [7 x i8] c"%d -> \00", align 1
@.str5 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  %k = alloca i32, align 4
  %n = alloca i32, align 4
  %a = alloca [10 x [10 x i32]], align 16
  %indeg = alloca [10 x i32], align 16
  %flag = alloca [10 x i32], align 16
  %count = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 0, i32* %count, align 4
  %2 = getelementptr inbounds [26 x i8], [26 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %n)
  %6 = getelementptr inbounds [28 x i8], [28 x i8]* @.str2, i32 0, i32 0
  %7 = call i32 (i8*, ...)* @printf(i8* %6)
  store i32 0, i32* %i, align 4
  br label %8

; <label>:8  		; preds = %0, %28
  %9 = load i32, i32* %i, align 4
  %10 = load i32, i32* %n, align 4
  %11 = icmp slt i32 %9, %10
  br i1 %11, label %12, label %31

; <label>:12   		; preds = %8
  store i32 0, i32* %j, align 4
  br label %13

; <label>:13		; preds = %12, %24
  %14 = load i32, i32* %j, align 4
  %15 = load i32, i32* %n, align 4
  %16 = icmp slt i32 %14, %15
  br i1 %16, label %17, label %27

; <label>:17                         		; preds = %13
  %18 = load i32, i32* %i, align 4
  %19 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* %a, i32 0, i32 %18
  %20 = load i32, i32* %j, align 4
  %21 = getelementptr inbounds [10 x i32], [10 x i32]* %19, i32 0, i32 %20
  %22 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %23 = call i32 (i8*, ...)* @scanf(i8* %22, i32* %21)
  br label %24

; <label>:24     		; preds = %17
  %25 = load i32, i32* %j, align 4
  %26 = add i32 %25, 1
  store i32 %26, i32* %j, align 4
  br label %13

; <label>:27		; preds = %13
  br label %28

; <label>:28     		; preds = %27
  %29 = load i32, i32* %i, align 4
  %30 = add i32 %29, 1
  store i32 %30, i32* %i, align 4
  br label %8

; <label>:31   		; preds = %8
  store i32 0, i32* %i, align 4
  br label %32

; <label>:32		; preds = %31, %41
  %33 = load i32, i32* %i, align 4
  %34 = load i32, i32* %n, align 4
  %35 = icmp slt i32 %33, %34
  br i1 %35, label %36, label %44

; <label>:36                                               		; preds = %32
  %37 = load i32, i32* %i, align 4
  %38 = getelementptr inbounds [10 x i32], [10 x i32]* %indeg, i32 0, i32 %37
  store i32 0, i32* %38, align 4
  %39 = load i32, i32* %i, align 4
  %40 = getelementptr inbounds [10 x i32], [10 x i32]* %flag, i32 0, i32 %39
  store i32 0, i32* %40, align 4
  br label %41

; <label>:41     		; preds = %36
  %42 = load i32, i32* %i, align 4
  %43 = add i32 %42, 1
  store i32 %43, i32* %i, align 4
  br label %32

; <label>:44  		; preds = %32
  store i32 0, i32* %i, align 4
  br label %45

; <label>:45		; preds = %44, %70
  %46 = load i32, i32* %i, align 4
  %47 = load i32, i32* %n, align 4
  %48 = icmp slt i32 %46, %47
  br i1 %48, label %49, label %73

; <label>:49  		; preds = %45
  store i32 0, i32* %j, align 4
  br label %50

; <label>:50		; preds = %49, %66
  %51 = load i32, i32* %j, align 4
  %52 = load i32, i32* %n, align 4
  %53 = icmp slt i32 %51, %52
  br i1 %53, label %54, label %69

; <label>:54                                                		; preds = %50
  %55 = load i32, i32* %i, align 4
  %56 = getelementptr inbounds [10 x i32], [10 x i32]* %indeg, i32 0, i32 %55
  %57 = load i32, i32* %j, align 4
  %58 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* %a, i32 0, i32 %57
  %59 = load i32, i32* %i, align 4
  %60 = getelementptr inbounds [10 x i32], [10 x i32]* %58, i32 0, i32 %59
  %61 = load i32, i32* %56, align 4
  %62 = load i32, i32* %60, align 4
  %63 = add i32 %61, %62
  %64 = load i32, i32* %i, align 4
  %65 = getelementptr inbounds [10 x i32], [10 x i32]* %indeg, i32 0, i32 %64
  store i32 %63, i32* %65, align 4
  br label %66

; <label>:66     		; preds = %54
  %67 = load i32, i32* %j, align 4
  %68 = add i32 %67, 1
  store i32 %68, i32* %j, align 4
  br label %50

; <label>:69		; preds = %50
  br label %70

; <label>:70     		; preds = %69
  %71 = load i32, i32* %i, align 4
  %72 = add i32 %71, 1
  store i32 %72, i32* %i, align 4
  br label %45

; <label>:73                                            		; preds = %45
  %74 = getelementptr inbounds [26 x i8], [26 x i8]* @.str3, i32 0, i32 0
  %75 = call i32 (i8*, ...)* @printf(i8* %74)
  br label %76

; <label>:76   		; preds = %73, %129
  %77 = load i32, i32* %count, align 4
  %78 = load i32, i32* %n, align 4
  %79 = icmp slt i32 %77, %78
  br i1 %79, label %80, label %132

; <label>:80  		; preds = %76
  store i32 0, i32* %k, align 4
  br label %81

; <label>:81		; preds = %80, %126
  %82 = load i32, i32* %k, align 4
  %83 = load i32, i32* %n, align 4
  %84 = icmp slt i32 %82, %83
  br i1 %84, label %85, label %129

; <label>:85      		; preds = %81
  %86 = load i32, i32* %k, align 4
  %87 = getelementptr inbounds [10 x i32], [10 x i32]* %indeg, i32 0, i32 %86
  %88 = load i32, i32* %87, align 4
  %89 = icmp eq i32 %88, 0
  br i1 %89, label %90, label %95

; <label>:90      		; preds = %85
  %91 = load i32, i32* %k, align 4
  %92 = getelementptr inbounds [10 x i32], [10 x i32]* %flag, i32 0, i32 %91
  %93 = load i32, i32* %92, align 4
  %94 = icmp eq i32 %93, 0
  br label %95

; <label>:95         		; preds = %85, %90
  %96 = phi i1 [ false, %85 ], [ %94, %90 ]
  br i1 %96, label %97, label %104

; <label>:97                                                 		; preds = %95
  %98 = getelementptr inbounds [7 x i8], [7 x i8]* @.str4, i32 0, i32 0
  %99 = load i32, i32* %k, align 4
  %100 = add i32 %99, 1
  %101 = call i32 (i8*, ...)* @printf(i8* %98, i32 %100)
  %102 = load i32, i32* %k, align 4
  %103 = getelementptr inbounds [10 x i32], [10 x i32]* %flag, i32 0, i32 %102
  store i32 1, i32* %103, align 4
  br label %104

; <label>:104		; preds = %95, %97
  store i32 0, i32* %i, align 4
  br label %105

; <label>:105		; preds = %104, %122
  %106 = load i32, i32* %i, align 4
  %107 = load i32, i32* %n, align 4
  %108 = icmp slt i32 %106, %107
  br i1 %108, label %109, label %125

; <label>:109     		; preds = %105
  %110 = load i32, i32* %i, align 4
  %111 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* %a, i32 0, i32 %110
  %112 = load i32, i32* %k, align 4
  %113 = getelementptr inbounds [10 x i32], [10 x i32]* %111, i32 0, i32 %112
  %114 = load i32, i32* %113, align 4
  %115 = icmp eq i32 %114, 1
  br i1 %115, label %116, label %121

; <label>:116     		; preds = %109
  %117 = load i32, i32* %k, align 4
  %118 = getelementptr inbounds [10 x i32], [10 x i32]* %indeg, i32 0, i32 %117
  %119 = load i32, i32* %118, align 4
  %120 = sub i32 %119, 1
  store i32 %120, i32* %118, align 4
  br label %121

; <label>:121		; preds = %109, %116
  br label %122

; <label>:122    		; preds = %121
  %123 = load i32, i32* %i, align 4
  %124 = add i32 %123, 1
  store i32 %124, i32* %i, align 4
  br label %105

; <label>:125		; preds = %105
  br label %126

; <label>:126    		; preds = %125
  %127 = load i32, i32* %k, align 4
  %128 = add i32 %127, 1
  store i32 %128, i32* %k, align 4
  br label %81

; <label>:129         		; preds = %81
  %130 = load i32, i32* %count, align 4
  %131 = add i32 %130, 1
  store i32 %131, i32* %count, align 4
  br label %76

; <label>:132                                          		; preds = %76
  %133 = getelementptr inbounds [2 x i8], [2 x i8]* @.str5, i32 0, i32 0
  %134 = call i32 (i8*, ...)* @printf(i8* %133)
  ret i32 0
}
