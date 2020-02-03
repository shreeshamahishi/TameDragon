define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca double, align 8
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 23, i32* %c, align 4
  %3 = load i32, i32* %c, align 4
  %4 = add i32 %3, 2
  store i32 %4, i32* %c, align 4
  br label %5

; <label>:5 		; preds = %0, %16
  %6 = load i32, i32* %2, align 4
  %7 = load i32, i32* %1, align 4
  %8 = icmp slt i32 %6, %7
  br i1 %8, label %9, label %19

; <label>:9       		; preds = %5
  %10 = load i32, i32* %c, align 4
  %11 = load i32, i32* %2, align 4
  %12 = icmp slt i32 %10, %11
  br i1 %12, label %13, label %16

; <label>:13      		; preds = %9
  %14 = load i32, i32* %c, align 4
  %15 = add i32 %14, 1
  store i32 %15, i32* %c, align 4
  br label %16

; <label>:16       		; preds = %9, %13
  %17 = load double, double* %d, align 8
  %18 = fsub double %17, 1.000000e+00
  store double %18, double* %d, align 8
  br label %5

; <label>:19      		; preds = %5
  %20 = load i32, i32* %1, align 4
  ret i32 %20
}
