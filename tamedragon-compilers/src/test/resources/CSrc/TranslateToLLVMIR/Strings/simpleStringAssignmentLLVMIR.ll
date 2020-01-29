@a = global i32 10, align 4
@.str = private unnamed_addr constant [14 x i8] c"Vikash Joshi\0A\00", align 1

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %string = alloca i8*, align 8
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [14 x i8], [14 x i8]* @.str, i32 0, i32 0
  store i8* %2, i8** %string, align 8
  ret i32 0
}
