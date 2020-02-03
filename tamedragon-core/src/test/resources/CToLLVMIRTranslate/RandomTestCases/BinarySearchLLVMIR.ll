@.str = private unnamed_addr constant [26 x i8] c"Enter number of elements\0A\00", align 16
@.str1 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str2 = private unnamed_addr constant [19 x i8] c"Enter %d integers\0A\00", align 16
@.str3 = private unnamed_addr constant [21 x i8] c"Enter value to find\0A\00", align 16
@.str4 = private unnamed_addr constant [26 x i8] c"%d found at location %d.\0A\00", align 16
@.str5 = private unnamed_addr constant [43 x i8] c"Not found! %d is not present in the list.\0A\00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %c = alloca i32, align 4
  %first = alloca i32, align 4
  %last = alloca i32, align 4
  %middle = alloca i32, align 4
  %n = alloca i32, align 4
  %search = alloca i32, align 4
  %array = alloca [100 x i32], align 16
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [26 x i8], [26 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %n)
  %6 = getelementptr inbounds [19 x i8], [19 x i8]* @.str2, i32 0, i32 0
  %7 = load i32, i32* %n, align 4
  %8 = call i32 (i8*, ...)* @printf(i8* %6, i32 %7)
  store i32 0, i32* %c, align 4
  br label %9

; <label>:9  		; preds = %0, %18
  %10 = load i32, i32* %c, align 4
  %11 = load i32, i32* %n, align 4
  %12 = icmp slt i32 %10, %11
  br i1 %12, label %13, label %21

; <label>:13                          		; preds = %9
  %14 = load i32, i32* %c, align 4
  %15 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 %14
  %16 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %17 = call i32 (i8*, ...)* @scanf(i8* %16, i32* %15)
  br label %18

; <label>:18     		; preds = %13
  %19 = load i32, i32* %c, align 4
  %20 = add i32 %19, 1
  store i32 %20, i32* %c, align 4
  br label %9

; <label>:21                                             		; preds = %9
  %22 = getelementptr inbounds [21 x i8], [21 x i8]* @.str3, i32 0, i32 0
  %23 = call i32 (i8*, ...)* @printf(i8* %22)
  %24 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %25 = call i32 (i8*, ...)* @scanf(i8* %24, i32* %search)
  store i32 0, i32* %first, align 4
  %26 = load i32, i32* %n, align 4
  %27 = sub i32 %26, 1
  store i32 %27, i32* %last, align 4
  %28 = load i32, i32* %first, align 4
  %29 = load i32, i32* %last, align 4
  %30 = add i32 %28, %29
  %31 = sdiv i32 %30, 2
  store i32 %31, i32* %middle, align 4
  br label %32

; <label>:32    		; preds = %21, %61
  %33 = load i32, i32* %first, align 4
  %34 = load i32, i32* %last, align 4
  %35 = icmp sle i32 %33, %34
  br i1 %35, label %36, label %66

; <label>:36                                                  		; preds = %32
  %37 = load i32, i32* %middle, align 4
  %38 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 %37
  %39 = load i32, i32* %search, align 4
  %40 = load i32, i32* %38, align 4
  %41 = icmp slt i32 %40, %39
  br i1 %41, label %42, label %45

; <label>:42          		; preds = %36
  %43 = load i32, i32* %middle, align 4
  %44 = add i32 %43, 1
  store i32 %44, i32* %first, align 4
  br label %61

; <label>:45                                                  		; preds = %36
  %46 = load i32, i32* %middle, align 4
  %47 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 %46
  %48 = load i32, i32* %search, align 4
  %49 = load i32, i32* %47, align 4
  %50 = icmp eq i32 %49, %48
  br i1 %50, label %51, label %57

; <label>:51                                            		; preds = %45
  %52 = getelementptr inbounds [26 x i8], [26 x i8]* @.str4, i32 0, i32 0
  %53 = load i32, i32* %search, align 4
  %54 = load i32, i32* %middle, align 4
  %55 = add i32 %54, 1
  %56 = call i32 (i8*, ...)* @printf(i8* %52, i32 %53, i32 %55)
  br label %66

; <label>:57          		; preds = %45
  %58 = load i32, i32* %middle, align 4
  %59 = sub i32 %58, 1
  store i32 %59, i32* %last, align 4
  br label %60

; <label>:60		; preds = %57
  br label %61

; <label>:61    		; preds = %42, %60
  %62 = load i32, i32* %first, align 4
  %63 = load i32, i32* %last, align 4
  %64 = add i32 %62, %63
  %65 = sdiv i32 %64, 2
  store i32 %65, i32* %middle, align 4
  br label %32

; <label>:66    		; preds = %32, %51
  %67 = load i32, i32* %first, align 4
  %68 = load i32, i32* %last, align 4
  %69 = icmp sgt i32 %67, %68
  br i1 %69, label %70, label %74

; <label>:70                                            		; preds = %66
  %71 = getelementptr inbounds [43 x i8], [43 x i8]* @.str5, i32 0, i32 0
  %72 = load i32, i32* %search, align 4
  %73 = call i32 (i8*, ...)* @printf(i8* %71, i32 %72)
  br label %74

; <label>:74		; preds = %66, %70
  ret i32 0
}
