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
  %1 = getelementptr inbounds [26 x i8], [26 x i8]* @.str, i32 0, i32 0
  %2 = call i32 (i8*, ...)* @printf(i8* %1)
  %3 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %4 = call i32 (i8*, ...)* @scanf(i8* %3, i32* %n)
  %5 = getelementptr inbounds [19 x i8], [19 x i8]* @.str2, i32 0, i32 0
  %6 = load i32, i32* %n, align 4
  %7 = call i32 (i8*, ...)* @printf(i8* %5, i32 %6)
  br label %8

; <label>:8        		; preds = %15, %0
  %c.0 = phi i32 [ %16, %15 ], [ 0, %0 ]
  %9 = load i32, i32* %n, align 4
  %10 = icmp slt i32 %c.0, %9
  br i1 %10, label %11, label %17

; <label>:11                                       		; preds = %8
  %12 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 %c.0
  %13 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %14 = call i32 (i8*, ...)* @scanf(i8* %13, i32* %12)
  br label %15

; <label>:15		; preds = %11
  %16 = add i32 %c.0, 1
  br label %8

; <label>:17                                  		; preds = %8
  %18 = getelementptr inbounds [21 x i8], [21 x i8]* @.str3, i32 0, i32 0
  %19 = call i32 (i8*, ...)* @printf(i8* %18)
  %20 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %21 = call i32 (i8*, ...)* @scanf(i8* %20, i32* %search)
  %22 = load i32, i32* %n, align 4
  %23 = sub i32 %22, 1
  %24 = add i32 0, %23
  %25 = sdiv i32 %24, 2
  br label %26

; <label>:26                		; preds = %48, %17
  %middle.0 = phi i32 [ %50, %48 ], [ %25, %17 ]
  %last.0 = phi i32 [ %last.1, %48 ], [ %23, %17 ]
  %first.0 = phi i32 [ %first.1, %48 ], [ 0, %17 ]
  %27 = icmp sle i32 %first.0, %last.0
  br i1 %27, label %28, label %51

; <label>:28                                           		; preds = %26
  %29 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 %middle.0
  %30 = load i32, i32* %search, align 4
  %31 = load i32, i32* %29, align 4
  %32 = icmp slt i32 %31, %30
  br i1 %32, label %33, label %35

; <label>:33		; preds = %28
  %34 = add i32 %middle.0, 1
  br label %48

; <label>:35                                           		; preds = %28
  %36 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 %middle.0
  %37 = load i32, i32* %search, align 4
  %38 = load i32, i32* %36, align 4
  %39 = icmp eq i32 %38, %37
  br i1 %39, label %40, label %45

; <label>:40                                  		; preds = %35
  %41 = getelementptr inbounds [26 x i8], [26 x i8]* @.str4, i32 0, i32 0
  %42 = load i32, i32* %search, align 4
  %43 = add i32 %middle.0, 1
  %44 = call i32 (i8*, ...)* @printf(i8* %41, i32 %42, i32 %43)
  br label %51

; <label>:45		; preds = %35
  %46 = sub i32 %middle.0, 1
  br label %47

; <label>:47		; preds = %45
  br label %48

; <label>:48                  		; preds = %47, %33
  %last.1 = phi i32 [ %46, %47 ], [ %last.0, %33 ]
  %first.1 = phi i32 [ %first.0, %47 ], [ %34, %33 ]
  %49 = add i32 %first.1, %last.1
  %50 = sdiv i32 %49, 2
  br label %26

; <label>:51    		; preds = %40, %26
  %52 = icmp sgt i32 %first.0, %last.0
  br i1 %52, label %53, label %57

; <label>:53                                 		; preds = %51
  %54 = getelementptr inbounds [43 x i8], [43 x i8]* @.str5, i32 0, i32 0
  %55 = load i32, i32* %search, align 4
  %56 = call i32 (i8*, ...)* @printf(i8* %54, i32 %55)
  br label %57

; <label>:57		; preds = %53, %51
  ret i32 0
}