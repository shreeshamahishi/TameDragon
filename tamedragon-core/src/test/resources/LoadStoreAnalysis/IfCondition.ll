define i32 @foo(i32 %m) nounwind {
  %1 = alloca i32, align 4
  %result = alloca i32, align 4
  %a = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  store i32 100, i32* %result, align 4
  store i32 40, i32* %a, align 4
  %2 = load i32* %1, align 4
  %3 = load i32* %a, align 4
  %4 = icmp slt i32 %2, %3
  br i1 %4, label %5, label %6


; <label>:5      		; preds = %0
  store i32 200, i32* %a, align 4
  br label %6


; <label>:6   		; preds = %0, %5
  %7 = load i32* %result, align 4
  %8 = load i32* %a, align 4
  %9 = add i32 %7, %8
  store i32 %9, i32* %result, align 4
  %10 = load i32* %result, align 4
  ret i32 %10
}

