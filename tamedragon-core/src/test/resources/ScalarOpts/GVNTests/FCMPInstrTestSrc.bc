define float @foo(float %m) nounwind {
  %1 = fpext float %m to double
  %2 = fsub double %1, 5.500000e+00
  %3 = fptrunc  double %2 to float
  %4 = fpext float %m to double
  %5 = fsub double %4, 5.500000e+00
  %6 = fptrunc  double %5 to float
  %7 = fadd float %3, %m
  %8 = fadd float %m, %6
  %9 = fcmp olt float %3, %6
  br i1 %9, label %10, label %11

; <label>:10		; preds = %0
  br label %24

; <label>:11     		; preds = %0
  %12 = fcmp oeq float %6, %3
  br i1 %12, label %13, label %14

; <label>:13		; preds = %11
  br label %24

; <label>:14    		; preds = %11
  %15 = fcmp one float %7, %8
  br i1 %15, label %16, label %17

; <label>:16		; preds = %14
  br label %24

; <label>:17    		; preds = %14
  %18 = fcmp oge float %7, %8
  br i1 %18, label %19, label %20

; <label>:19		; preds = %17
  br label %24

; <label>:20		; preds = %17
  br label %21

; <label>:21		; preds = %20
  br label %22

; <label>:22		; preds = %21
  br label %23

; <label>:23		; preds = %22
  br label %24

; <label>:24                                		; preds = %23, %19, %16, %13, %10
  %.0 = phi float [ %m, %23 ], [ %6, %19 ], [ %7, %16 ], [ %6, %13 ], [ %3, %10 ]
  ret float %.0
}