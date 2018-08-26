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
  br label %12

; <label>:12		; preds = %15, %11
  %13 = add i32 %a, %b
  %14 = add i32 %13, 2
  br label %15

; <label>:15    		; preds = %12
  %16 = icmp slt i32 %14, %c
  br i1 %16, label %12, label %17

; <label>:17		; preds = %15
  %18 = add i32 %13, %k.0
  ret i32 %18
}
