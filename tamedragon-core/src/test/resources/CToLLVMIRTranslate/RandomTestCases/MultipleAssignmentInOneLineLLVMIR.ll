@.str = private unnamed_addr constant [32 x i8] c"i = %d, j = %d, k = %d, m = %d\0A\00", align 16

declare i32 @printf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  %k = alloca i32, align 4
  %m = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = load i32, i32* %k, align 4
  %3 = add i32 %2, 1
  store i32 %3, i32* %k, align 4
  store i32 %2, i32* %m, align 4
  store i32 %2, i32* %j, align 4
  store i32 %2, i32* %i, align 4
  %4 = getelementptr inbounds [32 x i8], [32 x i8]* @.str, i32 0, i32 0
  %5 = load i32, i32* %i, align 4
  %6 = load i32, i32* %j, align 4
  %7 = load i32, i32* %k, align 4
  %8 = load i32, i32* %m, align 4
  %9 = call i32 (i8*, ...)* @printf(i8* %4, i32 %5, i32 %6, i32 %7, i32 %8)
  %10 = load i32, i32* %1, align 4
  ret i32 %10
}
