@.str = private unnamed_addr constant [18 x i8] c"Enter the String \00", align 16
@.str1 = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str2 = private unnamed_addr constant [39 x i8] c"Enter pattern to search in the String \00", align 16
@.str3 = private unnamed_addr constant [23 x i8] c"Pattern doesn't exist\0A\00", align 16
@.str4 = private unnamed_addr constant [29 x i8] c"Pattern exist from index %d\0A\00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

declare i32 @strlen(i8*) 

define i32 @mod(i32 %a, i32 %p, i32 %m) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %sqr = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %p, i32* %2, align 4
  store i32 %m, i32* %3, align 4
  %5 = load i32, i32* %2, align 4
  %6 = icmp eq i32 %5, 0
  br i1 %6, label %7, label %8

; <label>:7    		; preds = %0
  store i32 1, i32* %4, align 4
  br label %34

; <label>:8       		; preds = %0
  %9 = load i32, i32* %1, align 4
  %10 = load i32, i32* %2, align 4
  %11 = sdiv i32 %10, 2
  %12 = load i32, i32* %3, align 4
  %13 = call i32 @mod(i32 %9, i32 %11, i32 %12)
  %14 = load i32, i32* %3, align 4
  %15 = srem i32 %13, %14
  store i32 %15, i32* %sqr, align 4
  %16 = load i32, i32* %sqr, align 4
  %17 = load i32, i32* %sqr, align 4
  %18 = mul i32 %16, %17
  %19 = load i32, i32* %3, align 4
  %20 = srem i32 %18, %19
  store i32 %20, i32* %sqr, align 4
  %21 = load i32, i32* %2, align 4
  %22 = and i32 %21, 1
  %23 = icmp ne i32 %22, 0
  br i1 %23, label %24, label %32

; <label>:24        		; preds = %8
  %25 = load i32, i32* %1, align 4
  %26 = load i32, i32* %3, align 4
  %27 = srem i32 %25, %26
  %28 = load i32, i32* %sqr, align 4
  %29 = mul i32 %27, %28
  %30 = load i32, i32* %3, align 4
  %31 = srem i32 %29, %30
  store i32 %31, i32* %4, align 4
  br label %34

; <label>:32        		; preds = %8
  %33 = load i32, i32* %sqr, align 4
  store i32 %33, i32* %4, align 4
  br label %34

; <label>:34		; preds = %7, %24, %32
  %35 = load i32, i32* %4, align 4
  ret i32 %35
}

define i32 @tonum(i8 %c) nounwind {
  %1 = alloca i8, align 1
  %2 = alloca i32, align 4
  store i8 %c, i8* %1, align 1
  %3 = load i8, i8* %1, align 1
  %4 = icmp sge i8 %3, 65
  br i1 %4, label %5, label %8

; <label>:5    		; preds = %0
  %6 = load i8, i8* %1, align 1
  %7 = icmp sle i8 %6, 90
  br label %8

; <label>:8        		; preds = %0, %5
  %9 = phi i1 [ false, %0 ], [ %7, %5 ]
  br i1 %9, label %10, label %14

; <label>:10     		; preds = %8
  %11 = load i8, i8* %1, align 1
  %12 = sext i8 %11 to i32
  %13 = sub i32 %12, 65
  store i32 %13, i32* %2, align 4
  br label %19

; <label>:14     		; preds = %8
  %15 = load i8, i8* %1, align 1
  %16 = sext i8 %15 to i32
  %17 = sub i32 %16, 97
  %18 = add i32 %17, 26
  store i32 %18, i32* %2, align 4
  br label %19

; <label>:19		; preds = %10, %14
  %20 = load i32, i32* %2, align 4
  ret i32 %20
}

