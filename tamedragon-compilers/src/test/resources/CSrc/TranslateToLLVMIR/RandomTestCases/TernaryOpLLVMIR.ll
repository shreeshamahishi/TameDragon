@.str = private unnamed_addr constant [28 x i8] c"Enter the value for a and b\00", align 16
@.str1 = private unnamed_addr constant [6 x i8] c"%d,%d\00", align 1
@.str2 = private unnamed_addr constant [5 x i8] c"\0A%d\0A\00", align 1

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define i32 @foo(i32 %a) nounwind {
  %1 = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  %2 = load i32, i32* %1, align 4
  ret i32 %2
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  %c = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [28 x i8], [28 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [6 x i8], [6 x i8]* @.str1, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %a, i32* %b)
  %6 = load i32, i32* %a, align 4
  %7 = call i32 @foo(i32 %6)
  %8 = icmp ne i32 %7, 0
  br i1 %8, label %9, label %11

; <label>:9       		; preds = %0
  %10 = load i32, i32* %a, align 4
  br label %13

; <label>:11      		; preds = %0
  %12 = load i32, i32* %b, align 4
  br label %13

; <label>:13                     		; preds = %9, %11
  %14 = phi i32 [ %10, %9 ], [ %12, %11 ]
  store i32 %14, i32* %c, align 4
  %15 = getelementptr inbounds [5 x i8], [5 x i8]* @.str2, i32 0, i32 0
  %16 = load i32, i32* %c, align 4
  %17 = call i32 (i8*, ...)* @printf(i8* %15, i32 %16)
  %18 = load i32, i32* %c, align 4
  ret i32 %18
}
