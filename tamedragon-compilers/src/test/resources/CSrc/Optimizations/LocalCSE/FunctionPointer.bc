define double @compute_sum(double (double)* %funcp) nounwind {
  %1 = call double %funcp(double 3.000000e+01)
  ret double %1
}

define i32 @main() nounwind {
  %1 = call double @compute_sum(double (double)* @sin)
  %2 = call double @compute_sum(double (double)* @cos)
  ret i32 0
}

declare double @sin(double) readnone

declare double @cos(double) readnone