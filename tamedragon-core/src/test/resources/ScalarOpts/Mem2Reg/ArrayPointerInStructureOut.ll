%struct.student = type { i32, double, [2 x double*] }

define i32 @foo(i32 %a, i32 %b) nounwind {
  %st = alloca %struct.student, align 8
  %practMarks = alloca [2 x double], align 16
  %theoryMarks = alloca [2 x double], align 16
  %1 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 0
  store i32 1, i32* %1, align 4
  br label %2

; <label>:2                                       ; preds = %7, %0
  %i.0 = phi i32 [ 0, %0 ], [ %8, %7 ]
  %3 = icmp slt i32 %i.0, 2
  br i1 %3, label %4, label %9

; <label>:4                                       ; preds = %2
  %5 = getelementptr inbounds [2 x double], [2 x double]* %practMarks, i32 0, i32 %i.0
  store double 5.000000e+01, double* %5, align 8
  %6 = getelementptr inbounds [2 x double], [2 x double]* %theoryMarks, i32 0, i32 %i.0
  store double 5.000000e+01, double* %6, align 8
  br label %7

; <label>:7                                       ; preds = %4
  %8 = add i32 %i.0, 1
  br label %2

; <label>:9                                       ; preds = %2
  %10 = getelementptr inbounds [2 x double], [2 x double]* %practMarks, i32 0, i32 0
  %11 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 2
  %12 = getelementptr inbounds [2 x double*], [2 x double*]* %11, i32 0, i32 0
  store double* %10, double** %12, align 8
  %13 = getelementptr inbounds [2 x double], [2 x double]* %theoryMarks, i32 0, i32 0
  %14 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 2
  %15 = getelementptr inbounds [2 x double*], [2 x double*]* %14, i32 0, i32 1
  store double* %13, double** %15, align 8
  %16 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 2
  %17 = getelementptr inbounds [2 x double*], [2 x double*]* %16, i32 0, i32 0
  %18 = load double*, double** %17, align 8
  %19 = getelementptr inbounds double, double* %18, i32 0
  %20 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 2
  %21 = getelementptr inbounds [2 x double*], [2 x double*]* %20, i32 0, i32 0
  %22 = load double*, double** %21, align 8
  %23 = getelementptr inbounds double, double* %22, i32 1
  %24 = load double, double* %19, align 8
  %25 = load double, double* %23, align 8
  %26 = fadd double %24, %25
  %27 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 2
  %28 = getelementptr inbounds [2 x double*], [2 x double*]* %27, i32 0, i32 1
  %29 = load double*, double** %28, align 8
  %30 = getelementptr inbounds double, double* %29, i32 0
  %31 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 2
  %32 = getelementptr inbounds [2 x double*], [2 x double*]* %31, i32 0, i32 1
  %33 = load double*, double** %32, align 8
  %34 = getelementptr inbounds double, double* %33, i32 1
  %35 = load double, double* %30, align 8
  %36 = load double, double* %34, align 8
  %37 = fadd double %35, %36
  %38 = fadd double %26, %37
  %39 = fdiv double %38, 4.000000e+00
  %40 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 1
  store double %39, double* %40, align 8
  ret i32 0
}
