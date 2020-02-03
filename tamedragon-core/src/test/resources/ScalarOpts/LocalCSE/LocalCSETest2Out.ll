define i32 @foo(i32 %a, i32 %b, i32 %sum, i32 %sum2) nounwind {
  %1 = add i32 %a, %b
  %2 = icmp sgt i32 %1, %b
  br i1 %2, label %3, label %6

; <label>:3		; preds = %0
  %4 = add i32 %1, %b
  %5 = sub i32 %4, 1
  br label %9

; <label>:6		; preds = %0
  %7 = add i32 %1, %b
  %8 = add i32 %7, 1
  br label %9

; <label>:9       		; preds = %6, %3
  %.0 = phi i32 [ %8, %6 ], [ %5, %3 ]
  ret i32 %.0
}