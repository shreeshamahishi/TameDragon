define float @foo(float %m) nounwind {
  %1 = fpext float %m to double
  %2 = fsub double %1, 5.500000e+00
  %3 = fptrunc  double %2 to float
  %4 = fadd float %3, %m
  br i1 false, label %5, label %6

; <label>:5		; preds = %0
  br label %16

; <label>:6     		; preds = %0
  br i1 true, label %7, label %8

; <label>:7		; preds = %6
  br label %16

; <label>:8       		; preds = %6
  br i1 false, label %9, label %10

; <label>:9		; preds = %8
  br label %16

; <label>:10      		; preds = %8
  br i1 true, label %11, label %12

; <label>:11		; preds = %10
  br label %16

; <label>:12		; preds = %10
  br label %13

; <label>:13		; preds = %12
  br label %14

; <label>:14		; preds = %13
  br label %15

; <label>:15		; preds = %14
  br label %16

; <label>:16                                		; preds = %15, %11, %9, %7, %5
  %.0 = phi float [ %m, %15 ], [ %3, %11 ], [ %4, %9 ], [ %3, %7 ], [ %3, %5 ]
  ret float %.0
}