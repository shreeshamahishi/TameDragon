define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %n = alloca i32, align 4
  %i = alloca i32, align 4
  %flag = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 5, i32* %n, align 4
  store i32 1, i32* %flag, align 4
  store i32 2, i32* %i, align 4
  br label %2

; <label>:2 		; preds = %0, %21
  %3 = load i32, i32* %i, align 4
  %4 = load i32, i32* %n, align 4
  %5 = sdiv i32 %4, 2
  %6 = icmp slt i32 %3, %5
  br i1 %6, label %7, label %10

; <label>:7         		; preds = %2
  %8 = load i32, i32* %flag, align 4
  %9 = icmp ne i32 %8, 0
  br label %10

; <label>:10        		; preds = %2, %7
  %11 = phi i1 [ false, %2 ], [ %9, %7 ]
  br i1 %11, label %12, label %22

; <label>:12     		; preds = %10
  %13 = load i32, i32* %n, align 4
  %14 = load i32, i32* %i, align 4
  %15 = srem i32 %13, %14
  %16 = icmp eq i32 %15, 0
  br i1 %16, label %17, label %18

; <label>:17     		; preds = %12
  store i32 0, i32* %flag, align 4
  br label %21

; <label>:18     		; preds = %12
  %19 = load i32, i32* %i, align 4
  %20 = add i32 %19, 1
  store i32 %20, i32* %i, align 4
  br label %21

; <label>:21		; preds = %17, %18
  br label %2

; <label>:22		; preds = %10
  ret i32 0
}
