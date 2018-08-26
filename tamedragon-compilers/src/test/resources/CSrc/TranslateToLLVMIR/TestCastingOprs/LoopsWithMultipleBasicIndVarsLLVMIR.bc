define i32 @foo(double %a, i32 %b, i32 %j) nounwind {
  %1 = alloca double, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %i = alloca i32, align 4
  %dincr = alloca double, align 8
  %dotherincr = alloca double, align 8
  %result = alloca i32, align 4
  %p = alloca i32, align 4
  %q = alloca i32, align 4
  %r = alloca i32, align 4
  %dx = alloca double, align 8
  %dy = alloca double, align 8
  %dz = alloca double, align 8
  %m = alloca i32, align 4
  store double %a, double* %1, align 8
  store i32 %b, i32* %2, align 4
  store i32 %j, i32* %3, align 4
  store double 0.000000e+00, double* %dincr, align 8
  store double 2.300000e+00, double* %dotherincr, align 8
  store i32 34, i32* %result, align 4
  store i32 0, i32* %i, align 4
  br label %4

; <label>:4                                       ; preds = %34, %0
  %5 = load i32* %i, align 4
  %6 = load i32* %2, align 4
  %7 = icmp slt i32 %5, %6
  br i1 %7, label %8, label %37

; <label>:8                                       ; preds = %4
  store i32 20, i32* %p, align 4
  store i32 30, i32* %q, align 4
  store i32 40, i32* %r, align 4
  store double 2.000000e+00, double* %dx, align 8
  store double 4.000000e+00, double* %dy, align 8
  store double 6.000000e+00, double* %dz, align 8
  %9 = load i32* %i, align 4
  %10 = mul i32 %9, 42
  store i32 %10, i32* %p, align 4
  %11 = load i32* %3, align 4
  %12 = load i32* %p, align 4
  %13 = sub i32 %11, %12
  store i32 %13, i32* %q, align 4
  %14 = load i32* %p, align 4
  %15 = mul i32 30, %14
  store i32 %15, i32* %r, align 4
  %16 = load double* %dincr, align 8
  %17 = fadd double %16, 1.200000e+01
  store double %17, double* %dincr, align 8
  %18 = load double* %dotherincr, align 8
  %19 = fadd double %18, 4.500000e+00
  store double %19, double* %dotherincr, align 8
  %20 = load double* %dincr, align 8
  %21 = fmul double %20, 6.700000e+00
  store double %21, double* %dx, align 8
  %22 = load double* %dx, align 8
  %23 = load double* %dotherincr, align 8
  %24 = fsub double %22, %23
  store double %24, double* %dy, align 8
  %25 = load double* %dy, align 8
  %26 = fmul double 2.560000e+00, %25
  store double %26, double* %dz, align 8
  %27 = load double* %dz, align 8
  %28 = fptosi double %27 to i32
  store i32 %28, i32* %m, align 4
  %29 = load i32* %r, align 4
  %30 = load i32* %m, align 4
  %31 = add i32 %29, %30
  %32 = load i32* %result, align 4
  %33 = add i32 %32, %31
  store i32 %33, i32* %result, align 4
  br label %34

; <label>:34                                      ; preds = %8
  %35 = load i32* %i, align 4
  %36 = add i32 %35, 3
  store i32 %36, i32* %i, align 4
  br label %4

; <label>:37                                      ; preds = %4
  %38 = load i32* %result, align 4
  ret i32 %38
}
