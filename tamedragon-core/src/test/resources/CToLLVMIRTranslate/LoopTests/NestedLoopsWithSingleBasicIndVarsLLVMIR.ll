define i32 @foo(double %a, i32 %b, i32 %j) nounwind {
  %1 = alloca double, align 8
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %master = alloca i32, align 4
  %i = alloca i32, align 4
  %dincr = alloca double, align 8
  %dotherincr = alloca double, align 8
  %result = alloca i32, align 4
  %p = alloca i32, align 4
  %var = alloca i32, align 4
  %dx = alloca double, align 8
  %dy = alloca double, align 8
  %dz = alloca double, align 8
  %inner_index1 = alloca i32, align 4
  %inner_index2 = alloca i32, align 4
  %ir = alloca i32, align 4
  %var1 = alloca i32, align 4
  %var2 = alloca i32, align 4
  %check = alloca i32, align 4
  %innermost_index = alloca i32, align 4
  store double %a, double* %1, align 8
  store i32 %b, i32* %2, align 4
  store i32 %j, i32* %3, align 4
  store double 0.000000e+00, double* %dincr, align 8
  store double 2.300000e+00, double* %dotherincr, align 8
  store i32 34, i32* %result, align 4
  store i32 0, i32* %i, align 4
  br label %4

; <label>:4 		; preds = %0, %51
  %5 = load i32, i32* %i, align 4
  %6 = load i32, i32* %2, align 4
  %7 = icmp slt i32 %5, %6
  br i1 %7, label %8, label %54

; <label>:8               		; preds = %4
  store i32 20, i32* %p, align 4
  store double 2.000000e+00, double* %dx, align 8
  store double 4.000000e+00, double* %dy, align 8
  store double 6.000000e+00, double* %dz, align 8
  store i32 0, i32* %inner_index1, align 4
  store i32 3, i32* %inner_index2, align 4
  store i32 0, i32* %ir, align 4
  br label %9

; <label>:9             		; preds = %8, %42
  %10 = load i32, i32* %inner_index1, align 4
  %11 = load i32, i32* %p, align 4
  %12 = icmp slt i32 %10, %11
  br i1 %12, label %13, label %45

; <label>:13                 		; preds = %9
  %14 = load i32, i32* %inner_index1, align 4
  %15 = mul i32 %14, 2
  store i32 %15, i32* %var1, align 4
  %16 = load i32, i32* %inner_index2, align 4
  %17 = mul i32 %16, 4
  store i32 %17, i32* %var2, align 4
  %18 = load i32, i32* %var1, align 4
  %19 = load i32, i32* %var2, align 4
  %20 = add i32 %18, %19
  %21 = load i32, i32* %master, align 4
  %22 = add i32 %21, %20
  store i32 %22, i32* %master, align 4
  %23 = load i32, i32* %inner_index1, align 4
  %24 = add i32 %23, 2
  store i32 %24, i32* %inner_index1, align 4
  %25 = load i32, i32* %inner_index2, align 4
  %26 = add i32 %25, 5
  store i32 0, i32* %check, align 4
  store i32 3, i32* %innermost_index, align 4
  store i32 1, i32* %check, align 4
  br label %27

; <label>:27              		; preds = %13, %39
  %28 = load i32, i32* %check, align 4
  %29 = load i32, i32* %innermost_index, align 4
  %30 = icmp slt i32 %28, %29
  br i1 %30, label %31, label %42

; <label>:31                   		; preds = %27
  %32 = load i32, i32* %innermost_index, align 4
  %33 = sitofp i32 %32 to double
  %34 = fmul double 8.970000e+01, %33
  store double %34, double* %dx, align 8
  %35 = load double, double* %dx, align 8
  %36 = fsub double %35, 8.000000e+00
  store double %36, double* %dy, align 8
  %37 = load double, double* %dy, align 8
  %38 = fmul double 3.000000e+00, %37
  store double %38, double* %dz, align 8
  br label %39

; <label>:39                   		; preds = %31
  %40 = load i32, i32* %innermost_index, align 4
  %41 = add i32 %40, 4
  store i32 %41, i32* %innermost_index, align 4
  br label %27

; <label>:42            		; preds = %27
  %43 = load double, double* %dz, align 8
  %44 = fptosi double %43 to i32
  store i32 %44, i32* %ir, align 4
  br label %9

; <label>:45          		; preds = %9
  %46 = load i32, i32* %ir, align 4
  %47 = load i32, i32* %3, align 4
  %48 = add i32 %46, %47
  %49 = load i32, i32* %result, align 4
  %50 = add i32 %49, %48
  store i32 %50, i32* %result, align 4
  br label %51

; <label>:51     		; preds = %45
  %52 = load i32, i32* %i, align 4
  %53 = add i32 %52, 3
  store i32 %53, i32* %i, align 4
  br label %4

; <label>:54           		; preds = %4
  %55 = load i32, i32* %result, align 4
  ret i32 %55
}
