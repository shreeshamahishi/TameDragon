@.str = private unnamed_addr constant [54 x i8] c"Enter the number of rows and columns of first matrix\0A\00", align 16
@.str1 = private unnamed_addr constant [5 x i8] c"%d%d\00", align 1
@.str2 = private unnamed_addr constant [36 x i8] c"Enter the elements of first matrix\0A\00", align 16
@.str3 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str4 = private unnamed_addr constant [55 x i8] c"Enter the number of rows and columns of second matrix\0A\00", align 16
@.str5 = private unnamed_addr constant [67 x i8] c"Matrices with entered orders can't be multiplied with each other.\0A\00", align 16
@.str6 = private unnamed_addr constant [37 x i8] c"Enter the elements of second matrix\0A\00", align 16
@.str7 = private unnamed_addr constant [31 x i8] c"Product of entered matrices:-\0A\00", align 16
@.str8 = private unnamed_addr constant [4 x i8] c"%d\09\00", align 1
@.str9 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %m = alloca i32, align 4
  %n = alloca i32, align 4
  %p = alloca i32, align 4
  %q = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca i32, align 4
  %k = alloca i32, align 4
  %sum = alloca i32, align 4
  %first = alloca [10 x [10 x i32]], align 16
  %second = alloca [10 x [10 x i32]], align 16
  %multiply = alloca [10 x [10 x i32]], align 16
  store i32 0, i32* %1, align 4
  store i32 0, i32* %sum, align 4
  %2 = getelementptr inbounds [54 x i8], [54 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [5 x i8], [5 x i8]* @.str1, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %m, i32* %n)
  %6 = getelementptr inbounds [36 x i8], [36 x i8]* @.str2, i32 0, i32 0
  %7 = call i32 (i8*, ...)* @printf(i8* %6)
  store i32 0, i32* %c, align 4
  br label %8

; <label>:8  		; preds = %0, %28
  %9 = load i32, i32* %c, align 4
  %10 = load i32, i32* %m, align 4
  %11 = icmp slt i32 %9, %10
  br i1 %11, label %12, label %31

; <label>:12   		; preds = %8
  store i32 0, i32* %d, align 4
  br label %13

; <label>:13		; preds = %12, %24
  %14 = load i32, i32* %d, align 4
  %15 = load i32, i32* %n, align 4
  %16 = icmp slt i32 %14, %15
  br i1 %16, label %17, label %27

; <label>:17                         		; preds = %13
  %18 = load i32, i32* %c, align 4
  %19 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* %first, i32 0, i32 %18
  %20 = load i32, i32* %d, align 4
  %21 = getelementptr inbounds [10 x i32], [10 x i32]* %19, i32 0, i32 %20
  %22 = getelementptr inbounds [3 x i8], [3 x i8]* @.str3, i32 0, i32 0
  %23 = call i32 (i8*, ...)* @scanf(i8* %22, i32* %21)
  br label %24

; <label>:24     		; preds = %17
  %25 = load i32, i32* %d, align 4
  %26 = add i32 %25, 1
  store i32 %26, i32* %d, align 4
  br label %13

; <label>:27		; preds = %13
  br label %28

; <label>:28     		; preds = %27
  %29 = load i32, i32* %c, align 4
  %30 = add i32 %29, 1
  store i32 %30, i32* %c, align 4
  br label %8

; <label>:31                                             		; preds = %8
  %32 = getelementptr inbounds [55 x i8], [55 x i8]* @.str4, i32 0, i32 0
  %33 = call i32 (i8*, ...)* @printf(i8* %32)
  %34 = getelementptr inbounds [5 x i8], [5 x i8]* @.str1, i32 0, i32 0
  %35 = call i32 (i8*, ...)* @scanf(i8* %34, i32* %p, i32* %q)
  %36 = load i32, i32* %n, align 4
  %37 = load i32, i32* %p, align 4
  %38 = icmp ne i32 %36, %37
  br i1 %38, label %39, label %42

; <label>:39                                            		; preds = %31
  %40 = getelementptr inbounds [67 x i8], [67 x i8]* @.str5, i32 0, i32 0
  %41 = call i32 (i8*, ...)* @printf(i8* %40)
  br label %143

; <label>:42                                            		; preds = %31
  %43 = getelementptr inbounds [37 x i8], [37 x i8]* @.str6, i32 0, i32 0
  %44 = call i32 (i8*, ...)* @printf(i8* %43)
  store i32 0, i32* %c, align 4
  br label %45

; <label>:45		; preds = %42, %65
  %46 = load i32, i32* %c, align 4
  %47 = load i32, i32* %p, align 4
  %48 = icmp slt i32 %46, %47
  br i1 %48, label %49, label %68

; <label>:49  		; preds = %45
  store i32 0, i32* %d, align 4
  br label %50

; <label>:50		; preds = %49, %61
  %51 = load i32, i32* %d, align 4
  %52 = load i32, i32* %q, align 4
  %53 = icmp slt i32 %51, %52
  br i1 %53, label %54, label %64

; <label>:54                         		; preds = %50
  %55 = load i32, i32* %c, align 4
  %56 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* %second, i32 0, i32 %55
  %57 = load i32, i32* %d, align 4
  %58 = getelementptr inbounds [10 x i32], [10 x i32]* %56, i32 0, i32 %57
  %59 = getelementptr inbounds [3 x i8], [3 x i8]* @.str3, i32 0, i32 0
  %60 = call i32 (i8*, ...)* @scanf(i8* %59, i32* %58)
  br label %61

