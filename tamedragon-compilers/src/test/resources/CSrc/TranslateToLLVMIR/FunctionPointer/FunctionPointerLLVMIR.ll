@.str = private unnamed_addr constant [14 x i8] c"sum(sin): %f\0A\00", align 1
@.str1 = private unnamed_addr constant [14 x i8] c"sum(cos): %f\0A\00", align 1

declare i32 @printf(i8*, ...) 

declare double @sin(double) 

declare double @cos(double) 

define double @compute_sum(double (double)* %funcp) nounwind {
  %1 = alloca double (double)*, align 8
  %sum = alloca double, align 8
  store double (double)* %funcp, double (double)** %1, align 8
  %2 = load double (double)*, double (double)** %1, align 8
  %3 = call double %2(double 3.000000e+01)
  store double %3, double* %sum, align 8
  %4 = load double, double* %sum, align 8
  ret double %4
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %fp = alloca double (double)*, align 8
  %sum = alloca double, align 8
  store i32 0, i32* %1, align 4
  store double (double)* @sin, double (double)** %fp, align 8
  %2 = load double (double)*, double (double)** %fp, align 8
  %3 = call double @compute_sum(double (double)* %2)
  store double %3, double* %sum, align 8
  %4 = getelementptr inbounds [14 x i8], [14 x i8]* @.str, i32 0, i32 0
  %5 = load double, double* %sum, align 8
  %6 = call i32 (i8*, ...)* @printf(i8* %4, double %5)
  %7 = call double @compute_sum(double (double)* @cos)
  store double %7, double* %sum, align 8
  %8 = getelementptr inbounds [14 x i8], [14 x i8]* @.str1, i32 0, i32 0
  %9 = load double, double* %sum, align 8
  %10 = call i32 (i8*, ...)* @printf(i8* %8, double %9)
  ret i32 0
}
