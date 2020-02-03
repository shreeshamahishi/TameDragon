define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca double, align 8
  %passed_dbl = alloca double, align 8
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 23, i32* %c, align 4
  store double 1.000000e+01, double* %d, align 8
  store double 2.000000e+01, double* %passed_dbl, align 8
  %3 = load i32, i32* %c, align 4
  %4 = add i32 2, %3
  store i32 %4, i32* %c, align 4
  br label %5

; <label>:5 		; preds = %0, %26
  %6 = load i32, i32* %2, align 4
  %7 = load i32, i32* %1, align 4
  %8 = icmp slt i32 %6, %7
  br i1 %8, label %9, label %29

; <label>:9                      		; preds = %5
  %10 = load double, double* %d, align 8
  %11 = load double, double* %passed_dbl, align 8
  %12 = fadd double %10, %11
  store double %12, double* %d, align 8
  %13 = load i32, i32* %c, align 4
  %14 = load i32, i32* %2, align 4
  %15 = icmp slt i32 %13, %14
  br i1 %15, label %16, label %22

; <label>:16                     		; preds = %9
  %17 = load double, double* %d, align 8
  %18 = load double, double* %passed_dbl, align 8
  %19 = fsub double %17, %18
  store double %19, double* %d, align 8
  %20 = load i32, i32* %c, align 4
  %21 = add i32 %20, 1
  store i32 %21, i32* %c, align 4
  br label %26

; <label>:22                     		; preds = %9
  %23 = load double, double* %d, align 8
  %24 = load double, double* %passed_dbl, align 8
  %25 = fadd double %23, %24
  store double %25, double* %d, align 8
  br label %26

; <label>:26		; preds = %16, %22
  %27 = load i32, i32* %2, align 4
  %28 = sub i32 %27, 1
  store i32 %28, i32* %c, align 4
  br label %5

; <label>:29            		; preds = %5
  %30 = load double, double* %d, align 8
  %31 = fadd double %30, 3.400000e+00
  store double %31, double* %d, align 8
  %32 = load i32, i32* %c, align 4
  ret i32 %32
}