define i32 @RabinKarpMatch(i8* %T, i8* %P, i32 %d, i32 %q) nounwind {
  %1 = alloca i8*, align 8
  %2 = alloca i8*, align 8
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  %p = alloca i32, align 4
  %t = alloca i32, align 4
  %n = alloca i32, align 4
  %m = alloca i32, align 4
  %h = alloca i32, align 4
  %found = alloca i32, align 4
  store i8* %T, i8** %1, align 8
  store i8* %P, i8** %2, align 8
  store i32 %d, i32* %3, align 4
  store i32 %q, i32* %4, align 4
  %6 = load i8*, i8** %1, align 8
  %7 = call i32 @strlen(i8* %6)
  store i32 %7, i32* %n, align 4
  %8 = load i8*, i8** %2, align 8
  %9 = call i32 @strlen(i8* %8)
  store i32 %9, i32* %m, align 4
  %10 = load i32, i32* %3, align 4
  %11 = load i32, i32* %m, align 4
  %12 = sub i32 %11, 1
  %13 = load i32, i32* %4, align 4
  %14 = call i32 @mod(i32 %10, i32 %12, i32 %13)
  store i32 %14, i32* %h, align 4
  store i32 0, i32* %t, align 4
  store i32 0, i32* %p, align 4
  store i32 0, i32* %i, align 4
  br label %15

; <label>:15 		; preds = %0, %42
  %16 = load i32, i32* %i, align 4
  %17 = load i32, i32* %m, align 4
  %18 = icmp slt i32 %16, %17
  br i1 %18, label %19, label %45

; <label>:19                      		; preds = %15
  %20 = load i32, i32* %3, align 4
  %21 = load i32, i32* %p, align 4
  %22 = mul i32 %20, %21
  %23 = load i8*, i8** %2, align 8
  %24 = load i32, i32* %i, align 4
  %25 = getelementptr inbounds i8, i8* %23, i32 %24
  %26 = load i8, i8* %25, align 1
  %27 = call i32 @tonum(i8 %26)
  %28 = add i32 %22, %27
  %29 = load i32, i32* %4, align 4
  %30 = srem i32 %28, %29
  store i32 %30, i32* %p, align 4
  %31 = load i32, i32* %3, align 4
  %32 = load i32, i32* %t, align 4
  %33 = mul i32 %31, %32
  %34 = load i8*, i8** %1, align 8
  %35 = load i32, i32* %i, align 4
  %36 = getelementptr inbounds i8, i8* %34, i32 %35
  %37 = load i8, i8* %36, align 1
  %38 = call i32 @tonum(i8 %37)
  %39 = add i32 %33, %38
  %40 = load i32, i32* %4, align 4
  %41 = srem i32 %39, %40
  store i32 %41, i32* %t, align 4
  br label %42

; <label>:42     		; preds = %19
  %43 = load i32, i32* %i, align 4
  %44 = add i32 %43, 1
  store i32 %44, i32* %i, align 4
  br label %15

; <label>:45  		; preds = %15
  store i32 0, i32* %i, align 4
  br label %46

; <label>:46		; preds = %45, %109
  %47 = load i32, i32* %i, align 4
  %48 = load i32, i32* %n, align 4
  %49 = load i32, i32* %m, align 4
  %50 = sub i32 %48, %49
  %51 = icmp sle i32 %47, %50
  br i1 %51, label %52, label %112

; <label>:52     		; preds = %46
  %53 = load i32, i32* %p, align 4
  %54 = load i32, i32* %t, align 4
  %55 = icmp eq i32 %53, %54
  br i1 %55, label %56, label %84

; <label>:56      		; preds = %52
  store i32 1, i32* %found, align 4
  store i32 0, i32* %j, align 4
  br label %57

; <label>:57		; preds = %56, %75
  %58 = load i32, i32* %j, align 4
  %59 = load i32, i32* %m, align 4
  %60 = icmp slt i32 %58, %59
  br i1 %60, label %61, label %78

; <label>:61                      		; preds = %57
  %62 = load i8*, i8** %2, align 8
  %63 = load i32, i32* %j, align 4
  %64 = getelementptr inbounds i8, i8* %62, i32 %63
  %65 = load i8*, i8** %1, align 8
  %66 = load i32, i32* %i, align 4
  %67 = load i32, i32* %j, align 4
  %68 = add i32 %66, %67
  %69 = getelementptr inbounds i8, i8* %65, i32 %68
  %70 = load i8, i8* %64, align 1
  %71 = load i8, i8* %69, align 1
  %72 = icmp ne i8 %70, %71
  br i1 %72, label %73, label %74

; <label>:73      		; preds = %61
  store i32 0, i32* %found, align 4
  br label %78

; <label>:74		; preds = %61
  br label %75

; <label>:75     		; preds = %74
  %76 = load i32, i32* %j, align 4
  %77 = add i32 %76, 1
  store i32 %77, i32* %j, align 4
  br label %57

; <label>:78    		; preds = %57, %73
  %79 = load i32, i32* %found, align 4
  %80 = icmp ne i32 %79, 0
  br i1 %80, label %81, label %83

; <label>:81     		; preds = %78
  %82 = load i32, i32* %i, align 4
  store i32 %82, i32* %5, align 4
  br label %113

; <label>:83		; preds = %78
  br label %108

; <label>:84      		; preds = %52
  %85 = load i32, i32* %3, align 4
  %86 = load i32, i32* %t, align 4
  %87 = load i8*, i8** %1, align 8
  %88 = load i32, i32* %i, align 4
  %89 = getelementptr inbounds i8, i8* %87, i32 %88
  %90 = load i8, i8* %89, align 1
  %91 = call i32 @tonum(i8 %90)
  %92 = load i32, i32* %h, align 4
  %93 = mul i32 %91, %92
  %94 = load i32, i32* %4, align 4
  %95 = srem i32 %93, %94
  %96 = sub i32 %86, %95
  %97 = mul i32 %85, %96
  %98 = load i8*, i8** %1, align 8
  %99 = load i32, i32* %i, align 4
  %100 = load i32, i32* %m, align 4
  %101 = add i32 %99, %100
  %102 = getelementptr inbounds i8, i8* %98, i32 %101
  %103 = load i8, i8* %102, align 1
  %104 = call i32 @tonum(i8 %103)
  %105 = add i32 %97, %104
  %106 = load i32, i32* %4, align 4
  %107 = srem i32 %105, %106
  store i32 %107, i32* %t, align 4
  br label %108

; <label>:108		; preds = %83, %84
  br label %109

; <label>:109    		; preds = %108
  %110 = load i32, i32* %i, align 4
  %111 = add i32 %110, 1
  store i32 %111, i32* %i, align 4
  br label %46

; <label>:112  		; preds = %46
  store i32 -1, i32* %5, align 4
  br label %113

; <label>:113		; preds = %81, %112
  %114 = load i32, i32* %5, align 4
  ret i32 %114
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %str = alloca [100 x i8], align 16
  %pattern = alloca [100 x i8], align 16
  %index = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [18 x i8], [18 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %5 = getelementptr inbounds [100 x i8], [100 x i8]* %str, i32 0, i32 0
  %6 = call i32 (i8*, ...)* @scanf(i8* %4, i8* %5)
  %7 = getelementptr inbounds [39 x i8], [39 x i8]* @.str2, i32 0, i32 0
  %8 = call i32 (i8*, ...)* @printf(i8* %7)
  %9 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %10 = getelementptr inbounds [100 x i8], [100 x i8]* %pattern, i32 0, i32 0
  %11 = call i32 (i8*, ...)* @scanf(i8* %9, i8* %10)
  %12 = getelementptr inbounds [100 x i8], [100 x i8]* %str, i32 0, i32 0
  %13 = getelementptr inbounds [100 x i8], [100 x i8]* %pattern, i32 0, i32 0
  %14 = call i32 @RabinKarpMatch(i8* %12, i8* %13, i32 26, i32 1)
  store i32 %14, i32* %index, align 4
  %15 = load i32, i32* %index, align 4
  %16 = icmp eq i32 %15, -1
  br i1 %16, label %17, label %20

; <label>:17                                             		; preds = %0
  %18 = getelementptr inbounds [23 x i8], [23 x i8]* @.str3, i32 0, i32 0
  %19 = call i32 (i8*, ...)* @printf(i8* %18)
  br label %24

; <label>:20                                             		; preds = %0
  %21 = getelementptr inbounds [29 x i8], [29 x i8]* @.str4, i32 0, i32 0
  %22 = load i32, i32* %index, align 4
  %23 = call i32 (i8*, ...)* @printf(i8* %21, i32 %22)
  br label %24

; <label>:24		; preds = %17, %20
  ret i32 0
}
