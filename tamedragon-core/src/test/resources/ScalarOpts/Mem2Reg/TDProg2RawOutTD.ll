@q = external global i32*

define i32 @Prog2() nounwind {
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  %c = alloca [100 x i32], align 16
  store i32* %a, i32** @q, align 8
  store i32 2, i32* %a, align 4
  %1 = load i32*, i32** @q, align 8
  %2 = load i32, i32* %1, align 4
  %3 = add i32 %2, 2
  store i32 %3, i32* %b, align 4
  store i32* %b, i32** @q, align 8
  br label %4

; <label>:4        		; preds = %0, %13
  %i.0 = phi i32 [ 0, %0 ], [ %14, %13 ]
  %5 = icmp slt i32 %i.0, 100
  br i1 %5, label %6, label %15

; <label>:6                                                 		; preds = %4
  %7 = getelementptr inbounds [100 x i32], [100 x i32]* %c, i32 0, i32 %i.0
  %8 = load i32, i32* %a, align 4
  %9 = load i32, i32* %7, align 4
  %10 = add i32 %9, %8
  %11 = getelementptr inbounds [100 x i32], [100 x i32]* %c, i32 0, i32 %i.0
  store i32 %10, i32* %11, align 4
  %12 = load i32*, i32** @q, align 8
  store i32 %i.0, i32* %12, align 4
  br label %13

; <label>:13		; preds = %6
  %14 = add i32 %i.0, 1
  br label %4

; <label>:15        		; preds = %4
  %16 = load i32*, i32** @q, align 8
  %17 = load i32, i32* %a, align 4
  %18 = load i32, i32* %16, align 4
  %19 = add i32 %18, %17
  ret i32 %19
}