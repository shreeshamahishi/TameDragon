define i32 @foo(i32* %arg1, i32 %MAX) nounwind {
  %1 = alloca i32*, align 8
  %2 = alloca i32, align 4
  %ptr = alloca i32*, align 8
  %count = alloca i32, align 4
  %result = alloca i32, align 4
  store i32* %arg1, i32** %1, align 8
  store i32 %MAX, i32* %2, align 4
  store i32 0, i32* %count, align 4
  store i32 0, i32* %result, align 4
  %3 = load i32*, i32** %1, align 8
  store i32* %3, i32** %ptr, align 8
  br label %4

; <label>:4 		; preds = %8, %0
  %5 = load i32, i32* %count, align 4
  %6 = load i32, i32* %2, align 4
  %7 = icmp slt i32 %5, %6
  br i1 %7, label %8, label %17

; <label>:8          		; preds = %4
  %9 = load i32*, i32** %ptr, align 8
  %10 = load i32, i32* %9, align 4
  %11 = load i32, i32* %result, align 4
  %12 = add i32 %11, %10
  store i32 %12, i32* %result, align 4
  %13 = load i32*, i32** %ptr, align 8
  %14 = getelementptr inbounds i32, i32* %13, i32 1
  store i32* %14, i32** %ptr, align 8
  %15 = load i32, i32* %count, align 4
  %16 = add i32 %15, 1
  store i32 %16, i32* %count, align 4
  br label %4

; <label>:17      		; preds = %4
  %18 = load i32, i32* %result, align 4
  ret i32 %18
}