@.str = private unnamed_addr constant [26 x i8] c"Enter number of elements\0A\00", align 16
@.str1 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str2 = private unnamed_addr constant [19 x i8] c"Enter %d integers\0A\00", align 16
@.str3 = private unnamed_addr constant [21 x i8] c"Enter value to find\0A\00", align 16
@.str4 = private unnamed_addr constant [26 x i8] c"%d found at location %d.\0A\00", align 16
@.str5 = private unnamed_addr constant [43 x i8] c"Not found! %d is not present in the list.\0A\00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define i32 @main() nounwind {
  %n = alloca i32, align 4
  %search = alloca i32, align 4
  %array = alloca [100 x i32], align 16
  %1 = getelementptr inbounds [26 x i8]* @.str, i32 0, i32 0
  %2 = call i32 (i8*, ...)* @printf(i8* %1)
  %3 = getelementptr inbounds [3 x i8]* @.str1, i32 0, i32 0
  %4 = call i32 (i8*, ...)* @scanf(i8* %3, i32* %n)
  %5 = getelementptr inbounds [19 x i8]* @.str2, i32 0, i32 0
  %6 = load i32* %n, align 4
  %7 = call i32 (i8*, ...)* @printf(i8* %5, i32 %6)
  br label %8

; <label>:8        		; preds = %15, %0
  %c.0 = phi i32 [ %16, %15 ], [ 0, %0 ]
  %9 = load i32* %n, align 4
  %10 = icmp slt i32 %c.0, %9
  br i1 %10, label %11, label %17

; <label>:11                                       		; preds = %8
  %12 = getelementptr inbounds [100 x i32]* %array, i32 0, i32 %c.0
  %13 = getelementptr inbounds [3 x i8]* @.str1, i32 0, i32 0
  %14 = call i32 (i8*, ...)* @scanf(i8* %13, i32* %12)
  br label %15

; <label>:15		; preds = %11
  %16 = add i32 %c.0, 1
  br label %8

; <label>:17                                  		; preds = %8
  %18 = getelementptr inbounds [21 x i8]* @.str3, i32 0, i32 0
  %19 = call i32 (i8*, ...)* @printf(i8* %18)
  %20 = getelementptr inbounds [3 x i8]* @.str1, i32 0, i32 0
  %21 = call i32 (i8*, ...)* @scanf(i8* %20, i32* %search)
  %22 = load i32* %n, align 4
  %23 = sub i32 %22, 1
  %24 = sdiv i32 %23, 2
  br label %25

; <label>:25                		; preds = %45, %17
  %middle.0 = phi i32 [ %47, %45 ], [ %24, %17 ]
  %last.0 = phi i32 [ %last.1, %45 ], [ %23, %17 ]
  %first.0 = phi i32 [ %first.1, %45 ], [ 0, %17 ]
  %26 = icmp sle i32 %first.0, %last.0
  br i1 %26, label %27, label %48

; <label>:27                                           		; preds = %25
  %28 = getelementptr inbounds [100 x i32]* %array, i32 0, i32 %middle.0
  %29 = load i32* %search, align 4
  %30 = load i32* %28, align 4
  %31 = icmp slt i32 %30, %29
  br i1 %31, label %32, label %34

; <label>:32		; preds = %27
  %33 = add i32 %middle.0, 1
  br label %45

; <label>:34     		; preds = %27
  %35 = load i32* %search, align 4
  %36 = load i32* %28, align 4
  %37 = icmp eq i32 %36, %35
  br i1 %37, label %38, label %42

; <label>:38                                  		; preds = %34
  %39 = getelementptr inbounds [26 x i8]* @.str4, i32 0, i32 0
  %40 = load i32* %search, align 4
  %41 = add i32 %middle.0, 1
  %42 = call i32 (i8*, ...)* @printf(i8* %39, i32 %40, i32 %33)
  br label %48

; <label>:42		; preds = %34
  %43 = sub i32 %middle.0, 1
  br label %44

; <label>:44		; preds = %42
  br label %45

; <label>:45                  		; preds = %44, %32
  %last.1 = phi i32 [ %43, %44 ], [ %last.0, %32 ]
  %first.1 = phi i32 [ %first.0, %44 ], [ %33, %32 ]
  %46 = add i32 %first.1, %last.1
  %47 = sdiv i32 %46, 2
  br label %25

; <label>:48    		; preds = %38, %25
  %49 = icmp sgt i32 %first.0, %last.0
  br i1 %49, label %50, label %54

; <label>:50                                 		; preds = %48
  %51 = getelementptr inbounds [43 x i8]* @.str5, i32 0, i32 0
  %52 = load i32* %search, align 4
  %53 = call i32 (i8*, ...)* @printf(i8* %51, i32 %52)
  br label %54

; <label>:54		; preds = %50, %48
  ret i32 0
}