%enum.DAY = type i32

@.str = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

declare i32 @printf(i8*, ...) 

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %today = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 2, i32* %today, align 4
  %2 = getelementptr inbounds [4 x i8], [4 x i8]* @.str, i32 0, i32 0
  %3 = load i32, i32* %today, align 4
  %4 = call i32 (i8*, ...)* @printf(i8* %2, i32 %3)
  ret i32 0
}
