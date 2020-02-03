define i32 @foo(i32 %a, i32 %b, i32 %sum, i32 %sum2) nounwind {
  %1 = add i32 %a, %b
  %2 = add i32 %1, 1
  %3 = sub i32 %1, 1
  %4 = icmp sgt i32 %1, %b
  br i1 %4, label %5, label %6

; <label>:5		; preds = %0
  br label %7

; <label>:6		; preds = %0
  br label %7

; <label>:7       		; preds = %6, %5
  %.0 = phi i32 [ %2, %6 ], [ %3, %5 ]
  ret i32 %.0
}