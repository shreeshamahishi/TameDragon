define i32 @foo(i32 %m) nounwind {
  %1 = mul i32 %m, 2
  %2 = mul i32 %m, 2
  br label %3

; <label>:3          		; preds = %12, %0
  %d.0 = phi i32 [ %d.1, %12 ], [ %m, %0 ]
  %i.0 = phi i32 [ %13, %12 ], [ 0, %0 ]
  %4 = icmp slt i32 %i.0, 3
  br i1 %4, label %5, label %14

; <label>:5   		; preds = %3
  %6 = icmp eq i32 %d.0, 5
  br i1 %6, label %7, label %9

; <label>:7		; preds = %5
  %8 = add i32 %1, 3
  br label %11

; <label>:9		; preds = %5
  %10 = add i32 %2, 3
  br label %11

; <label>:11        		; preds = %9, %7
  %d.1 = phi i32 [ %10, %9 ], [ %8, %7 ]
  br label %12

; <label>:12		; preds = %11
  %13 = add i32 %i.0, 1
  br label %3

; <label>:14		; preds = %3
  ret i32 %d.0
}