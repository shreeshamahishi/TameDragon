define i32 @foobar(i32 %a, i32 %b, i32 %c) nounwind {
  br label %1

; <label>:1       		; preds = %3, %0
  %i.0 = phi i32 [ %5, %3 ], [ 0, %0 ]
  %.0 = phi i32 [ %4, %3 ], [ %a, %0 ]
  %2 = icmp slt i32 %i.0, %.0
  br i1 %2, label %3, label %6

; <label>:3		; preds = %1
  %4 = add i32 %b, %c
  %5 = add i32 %i.0, 1
  br label %1

; <label>:6		; preds = %1
  %7 = add i32 1, 2
  %8 = add i32 undef, %7
  ret i32 %8
}