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

; <label>:14                              		; preds = %3, %5, %7, %9, %13
  %.0 = phi i32 [ %1, %3 ], [ %1, %5 ], [ %2, %7 ], [ %1, %9 ], [ %m, %13 ]
  ret i32 %.0
}
