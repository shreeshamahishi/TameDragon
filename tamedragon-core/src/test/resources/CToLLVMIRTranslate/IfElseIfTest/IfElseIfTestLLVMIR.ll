define void @foo() nounwind {
  %a = alloca i32, align 4
  store i32 10, i32* %a, align 4
  %1 = load i32, i32* %a, align 4
  %2 = icmp slt i32 %1, 10
  br i1 %2, label %3, label %6

; <label>:3      		; preds = %0
  %4 = load i32, i32* %a, align 4
  %5 = add i32 %4, 1
  store i32 %5, i32* %a, align 4
  br label %16

; <label>:6      		; preds = %0
  %7 = load i32, i32* %a, align 4
  %8 = icmp sgt i32 %7, 10
  br i1 %8, label %9, label %12

; <label>:9       		; preds = %6
  %10 = load i32, i32* %a, align 4
  %11 = sub i32 %10, 1
  store i32 %11, i32* %a, align 4
  br label %15

; <label>:12      		; preds = %6
  %13 = load i32, i32* %a, align 4
  %14 = add i32 %13, 1
  store i32 %14, i32* %a, align 4
  br label %15

; <label>:15		; preds = %9, %12
  br label %16

; <label>:16		; preds = %3, %15
  ret void
}
