define i32 @foo(i32 %a, i32 %b, i32 %sum, i32 %sum2) nounwind {
  %1 = add i32 %a, %b
  %2 = add i32 %a, %b
  %3 = add i32 %a, %b
  %4 = add i32 %1, 1
  %5 = sub i32 %2, 1
  %6 = icmp sgt i32 %3, %b
  br i1 %6, label %7, label %8

; <label>:7		; preds = %0
  br label %9

; <label>:8		; preds = %0
  br label %9

; <label>:9       		; preds = %8, %7
  %.0 = phi i32 [ %4, %8 ], [ %5, %7 ]
  ret i32 %.0
}