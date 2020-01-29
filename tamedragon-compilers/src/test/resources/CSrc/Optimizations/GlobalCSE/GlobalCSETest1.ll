define i32 @foo(i32 %a, i32 %b, i32 %sum) nounwind {
  br label %1

; <label>:1         		; preds = %0, %6
  %i.0 = phi i32 [ 0, %0 ], [ %7, %6 ]
  %.0 = phi i32 [ %sum, %0 ], [ %5, %6 ]
  %2 = add i32 %a, %b
  %3 = icmp slt i32 %i.0, %2
  br i1 %3, label %4, label %8

; <label>:4		; preds = %1
  %5 = add i32 %.0, %2
  br label %6

; <label>:6		; preds = %4
  %7 = add i32 %i.0, 1
  br label %1

; <label>:8		; preds = %1
  ret i32 %.0
}
