@.str = private unnamed_addr constant [26 x i8] c"This is the empty array!\0A\00", align 16
@.str1 = private unnamed_addr constant [17 x i8] c"The array was: \0A\00", align 16
@.str2 = private unnamed_addr constant [23 x i8] c"The sorted array is: \0A\00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @getIntArray(i32*, i32, i32) 

declare void @printIntArray(i32*, i32) 

declare void @selectionSort(i32*, i32) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %x = alloca [10 x i32], align 16
  %hmny = alloca i32, align 4
  %who = alloca i32, align 4
  %where = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [10 x i32], [10 x i32]* %x, i32 0, i32 0
  %3 = call i32 @getIntArray(i32* %2, i32 10, i32 0)
  store i32 %3, i32* %hmny, align 4
  %4 = load i32, i32* %hmny, align 4
  %5 = icmp eq i32 %4, 0
  br i1 %5, label %6, label %9

; <label>:6                                            		; preds = %0
  %7 = getelementptr inbounds [26 x i8], [26 x i8]* @.str, i32 0, i32 0
  %8 = call i32 (i8*, ...)* @printf(i8* %7)
  br label %20

; <label>:9                                              		; preds = %0
  %10 = getelementptr inbounds [17 x i8], [17 x i8]* @.str1, i32 0, i32 0
  %11 = call i32 (i8*, ...)* @printf(i8* %10)
  %12 = getelementptr inbounds [10 x i32], [10 x i32]* %x, i32 0, i32 0
  %13 = load i32, i32* %hmny, align 4
  call void @printIntArray(i32* %12, i32 %13)
  %14 = getelementptr inbounds [10 x i32], [10 x i32]* %x, i32 0, i32 0
  %15 = load i32, i32* %hmny, align 4
  call void @selectionSort(i32* %14, i32 %15)
  %16 = getelementptr inbounds [23 x i8], [23 x i8]* @.str2, i32 0, i32 0
  %17 = call i32 (i8*, ...)* @printf(i8* %16)
  %18 = getelementptr inbounds [10 x i32], [10 x i32]* %x, i32 0, i32 0
  %19 = load i32, i32* %hmny, align 4
  call void @printIntArray(i32* %18, i32 %19)
  br label %20

; <label>:20  		; preds = %6, %9
  %21 = load i32, i32* %1, align 4
  ret i32 %21
}