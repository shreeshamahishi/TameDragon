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
  %5 = load i32, i32* %2, align 4
  %6 = load i32, i32* %1, align 4
  %7 = icmp slt i32 %5, %6
  br i1 %7, label %8, label %11

; <label>:8      		; preds = %0
  %9 = load i32, i32* %2, align 4
  %10 = sub i32 %9, 1
  store i32 %10, i32* %c, align 4
  br label %11

; <label>:11  		; preds = %0, %8
  %12 = load i32, i32* %2, align 4
  ret i32 %12
}
