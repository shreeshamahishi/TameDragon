define i32 @foo(i32 %a, i32 %b) nounwind {
  br label %1

; <label>:1       		; preds = %8, %0
  %i.0 = phi i32 [ %6, %8 ], [ 0, %0 ]
  %2 = icmp slt i32 %i.0, 10
  br i1 %2, label %3, label %9

; <label>:3   		; preds = %1
  %4 = icmp eq i32 %i.0, 2
  br i1 %4, label %5, label %7

; <label>:5		; preds = %3
  %6 = add i32 %i.0, 1
  br label %8

; <label>:7		; preds = %3
  br label %9

; <label>:8		; preds = %5
  br label %1

; <label>:9		; preds = %7, %1
  ret i32 %i.0
}