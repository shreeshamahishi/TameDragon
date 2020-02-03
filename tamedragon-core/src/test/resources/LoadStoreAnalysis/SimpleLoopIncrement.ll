define i32 @foo(i32 %MAX) nounwind {
  %1 = alloca i32, align 4
  %a = alloca i32, align 4
  %i = alloca i32, align 4
  store i32 %MAX, i32* %1, align 4
  store i32 23, i32* %a, align 4
  store i32 0, i32* %i, align 4
  br label %2


; <label>:2		; preds = %0, %6
  %3 = load i32* %i, align 4
  %4 = load i32* %1, align 4
  %5 = icmp slt i32 %3, %4
  br i1 %5, label %6, label %11


; <label>:6      		; preds = %2
  %7 = load i32* %a, align 4
  %8 = add i32 %7, 1
  store i32 %8, i32* %a, align 4
  %9 = load i32* %i, align 4
  %10 = add i32 %9, 1
  store i32 %10, i32* %i, align 4
  br label %2


; <label>:11 		; preds = %2
  %12 = load i32* %a, align 4
  ret i32 %12
}

