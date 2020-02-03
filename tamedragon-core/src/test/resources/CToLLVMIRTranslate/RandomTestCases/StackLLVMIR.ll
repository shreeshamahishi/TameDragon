@stack = common global [200 x i32] zeroinitializer, align 16
@top = common global i32 0, align 4
@.str = private unnamed_addr constant [11 x i8] c"STACK FULL\00", align 1
@.str1 = private unnamed_addr constant [12 x i8] c"STACK EMPTY\00", align 1
@.str2 = private unnamed_addr constant [67 x i8] c"MAIN MENU:\0A1.Add element to stack\0A2.Delete element from the stack\0A\00", align 16
@.str3 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str4 = private unnamed_addr constant [19 x i8] c"Enter the data... \00", align 16
@.str5 = private unnamed_addr constant [42 x i8] c"\0AValue returned from pop function is  %d \00", align 16
@.str6 = private unnamed_addr constant [18 x i8] c"Invalid Choice . \00", align 16
@.str7 = private unnamed_addr constant [81 x i8] c"\0ADo you want to do more operations on Stack ( 1 for yes, any other key to exit) \00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define void @push(i32 %y) nounwind {
  %1 = alloca i32, align 4
  store i32 %y, i32* %1, align 4
  %2 = load i32, i32* @top, align 4
  %3 = icmp sgt i32 %2, 200
  br i1 %3, label %4, label %7

; <label>:4                                            		; preds = %0
  %5 = getelementptr inbounds [11 x i8], [11 x i8]* @.str, i32 0, i32 0
  %6 = call i32 (i8*, ...)* @printf(i8* %5)
  br label %13

; <label>:7                                                    		; preds = %0
  %8 = load i32, i32* @top, align 4
  %9 = add i32 %8, 1
  store i32 %9, i32* @top, align 4
  %10 = load i32, i32* @top, align 4
  %11 = getelementptr inbounds [200 x i32], [200 x i32]* @stack, i32 0, i32 %10
  %12 = load i32, i32* %1, align 4
  store i32 %12, i32* %11, align 4
  br label %13

; <label>:13		; preds = %4, %7
  ret void
}

define i32 @pop() nounwind {
  %1 = alloca i32, align 4
  %a = alloca i32, align 4
  %2 = load i32, i32* @top, align 4
  %3 = icmp sle i32 %2, 0
  br i1 %3, label %4, label %7

; <label>:4                                             		; preds = %0
  %5 = getelementptr inbounds [12 x i8], [12 x i8]* @.str1, i32 0, i32 0
  %6 = call i32 (i8*, ...)* @printf(i8* %5)
  store i32 0, i32* %1, align 4
  br label %15

; <label>:7         		; preds = %0
  %8 = load i32, i32* @top, align 4
  %9 = getelementptr inbounds [200 x i32], [200 x i32]* @stack, i32 0, i32 %8
  %10 = load i32, i32* %9, align 4
  store i32 %10, i32* %a, align 4
  %11 = load i32, i32* @top, align 4
  %12 = sub i32 %11, 1
  store i32 %12, i32* @top, align 4
  br label %13

; <label>:13      		; preds = %7
  %14 = load i32, i32* %a, align 4
  store i32 %14, i32* %1, align 4
  br label %15

; <label>:15 		; preds = %4, %13
  %16 = load i32, i32* %1, align 4
  ret i32 %16
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %will = alloca i32, align 4
  %i = alloca i32, align 4
  %num = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 1, i32* %will, align 4
  br label %2

; <label>:2    		; preds = %0, %25
  %3 = load i32, i32* %will, align 4
  %4 = icmp eq i32 %3, 1
  br i1 %4, label %5, label %30

; <label>:5                                                 		; preds = %2
  %6 = getelementptr inbounds [67 x i8], [67 x i8]* @.str2, i32 0, i32 0
  %7 = call i32 (i8*, ...)* @printf(i8* %6)
  %8 = getelementptr inbounds [3 x i8], [3 x i8]* @.str3, i32 0, i32 0
  %9 = call i32 (i8*, ...)* @scanf(i8* %8, i32* %will)
  %10 = load i32, i32* %will, align 4
  switch i32 %10, label %22 [
    i32 1, label %11
    i32 2, label %17 
  ]

; <label>:11                                             		; preds = %5
  %12 = getelementptr inbounds [19 x i8], [19 x i8]* @.str4, i32 0, i32 0
  %13 = call i32 (i8*, ...)* @printf(i8* %12)
  %14 = getelementptr inbounds [3 x i8], [3 x i8]* @.str3, i32 0, i32 0
  %15 = call i32 (i8*, ...)* @scanf(i8* %14, i32* %num)
  %16 = load i32, i32* %num, align 4
  call void @push(i32 %16)
  br label %25

; <label>:17                          		; preds = %5
  %18 = call i32 @pop()
  store i32 %18, i32* %i, align 4
  %19 = getelementptr inbounds [42 x i8], [42 x i8]* @.str5, i32 0, i32 0
  %20 = load i32, i32* %i, align 4
  %21 = call i32 (i8*, ...)* @printf(i8* %19, i32 %20)
  br label %25

; <label>:22                                             		; preds = %5
  %23 = getelementptr inbounds [18 x i8], [18 x i8]* @.str6, i32 0, i32 0
  %24 = call i32 (i8*, ...)* @printf(i8* %23)
  br label %25

; <label>:25                                  		; preds = %11, %17, %22
  %26 = getelementptr inbounds [81 x i8], [81 x i8]* @.str7, i32 0, i32 0
  %27 = call i32 (i8*, ...)* @printf(i8* %26)
  %28 = getelementptr inbounds [3 x i8], [3 x i8]* @.str3, i32 0, i32 0
  %29 = call i32 (i8*, ...)* @scanf(i8* %28, i32* %will)
  br label %2

; <label>:30      		; preds = %2
  %31 = load i32, i32* %1, align 4
  ret i32 %31
}
