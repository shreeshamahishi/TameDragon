define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %i = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 0, i32* %i, align 4
  br label %3

; <label>:3 		; preds = %0, %13
  %4 = load i32, i32* %i, align 4
  %5 = icmp slt i32 %4, 10
  br i1 %5, label %6, label %14

; <label>:6      		; preds = %3
  %7 = load i32, i32* %i, align 4
  %8 = icmp eq i32 %7, 2
  br i1 %8, label %9, label %12

; <label>:9       		; preds = %6
  %10 = load i32, i32* %i, align 4
  %11 = add i32 %10, 1
  store i32 %11, i32* %i, align 4
  br label %13

; <label>:12		; preds = %6
  br label %14

; <label>:13		; preds = %9
  br label %3

; <label>:14 		; preds = %3, %12
  %15 = load i32, i32* %i, align 4
  ret i32 %15
}
