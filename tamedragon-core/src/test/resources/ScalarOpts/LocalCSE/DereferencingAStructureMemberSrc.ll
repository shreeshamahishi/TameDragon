%struct.student = type { i32, double }

define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = getelementptr inbounds %struct.student, %struct.student* undef, i32 0, i32 0
  store i32 1, i32* %1, align 4
  %2 = getelementptr inbounds %struct.student, %struct.student* undef, i32 0, i32 1
  store double 9.540000e+01, double* %2, align 8
  ret i32 0
}