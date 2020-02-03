define double @foo(i32 %a, double %passed_dbl) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca double, align 8
  %b = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca double, align 8
  store i32 %a, i32* %1, align 4
  store double %passed_dbl, double* %2, align 8
  store i32 12, i32* %b, align 4
  store i32 23, i32* %c, align 4
  %3 = load i32, i32* %c, align 4
  %4 = load i32, i32* %1, align 4
  %5 = add i32 %3, %4
  store i32 %5, i32* %c, align 4
  br label %6

; <label>:6 		; preds = %0, %27
  %7 = load i32, i32* %b, align 4
  %8 = load i32, i32* %1, align 4
  %9 = icmp slt i32 %7, %8
  br i1 %9, label %10, label %30

; <label>:10            		; preds = %6
  %11 = load double, double* %d, align 8
  %12 = load double, double* %2, align 8
  %13 = fadd double %11, %12
  store double %13, double* %d, align 8
  %14 = load i32, i32* %c, align 4
  %15 = load i32, i32* %b, align 4
  %16 = icmp slt i32 %14, %15
  br i1 %16, label %17, label %23

; <label>:17           		; preds = %10
  %18 = load double, double* %d, align 8
  %19 = load double, double* %2, align 8
  %20 = fsub double %18, %19
  store double %20, double* %d, align 8
  %21 = load i32, i32* %c, align 4
  %22 = add i32 %21, 1
  store i32 %22, i32* %c, align 4
  br label %27

; <label>:23           		; preds = %10
  %24 = load double, double* %d, align 8
  %25 = load double, double* %2, align 8
  %26 = fadd double %24, %25
  store double %26, double* %d, align 8
  br label %27

; <label>:27		; preds = %17, %23
  %28 = load i32, i32* %b, align 4
  %29 = sub i32 %28, 1
  store i32 %29, i32* %b, align 4
  br label %6

; <label>:30            		; preds = %6
  %31 = load double, double* %d, align 8
  %32 = load i32, i32* %c, align 4
  %33 = sitofp i32 %32 to double
  %34 = fadd double %31, %33
  store double %34, double* %d, align 8
  %35 = load double, double* %d, align 8
  ret double %35
}
