%struct.student = type { i32, double, [2 x double*] }

define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %i = alloca i32, align 4
  %st = alloca %struct.student, align 8
  %practMarks = alloca [2 x double], align 16
  %theoryMarks = alloca [2 x double], align 16
  %totalPracticalMarks = alloca double, align 8
  %totalTheoryMarks = alloca double, align 8
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 0
  store i32 1, i32* %3, align 4
  store i32 0, i32* %i, align 4
  br label %4

; <label>:4 		; preds = %0, %12
  %5 = load i32, i32* %i, align 4
  %6 = icmp slt i32 %5, 2
  br i1 %6, label %7, label %15

; <label>:7                      		; preds = %4
  %8 = load i32, i32* %i, align 4
  %9 = getelementptr inbounds [2 x double], [2 x double]* %practMarks, i32 0, i32 %8
  store double 5.000000e+01, double* %9, align 8
  %10 = load i32, i32* %i, align 4
  %11 = getelementptr inbounds [2 x double], [2 x double]* %theoryMarks, i32 0, i32 %10
  store double 5.000000e+01, double* %11, align 8
  br label %12

; <label>:12      		; preds = %7
  %13 = load i32, i32* %i, align 4
  %14 = add i32 %13, 1
  store i32 %14, i32* %i, align 4
  br label %4

; <label>:15                                                         		; preds = %4
  %16 = getelementptr inbounds [2 x double], [2 x double]* %practMarks, i32 0, i32 0
  %17 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 2
  %18 = getelementptr inbounds [2 x double*], [2 x double*]* %17, i32 0, i32 0
  store double* %16, double** %18, align 8
  %19 = getelementptr inbounds [2 x double], [2 x double]* %theoryMarks, i32 0, i32 0
  %20 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 2
  %21 = getelementptr inbounds [2 x double*], [2 x double*]* %20, i32 0, i32 1
  store double* %19, double** %21, align 8
  %22 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 2
  %23 = getelementptr inbounds [2 x double*], [2 x double*]* %22, i32 0, i32 0
  %24 = load double*, double** %23, align 8
  %25 = getelementptr inbounds double, double* %24, i32 0
  %26 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 2
  %27 = getelementptr inbounds [2 x double*], [2 x double*]* %26, i32 0, i32 0
  %28 = load double*, double** %27, align 8
  %29 = getelementptr inbounds double, double* %28, i32 1
  %30 = load double, double* %25, align 8
  %31 = load double, double* %29, align 8
  %32 = fadd double %30, %31
  store double %32, double* %totalPracticalMarks, align 8
  %33 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 2
  %34 = getelementptr inbounds [2 x double*], [2 x double*]* %33, i32 0, i32 1
  %35 = load double*, double** %34, align 8
  %36 = getelementptr inbounds double, double* %35, i32 0
  %37 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 2
  %38 = getelementptr inbounds [2 x double*], [2 x double*]* %37, i32 0, i32 1
  %39 = load double*, double** %38, align 8
  %40 = getelementptr inbounds double, double* %39, i32 1
  %41 = load double, double* %36, align 8
  %42 = load double, double* %40, align 8
  %43 = fadd double %41, %42
  store double %43, double* %totalTheoryMarks, align 8
  %44 = load double, double* %totalPracticalMarks, align 8
  %45 = load double, double* %totalTheoryMarks, align 8
  %46 = fadd double %44, %45
  %47 = fdiv double %46, 4.000000e+00
  %48 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 1
  store double %47, double* %48, align 8
  ret i32 0
}
