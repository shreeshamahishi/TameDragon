define i32 @foo(i32 %m) nounwind {
  %1 = sub i32 %m, 5
  %2 = add i32 %1, %m
  br i1 false, label %3, label %4

; <label>:3		; preds = %0
  br label %14

; <label>:4     		; preds = %0
  br i1 true, label %5, label %6

; <label>:5		; preds = %4
  br label %14

; <label>:6      		; preds = %4
  br i1 false, label %7, label %8

; <label>:7		; preds = %6
  br label %14

; <label>:8      		; preds = %6
  br i1 true, label %9, label %10

; <label>:9		; preds = %8
  br label %14

; <label>:10		; preds = %8
  br label %11

; <label>:11		; preds = %10
  br label %12

; <label>:12		; preds = %11
  br label %13

; <label>:13		; preds = %12
  br label %14

; <label>:14                              		; preds = %13, %9, %7, %5, %3
  %.0 = phi i32 [ %m, %13 ], [ %1, %9 ], [ %2, %7 ], [ %1, %5 ], [ %1, %3 ]
  ret i32 %.0
}