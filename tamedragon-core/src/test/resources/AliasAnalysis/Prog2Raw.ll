@q = external global i32*

define i32 @Prog2() nounwind {
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  %c = alloca [100 x i32], align 16
  %d = alloca i32, align 4
  %i = alloca i32, align 4
  store i32* %a, i32** @q, align 8
  store i32 2, i32* %a, align 4
  %1 = load i32*, i32** @q, align 8
  %2 = load i32, i32* %1, align 4
  %3 = add i32 %2, 2
  store i32 %3, i32* %b, align 4
  store i32* %b, i32** @q, align 8
  store i32 0, i32* %i, align 4
  br label %4

; <label>:4 		; preds = %0, %17
  %5 = load i32, i32* %i, align 4
  %6 = icmp slt i32 %5, 100
  br i1 %6, label %7, label %20

; <label>:7       		; preds = %4
  %8 = load i32, i32* %i, align 4
  %9 = getelementptr inbounds [100 x i32], [100 x i32]* %c, i32 0, i32 %8
  %10 = load i32, i32* %a, align 4
  %11 = load i32, i32* %9, align 4
  %12 = add i32 %11, %10
  %13 = load i32, i32* %i, align 4
  %14 = getelementptr inbounds [100 x i32], [100 x i32]* %c, i32 0, i32 %13
  store i32 %12, i32* %14, align 4
  %15 = load i32*, i32** @q, align 8
  %16 = load i32, i32* %i, align 4
  store i32 %16, i32* %15, align 4
  br label %17

; <label>:17      		; preds = %7
  %18 = load i32, i32* %i, align 4
  %19 = add i32 %18, 1
  store i32 %19, i32* %i, align 4
  br label %4

; <label>:20        		; preds = %4
  %21 = load i32*, i32** @q, align 8
  %22 = load i32, i32* %a, align 4
  %23 = load i32, i32* %21, align 4
  %24 = add i32 %23, %22
  store i32 %24, i32* %d, align 4
  %25 = load i32, i32* %d, align 4
  ret i32 %25
}
