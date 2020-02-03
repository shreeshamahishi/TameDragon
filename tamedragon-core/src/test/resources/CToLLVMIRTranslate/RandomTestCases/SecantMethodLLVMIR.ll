@.str = private unnamed_addr constant [25 x i8] c"\0AEnter the value of x1: \00", align 16
@.str1 = private unnamed_addr constant [4 x i8] c"%lf\00", align 1
@.str2 = private unnamed_addr constant [25 x i8] c"\0AEnter the value of x2: \00", align 16
@.str3 = private unnamed_addr constant [49 x i8] c"\0A______________________________________________\0A\00", align 16
@.str4 = private unnamed_addr constant [36 x i8] c"\0A    x1\09  x2\09  x3\09     f(x1)\09 f(x2)\00", align 16
@.str5 = private unnamed_addr constant [29 x i8] c"\0A%lf   %lf   %lf   %lf   %lf\00", align 16
@.str6 = private unnamed_addr constant [17 x i8] c"\0A\0AApp.root = %lf\00", align 16

declare i32 @printf(i8*, ...) 

declare i32 @scanf(i8*, ...) 

define double @fabs(double %d) nounwind {
  %1 = alloca double, align 8
  %2 = alloca double, align 8
  store double %d, double* %1, align 8
  %3 = load double, double* %1, align 8
  %4 = sitofp i32 0 to double
  %5 = fcmp olt double %3, %4
  br i1 %5, label %6, label %9

; <label>:6            		; preds = %0
  %7 = load double, double* %1, align 8
  %8 = fmul double -1.000000e+00, %7
  store double %8, double* %2, align 8
  br label %11

; <label>:9             		; preds = %0
  %10 = load double, double* %1, align 8
  store double %10, double* %2, align 8
  br label %11

; <label>:11        		; preds = %6, %9
  %12 = load double, double* %2, align 8
  ret double %12
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %x1 = alloca double, align 8
  %x2 = alloca double, align 8
  %x3 = alloca double, align 8
  %f1 = alloca double, align 8
  %f2 = alloca double, align 8
  %t = alloca double, align 8
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [25 x i8], [25 x i8]* @.str, i32 0, i32 0
  %3 = call i32 (i8*, ...)* @printf(i8* %2)
  %4 = getelementptr inbounds [4 x i8], [4 x i8]* @.str1, i32 0, i32 0
  %5 = call i32 (i8*, ...)* @scanf(i8* %4, double* %x1)
  %6 = getelementptr inbounds [25 x i8], [25 x i8]* @.str2, i32 0, i32 0
  %7 = call i32 (i8*, ...)* @printf(i8* %6)
  %8 = getelementptr inbounds [4 x i8], [4 x i8]* @.str1, i32 0, i32 0
  %9 = call i32 (i8*, ...)* @scanf(i8* %8, double* %x2)
  %10 = getelementptr inbounds [49 x i8], [49 x i8]* @.str3, i32 0, i32 0
  %11 = call i32 (i8*, ...)* @printf(i8* %10)
  %12 = getelementptr inbounds [36 x i8], [36 x i8]* @.str4, i32 0, i32 0
  %13 = call i32 (i8*, ...)* @printf(i8* %12)
  %14 = getelementptr inbounds [49 x i8], [49 x i8]* @.str3, i32 0, i32 0
  %15 = call i32 (i8*, ...)* @printf(i8* %14)
  br label %16

; <label>:16                                                                        		; preds = %0, %64
  %17 = load double, double* %x1, align 8
  %18 = load double, double* %x1, align 8
  %19 = fmul double %17, %18
  %20 = load double, double* %x1, align 8
  %21 = sitofp i32 4 to double
  %22 = fmul double %21, %20
  %23 = fsub double %19, %22
  %24 = sitofp i32 10 to double
  %25 = fsub double %23, %24
  store double %25, double* %f1, align 8
  %26 = load double, double* %x2, align 8
  %27 = load double, double* %x2, align 8
  %28 = fmul double %26, %27
  %29 = load double, double* %x2, align 8
  %30 = sitofp i32 4 to double
  %31 = fmul double %30, %29
  %32 = fsub double %28, %31
  %33 = sitofp i32 10 to double
  %34 = fsub double %32, %33
  store double %34, double* %f2, align 8
  %35 = load double, double* %x2, align 8
  %36 = load double, double* %f2, align 8
  %37 = load double, double* %x2, align 8
  %38 = load double, double* %x1, align 8
  %39 = fsub double %37, %38
  %40 = fmul double %36, %39
  %41 = load double, double* %f2, align 8
  %42 = load double, double* %f1, align 8
  %43 = fsub double %41, %42
  %44 = fdiv double %40, %43
  %45 = fsub double %35, %44
  store double %45, double* %x3, align 8
  %46 = getelementptr inbounds [29 x i8], [29 x i8]* @.str5, i32 0, i32 0
  %47 = load double, double* %x1, align 8
  %48 = load double, double* %x2, align 8
  %49 = load double, double* %x3, align 8
  %50 = load double, double* %f1, align 8
  %51 = load double, double* %f2, align 8
  %52 = call i32 (i8*, ...)* @printf(i8* %46, double %47, double %48, double %49, double %50, double %51)
  %53 = load double, double* %x2, align 8
  store double %53, double* %x1, align 8
  %54 = load double, double* %x3, align 8
  store double %54, double* %x2, align 8
  %55 = load double, double* %f2, align 8
  %56 = sitofp i32 0 to double
  %57 = fcmp olt double %55, %56
  br i1 %57, label %58, label %61

; <label>:58            		; preds = %16
  %59 = load double, double* %f2, align 8
  %60 = call double @fabs(double %59)
  store double %60, double* %t, align 8
  br label %63

; <label>:61            		; preds = %16
  %62 = load double, double* %f2, align 8
  store double %62, double* %t, align 8
  br label %63

; <label>:63		; preds = %58, %61
  br label %64

; <label>:64            		; preds = %63
  %65 = load double, double* %t, align 8
  %66 = fcmp ogt double %65, 1.000000e-04
  br i1 %66, label %16, label %67

; <label>:67                                            		; preds = %64
  %68 = getelementptr inbounds [49 x i8], [49 x i8]* @.str3, i32 0, i32 0
  %69 = call i32 (i8*, ...)* @printf(i8* %68)
  %70 = getelementptr inbounds [17 x i8], [17 x i8]* @.str6, i32 0, i32 0
  %71 = load double, double* %x3, align 8
  %72 = call i32 (i8*, ...)* @printf(i8* %70, double %71)
  %73 = load i32, i32* %1, align 4
  ret i32 %73
}
