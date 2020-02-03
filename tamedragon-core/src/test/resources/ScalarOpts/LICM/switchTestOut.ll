define i32 @switchTest(i32 %a, i32 %b, i32 %c) nounwind {
  switch i32 %a, label %9 [
    i32 1, label %1
    i32 2, label %3
    i32 3, label %5
    i32 4, label %7 
  ]

; <label>:1		; preds = %0
  %2 = add i32 0, 1
  br label %11

; <label>:3		; preds = %0
  %4 = add i32 0, 1
  br label %11

; <label>:5		; preds = %0
  %6 = add i32 0, 1
  br label %11

; <label>:7		; preds = %0
  %8 = add i32 0, 1
  br label %11

; <label>:9		; preds = %0
  %10 = add i32 0, 1
  br label %11

; <label>:11                                		; preds = %9, %7, %5, %3, %1
  %k.0 = phi i32 [ %10, %9 ], [ %8, %7 ], [ %6, %5 ], [ %4, %3 ], [ %2, %1 ]
  %12 = add i32 %a, %b
  %13 = add i32 %12, 2
  %14 = icmp slt i32 %13, %c
  br label %15

; <label>:15		; preds = %16, %11
  br label %16

; <label>:16    		; preds = %15
  br i1 %14, label %15, label %17

; <label>:17		; preds = %16
  %18 = add i32 %12, %k.0
  ret i32 %18
}