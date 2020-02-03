%struct.student = type { i32, double }

define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %st = alloca %struct.student*, align 8
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = load %struct.student*, %struct.student** %st, align 8
  %4 = getelementptr inbounds %struct.student, %struct.student* %3, i32 0, i32 0
  store i32 1, i32* %4, align 4
  %5 = load %struct.student*, %struct.student** %st, align 8
  %6 = getelementptr inbounds %struct.student, %struct.student* %5, i32 0, i32 1
  store double 9.540000e+01, double* %6, align 8
  ret i32 0
}