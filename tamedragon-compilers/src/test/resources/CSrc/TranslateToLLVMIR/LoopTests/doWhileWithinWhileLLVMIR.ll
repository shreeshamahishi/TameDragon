define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %i = alloca i32, align 4
  %k = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 0, i32* %i, align 4
  store i32 0, i32* %k, align 4
  br label %3

; <label>:3 		; preds = %0, %18
  %4 = load i32, i32* %i, align 4
  %5 = icmp slt i32 %4, 10
  br i1 %5, label %6, label %21

; <label>:6      		; preds = %3
  %7 = load i32, i32* %i, align 4
  %8 = icmp slt i32 %7, 2
  br i1 %8, label %9, label %10

; <label>:9		; preds = %6
  br label %21

; <label>:10		; preds = %6
  br label %11

; <label>:11		; preds = %10, %14
  %12 = load i32, i32* %k, align 4
  %13 = add i32 %12, 1
  store i32 %13, i32* %k, align 4
  br label %14

; <label>:14     		; preds = %11
  %15 = load i32, i32* %k, align 4
  %16 = icmp slt i32 %15, 10
  br i1 %16, label %11, label %17

; <label>:17		; preds = %14
  br label %18

; <label>:18     		; preds = %17
  %19 = load i32, i32* %i, align 4
  %20 = add i32 %19, 1
  store i32 %20, i32* %i, align 4
  br label %3

; <label>:21  		; preds = %3, %9
  %22 = load i32, i32* %k, align 4
  ret i32 %22
}
