@.str = private unnamed_addr constant [16 x i8] c"Enter a string \00", align 16
@.str1 = private unnamed_addr constant [16 x i8] c"You Entered %s\0A\00", align 16
@.str2 = private unnamed_addr constant [4 x i8] c"%c\0A\00", align 1

declare i8* @gets(i8*) 

declare i32 @printf(i8*, ...) 

declare i32 @strlen(i8*) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %chArr = alloca [200 x i8], align 16
  %len = alloca i32, align 4
  %i = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [16 x i8], [16 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [200 x i8], [200 x i8]* %chArr, i32 0, i32 0
  %5 = call i8* @gets(i8* %4)
  %6 = getelementptr inbounds [16 x i8], [16 x i8]* @.str1, i32 0, i32 0
  %7 = getelementptr inbounds [200 x i8], [200 x i8]* %chArr, i32 0, i32 0
  %8 = call i32 (i8*, ...)* @printf(i8* %6, i8* %7)
  %9 = getelementptr inbounds [200 x i8], [200 x i8]* %chArr, i32 0, i32 0
  %10 = call i32 @strlen(i8* %9)
  store i32 %10, i32* %len, align 4
  store i32 0, i32* %i, align 4
  br label %11

; <label>:11   		; preds = %0, %21
  %12 = load i32, i32* %i, align 4
  %13 = load i32, i32* %len, align 4
  %14 = icmp slt i32 %12, %13
  br i1 %14, label %15, label %24

; <label>:15                        		; preds = %11
  %16 = load i32, i32* %i, align 4
  %17 = getelementptr inbounds [200 x i8], [200 x i8]* %chArr, i32 0, i32 %16
  %18 = getelementptr inbounds [4 x i8], [4 x i8]* @.str2, i32 0, i32 0
  %19 = load i8, i8* %17, align 1
  %20 = call i32 (i8*, ...)* @printf(i8* %18, i8 %19)
  br label %21

; <label>:21     		; preds = %15
  %22 = load i32, i32* %i, align 4
  %23 = add i32 %22, 1
  store i32 %23, i32* %i, align 4
  br label %11

; <label>:24		; preds = %11
  ret i32 0
}
