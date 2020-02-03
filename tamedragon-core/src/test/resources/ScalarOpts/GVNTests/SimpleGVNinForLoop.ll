define i32 @foo(i32 %m) nounwind {
  %1 = mul i32 %m, 2
  br label %2

; <label>:2          		; preds = %11, %0
  %d.0 = phi i32 [ %d.1, %11 ], [ %m, %0 ]
  %i.0 = phi i32 [ %12, %11 ], [ 0, %0 ]
  %3 = icmp slt i32 %i.0, 3
  br i1 %3, label %4, label %13

; <label>:4   		; preds = %2
  %5 = icmp eq i32 %d.0, 5
  br i1 %5, label %6, label %8

; <label>:6		; preds = %4
  %7 = add i32 %1, 3
  br label %10

; <label>:8		; preds = %4
  %9 = add i32 %1, 3
  br label %10

; <label>:10       		; preds = %8, %6
  %d.1 = phi i32 [ %9, %8 ], [ %7, %6 ]
  br label %11

; <label>:11		; preds = %10
  %12 = add i32 %i.0, 1
  br label %2

; <label>:13		; preds = %2
  ret i32 %d.0
}