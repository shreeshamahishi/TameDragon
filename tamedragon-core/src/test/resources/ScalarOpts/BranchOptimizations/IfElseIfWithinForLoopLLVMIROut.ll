define void @foo() nounwind {
  %a = alloca i32, align 4
  store i32 10, i32* %a, align 4
  store i32 0, i32* %a, align 4
  br label %1

; <label>:1		; preds = %19, %0
  %2 = load i32, i32* %a, align 4
  %3 = icmp slt i32 %2, 10
  br i1 %3, label %4, label %22

; <label>:4    		; preds = %1
  %5 = load i32, i32* %a, align 4
  %6 = icmp slt i32 %5, 10
  br i1 %6, label %7, label %10

; <label>:7     		; preds = %4
  %8 = load i32, i32* %a, align 4
  %9 = add i32 %8, 1
  store i32 %9, i32* %a, align 4
  br label %19

; <label>:10     		; preds = %4
  %11 = load i32, i32* %a, align 4
  %12 = icmp sgt i32 %11, 10
  br i1 %12, label %13, label %16

; <label>:13    		; preds = %10
  %14 = load i32, i32* %a, align 4
  %15 = sub i32 %14, 1
  store i32 %15, i32* %a, align 4
  br label %19

; <label>:16    		; preds = %10
  %17 = load i32, i32* %a, align 4
  %18 = add i32 %17, 1
  store i32 %18, i32* %a, align 4
  br label %19

; <label>:19		; preds = %16, %13, %7
  %20 = load i32, i32* %a, align 4
  %21 = add i32 %20, 1
  store i32 %21, i32* %a, align 4
  br label %1

; <label>:22		; preds = %1
  ret void
}