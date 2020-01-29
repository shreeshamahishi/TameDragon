define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca double, align 8
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 23, i32* %c, align 4
  %3 = load i32, i32* %c, align 4
  %4 = add i32 2, %3
  store i32 %4, i32* %c, align 4
  br label %5

; <label>:5 		; preds = %0, %15
  %6 = load i32, i32* %c, align 4
  %7 = load i32, i32* %2, align 4
  %8 = icmp slt i32 %6, %7
  br i1 %8, label %9, label %12

; <label>:9       		; preds = %5
  %10 = load i32, i32* %c, align 4
  %11 = add i32 %10, 1
  store i32 %11, i32* %c, align 4
  br label %12

; <label>:12  		; preds = %5, %9
  %13 = load i32, i32* %2, align 4
  %14 = sub i32 %13, 1
  store i32 %14, i32* %c, align 4
  br label %15

; <label>:15     		; preds = %12
  %16 = load i32, i32* %2, align 4
  %17 = load i32, i32* %1, align 4
  %18 = icmp slt i32 %16, %17
  br i1 %18, label %5, label %19

; <label>:19     		; preds = %15
  %20 = load i32, i32* %2, align 4
  ret i32 %20
}
