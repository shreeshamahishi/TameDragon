%struct.subject = type { double, double }
%struct.student = type { i32, double, [2 x %struct.subject] }

define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %i = alloca i32, align 4
  %st = alloca %struct.student, align 8
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 0
  store i32 1, i32* %3, align 4
  %4 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 1
  store double 9.050000e+01, double* %4, align 8
  store i32 0, i32* %i, align 4
  br label %5

; <label>:5 		; preds = %0, %17
  %6 = load i32, i32* %i, align 4
  %7 = icmp slt i32 %6, 2
  br i1 %7, label %8, label %20

; <label>:8                                                       		; preds = %5
  %9 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 2
  %10 = load i32, i32* %i, align 4
  %11 = getelementptr inbounds [2 x %struct.subject], [2 x %struct.subject]* %9, i32 0, i32 %10
  %12 = getelementptr inbounds %struct.subject, %struct.subject* %11, i32 0, i32 0
  store double 5.000000e+01, double* %12, align 8
  %13 = getelementptr inbounds %struct.student, %struct.student* %st, i32 0, i32 2
  %14 = load i32, i32* %i, align 4
  %15 = getelementptr inbounds [2 x %struct.subject], [2 x %struct.subject]* %13, i32 0, i32 %14
  %16 = getelementptr inbounds %struct.subject, %struct.subject* %15, i32 0, i32 1
  store double 6.000000e+01, double* %16, align 8
  br label %17

; <label>:17      		; preds = %8
  %18 = load i32, i32* %i, align 4
  %19 = add i32 %18, 1
  store i32 %19, i32* %i, align 4
  br label %5

; <label>:20		; preds = %5
  ret i32 0
}
