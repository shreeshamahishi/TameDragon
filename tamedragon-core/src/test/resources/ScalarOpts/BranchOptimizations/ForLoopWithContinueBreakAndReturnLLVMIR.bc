define void @foo(i32 %a, i32 %b) nounwind {
  br label %1

; <label>:1                  		; preds = %13, %5, %0
  %i.0 = phi i32 [ %14, %13 ], [ %i.0, %5 ], [ 0, %0 ]
  %2 = icmp slt i32 %i.0, 10
  br i1 %2, label %3, label %15

; <label>:3   		; preds = %1
  %4 = icmp eq i32 %i.0, 2
  br i1 %4, label %5, label %6

; <label>:5		; preds = %3
  br label %1

; <label>:6   		; preds = %3
  %7 = icmp eq i32 %i.0, 1
  br i1 %7, label %8, label %9

; <label>:8		; preds = %6
  br label %15

; <label>:9		; preds = %6
  br label %10

; <label>:10     		; preds = %9
  %11 = icmp eq i32 %i.0, 6
  br i1 %11, label %12, label %13

; <label>:12		; preds = %10
  br label %16

; <label>:13		; preds = %10
  %14 = add i32 %i.0, 1
  br label %1

; <label>:15		; preds = %8, %1
  br label %16

; <label>:16		; preds = %15, %12
  ret void
}