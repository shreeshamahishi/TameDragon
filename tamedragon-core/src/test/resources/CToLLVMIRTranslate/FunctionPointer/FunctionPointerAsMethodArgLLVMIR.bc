define double @compute_sum(double (double)* %funcp, i32 %a) nounwind {
  %1 = alloca double (double)*, align 4
  %2 = alloca i32, align 4
  %sum = alloca double, align 8
  store double (double)* %funcp, double (double)** %1, align 4
  store i32 %a, i32* %2, align 4
  %3 = load i32* %2, align 4
  %4 = add i32 %3, 1
  store i32 %4, i32* %2, align 4
  %5 = load double (double)** %1, align 4
  %6 = call double %5(double 3.000000e+01)
  store double %6, double* %sum, align 8
  %7 = load double* %sum, align 8
  ret double %7
}