@.str = private unnamed_addr constant [18 x i8] c"value at ptr = %d\00", align 16

declare i32 @printf(i8*, ...) 

declare noalias i8* @malloc(i64) nounwind

define i32 @main(i32 %argc, i8** %argv) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i8**, align 8
  %3 = alloca i32, align 4
  %ptr = alloca i32*, align 8
  store i32 %argc, i32* %1, align 4
  store i8** %argv, i8*** %2, align 8
  store i32 0, i32* %3, align 4
  %4 = call i8* @malloc(i64 4) nounwind
  %5 = bitcast i8* %4 to i32*
  store i32* %5, i32** %ptr, align 8
  %6 = load i32*, i32** %ptr, align 8
  store i32 3, i32* %6, align 4
  %7 = load i32*, i32** %ptr, align 8
  %8 = load i32, i32* %7, align 4
  %9 = getelementptr inbounds [18 x i8], [18 x i8]* @.str, i32 0, i32 0
  %10 = call i32 (i8*, ...)* @printf(i8* %9, i32 %8)
  ret i32 0
}