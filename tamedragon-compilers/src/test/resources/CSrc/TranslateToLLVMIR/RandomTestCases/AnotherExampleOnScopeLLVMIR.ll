@x = global i32 2, align 4
@y = global i32 3, align 4
@z = global i32 4, align 4
@.str = private unnamed_addr constant [25 x i8] c"x = %d \0A y = %d \0A z = %d\00", align 16

declare i32 @printf(i8*, ...) 

define void @moo(i32 %x, i32* %y) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32*, align 8
  %z = alloca i32, align 4
  store i32 %x, i32* %1, align 4
  store i32* %y, i32** %2, align 8
  %3 = load i32, i32* %1, align 4
  %4 = add i32 %3, 3
  store i32 %4, i32* %1, align 4
  %5 = load i32*, i32** %2, align 8
  %6 = load i32, i32* %5, align 4
  %7 = add i32 %6, 3
  %8 = load i32*, i32** %2, align 8
  store i32 %7, i32* %8, align 4
  %9 = load i32, i32* %z, align 4
  %10 = add i32 %9, 3
  store i32 %10, i32* %z, align 4
  %11 = getelementptr inbounds [25 x i8], [25 x i8]* @.str, i32 0, i32 0
  %12 = load i32, i32* %1, align 4
  %13 = load i32*, i32** %2, align 8
  %14 = load i32, i32* %z, align 4
  %15 = call i32 (i8*, ...)* @printf(i8* %11, i32 %12, i32* %13, i32 %14)
  ret void
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = load i32, i32* @x, align 4
  call void @moo(i32 %2, i32* @y)
  %3 = load i32, i32* %1, align 4
  ret i32 %3
}
