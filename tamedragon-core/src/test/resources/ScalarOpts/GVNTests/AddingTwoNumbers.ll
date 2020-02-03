@.str = private unnamed_addr constant [22 x i8] c"Enter two integers > \00", align 16
@.str1 = private unnamed_addr constant [6 x i8] c"%d %d\00", align 1
@.str2 = private unnamed_addr constant [29 x i8] c"The two numbers are: %d  %d\0A\00", align 16
@.str3 = private unnamed_addr constant [17 x i8] c"Their sum is %d\0A\00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define i32 @main() nounwind {
  %first = alloca i32, align 4
  %second = alloca i32, align 4
  %1 = getelementptr inbounds [22 x i8], [22 x i8]* @.str, i32 0, i32 0
  %2 = call i32 (i8*, ...)* @printf(i8* %1)
  %3 = getelementptr inbounds [6 x i8], [6 x i8]* @.str1, i32 0, i32 0
  %4 = call i32 (i8*, ...)* @scanf(i8* %3, i32* %first, i32* %second)
  %5 = getelementptr inbounds [29 x i8], [29 x i8]* @.str2, i32 0, i32 0
  %6 = load i32, i32* %first, align 4
  %7 = load i32, i32* %second, align 4
  %8 = call i32 (i8*, ...)* @printf(i8* %5, i32 %6, i32 %7)
  %9 = load i32, i32* %first, align 4
  %10 = load i32, i32* %second, align 4
  %11 = add i32 %9, %10
  %12 = getelementptr inbounds [17 x i8], [17 x i8]* @.str3, i32 0, i32 0
  %13 = call i32 (i8*, ...)* @printf(i8* %12, i32 %11)
  ret i32 0
}