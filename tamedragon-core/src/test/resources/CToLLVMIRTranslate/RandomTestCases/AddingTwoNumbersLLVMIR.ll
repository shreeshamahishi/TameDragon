@.str = private unnamed_addr constant [22 x i8] c"Enter two integers > \00", align 16
@.str1 = private unnamed_addr constant [6 x i8] c"%d %d\00", align 1
@.str2 = private unnamed_addr constant [29 x i8] c"The two numbers are: %d  %d\0A\00", align 16
@.str3 = private unnamed_addr constant [17 x i8] c"Their sum is %d\0A\00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %first = alloca i32, align 4
  %second = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [22 x i8], [22 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [6 x i8], [6 x i8]* @.str1, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, i32* %first, i32* %second)
  %6 = getelementptr inbounds [29 x i8], [29 x i8]* @.str2, i32 0, i32 0
  %7 = load i32, i32* %first, align 4
  %8 = load i32, i32* %second, align 4
  %9 = call i32 (i8*, ...)* @printf(i8* %6, i32 %7, i32 %8)
  %10 = getelementptr inbounds [17 x i8], [17 x i8]* @.str3, i32 0, i32 0
  %11 = load i32, i32* %first, align 4
  %12 = load i32, i32* %second, align 4
  %13 = add i32 %11, %12
  %14 = call i32 (i8*, ...)* @printf(i8* %10, i32 %13)
  %15 = load i32, i32* %1, align 4
  ret i32 %15
}