; <label>:61     		; preds = %54
  %62 = load i32, i32* %d, align 4
  %63 = add i32 %62, 1
  store i32 %63, i32* %d, align 4
  br label %50

; <label>:64		; preds = %50
  br label %65

; <label>:65     		; preds = %64
  %66 = load i32, i32* %c, align 4
  %67 = add i32 %66, 1
  store i32 %67, i32* %c, align 4
  br label %45

; <label>:68  		; preds = %45
  store i32 0, i32* %c, align 4
  br label %69

; <label>:69		; preds = %68, %110
  %70 = load i32, i32* %c, align 4
  %71 = load i32, i32* %m, align 4
  %72 = icmp slt i32 %70, %71
  br i1 %72, label %73, label %113

; <label>:73  		; preds = %69
  store i32 0, i32* %d, align 4
  br label %74

; <label>:74		; preds = %73, %106
  %75 = load i32, i32* %d, align 4
  %76 = load i32, i32* %q, align 4
  %77 = icmp slt i32 %75, %76
  br i1 %77, label %78, label %109

; <label>:78  		; preds = %74
  store i32 0, i32* %k, align 4
  br label %79

; <label>:79		; preds = %78, %97
  %80 = load i32, i32* %k, align 4
  %81 = load i32, i32* %p, align 4
  %82 = icmp slt i32 %80, %81
  br i1 %82, label %83, label %100

; <label>:83                                             		; preds = %79
  %84 = load i32, i32* %sum, align 4
  %85 = load i32, i32* %c, align 4
  %86 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* %first, i32 0, i32 %85
  %87 = load i32, i32* %k, align 4
  %88 = getelementptr inbounds [10 x i32], [10 x i32]* %86, i32 0, i32 %87
  %89 = load i32, i32* %k, align 4
  %90 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* %second, i32 0, i32 %89
  %91 = load i32, i32* %d, align 4
  %92 = getelementptr inbounds [10 x i32], [10 x i32]* %90, i32 0, i32 %91
  %93 = load i32, i32* %88, align 4
  %94 = load i32, i32* %92, align 4
  %95 = mul i32 %93, %94
  %96 = add i32 %84, %95
  store i32 %96, i32* %sum, align 4
  br label %97

; <label>:97     		; preds = %83
  %98 = load i32, i32* %k, align 4
  %99 = add i32 %98, 1
  store i32 %99, i32* %k, align 4
  br label %79

; <label>:100      		; preds = %79
  %101 = load i32, i32* %c, align 4
  %102 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* %multiply, i32 0, i32 %101
  %103 = load i32, i32* %d, align 4
  %104 = getelementptr inbounds [10 x i32], [10 x i32]* %102, i32 0, i32 %103
  %105 = load i32, i32* %sum, align 4
  store i32 %105, i32* %104, align 4
  store i32 0, i32* %sum, align 4
  br label %106

; <label>:106    		; preds = %100
  %107 = load i32, i32* %d, align 4
  %108 = add i32 %107, 1
  store i32 %108, i32* %d, align 4
  br label %74

; <label>:109		; preds = %74
  br label %110

; <label>:110    		; preds = %109
  %111 = load i32, i32* %c, align 4
  %112 = add i32 %111, 1
  store i32 %112, i32* %c, align 4
  br label %69

; <label>:113                                            		; preds = %69
  %114 = getelementptr inbounds [31 x i8], [31 x i8]* @.str7, i32 0, i32 0
  %115 = call i32 (i8*, ...)* @printf(i8* %114)
  store i32 0, i32* %c, align 4
  br label %116

; <label>:116		; preds = %113, %139
  %117 = load i32, i32* %c, align 4
  %118 = load i32, i32* %m, align 4
  %119 = icmp slt i32 %117, %118
  br i1 %119, label %120, label %142

; <label>:120		; preds = %116
  store i32 0, i32* %d, align 4
  br label %121

; <label>:121		; preds = %120, %133
  %122 = load i32, i32* %d, align 4
  %123 = load i32, i32* %q, align 4
  %124 = icmp slt i32 %122, %123
  br i1 %124, label %125, label %136

; <label>:125                          		; preds = %121
  %126 = load i32, i32* %c, align 4
  %127 = getelementptr inbounds [10 x [10 x i32]], [10 x [10 x i32]]* %multiply, i32 0, i32 %126
  %128 = load i32, i32* %d, align 4
  %129 = getelementptr inbounds [10 x i32], [10 x i32]* %127, i32 0, i32 %128
  %130 = getelementptr inbounds [4 x i8], [4 x i8]* @.str8, i32 0, i32 0
  %131 = load i32, i32* %129, align 4
  %132 = call i32 (i8*, ...)* @printf(i8* %130, i32 %131)
  br label %133

; <label>:133    		; preds = %125
  %134 = load i32, i32* %d, align 4
  %135 = add i32 %134, 1
  store i32 %135, i32* %d, align 4
  br label %121

; <label>:136                                         		; preds = %121
  %137 = getelementptr inbounds [2 x i8], [2 x i8]* @.str9, i32 0, i32 0
  %138 = call i32 (i8*, ...)* @printf(i8* %137)
  br label %139

; <label>:139    		; preds = %136
  %140 = load i32, i32* %c, align 4
  %141 = add i32 %140, 1
  store i32 %141, i32* %c, align 4
  br label %116

; <label>:142		; preds = %116
  br label %143

; <label>:143		; preds = %39, %142
  ret i32 0
}
