define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = load i32, i32* %1, align 4
  %4 = icmp slt i32 %3, 2
  br i1 %4, label %5, label %9

; <label>:5      		; preds = %0
  %6 = load i32, i32* %1, align 4
  %7 = load i32, i32* %1, align 4
  %8 = add i32 %6, %7
  store i32 %8, i32* %1, align 4
  br label %13

; <label>:9       		; preds = %0
  %10 = load i32, i32* %2, align 4
  %11 = load i32, i32* %2, align 4
  %12 = add i32 %10, %11
  store i32 %12, i32* %2, align 4
  br label %13

; <label>:13  		; preds = %5, %9
  %14 = load i32, i32* %2, align 4
  ret i32 %14
}
