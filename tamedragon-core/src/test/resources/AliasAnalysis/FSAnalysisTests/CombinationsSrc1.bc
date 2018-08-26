;target datalayout="e-p:32:32:32-i1:8:8-i8:8:8-i16:16:16-i32:32:32-i64:64:64-f32:32:32-f64:64:64-f80:128:128-v64:64:64-v128:128:128-a0:0:64-f80:32:32-n8:16:32"

@glb_var1 = common global i32 0, align 4
@glb_var2 = common global i32 0, align 4
@glb_var3 = common global double 0.0, align 8
@glb_var4 = common global double 0.0, align 8

define i32 @foo(i32* %a, i32* %b, double* %d1, double* %d2) nounwind {
  %1 = alloca i32*, align 8
  %2 = alloca i32*, align 8
  %3 = alloca double*, align 8
  %4 = alloca double*, align 8
  %p1 = alloca i32*, align 8
  %p2 = alloca i32*, align 8
  %x = alloca i32, align 4
  %y = alloca i32, align 4
  %pd1 = alloca double*, align 8
  %pd2 = alloca double*, align 8
  %c = alloca double, align 8
  %d = alloca double, align 8
  %res = alloca double, align 8
  store i32* %a, i32** %1, align 8
  store i32* %b, i32** %2, align 8
  store double* %d1, double** %3, align 8
  store double* %d2, double** %4, align 8
  store i32 32, i32* %x, align 4
  store i32 34, i32* %y, align 4
  store double 5.670000e+01, double* %c, align 8
  store double 2.110000e+01, double* %d, align 8
  store i32* %x, i32** %p1, align 8
  store i32* %y, i32** %p2, align 8
  store double* %c, double** %pd1, align 8
  store double* %d, double** %pd2, align 8
  %5 = load double** %4, align 8
  %6 = load double** %3, align 8
  %7 = load double** %pd1, align 8
  %8 = load double** %pd2, align 8
  %9 = load double* %7, align 8
  %10 = load double* %8, align 8
  %11 = fadd double %9, %10
  %12 = load double* %6, align 8
  %13 = fadd double %11, %12
  %14 = load double* %5, align 8
  %15 = fadd double %13, %14
  %16 = load double* @glb_var3, align 8
  %17 = fadd double %15, %16
  %18 = load double* @glb_var4, align 8
  %19 = fadd double %17, %18
  store double %19, double* %res, align 8
  %20 = load i32** %2, align 8
  %21 = load i32** %1, align 8
  %22 = load i32** %p1, align 8
  %23 = load i32** %p2, align 8
  %24 = load i32* %22, align 4
  %25 = load i32* %23, align 4
  %26 = add i32 %24, %25
  %27 = load i32* %21, align 4
  %28 = add i32 %26, %27
  %29 = load i32* %20, align 4
  %30 = add i32 %28, %29
  %31 = load i32* @glb_var1, align 4
  %32 = add i32 %30, %31
  %33 = load i32* @glb_var2, align 4
  %34 = add i32 %32, %33
  ret i32 %34
}