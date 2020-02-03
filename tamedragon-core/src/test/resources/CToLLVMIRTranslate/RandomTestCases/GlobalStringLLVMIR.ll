@.str = private unnamed_addr constant [6 x i8] c"Hello\00", align 1
@str = global i8* getelementptr inbounds [6 x i8], [6 x i8]* @.str, i32 0, i32 0, align 8
@.str1 = private unnamed_addr constant [3 x i8] c"%s\00", align 1

declare i32 @printf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [3 x i8], [3 x i8]* @.str1, i32 0, i32 0
  %3 = load i8*, i8** @str, align 8
  %4 = call i32 (i8*, ...)* @printf(i8* %2, i8* %3)
  %5 = load i32, i32* %1, align 4
  ret i32 %5
}
