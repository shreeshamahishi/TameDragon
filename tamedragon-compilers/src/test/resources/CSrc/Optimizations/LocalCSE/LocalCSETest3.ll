define i32 @foo(i32 %a, i32 %b, i32 %sum, i32 %sum2) nounwind {
  %1 = add i32 2, %a
  %2 = add i32 %1, %b
  %3 = add i32 3, %a
  %4 = add i32 %3, %b
  %5 = add i32 %a, %b
  %6 = add i32 %2, 1
  %7 = sub i32 %4, 1
  %8 = icmp sgt i32 %5, %b
  br i1 %8, label %9, label %10

; <label>:9		; preds = %0
  br label %11

; <label>:10		; preds = %0
  br label %11

; <label>:11      		; preds = %9, %10
  %.0 = phi i32 [ %7, %9 ], [ %6, %10 ]
  ret i32 %.0
}
