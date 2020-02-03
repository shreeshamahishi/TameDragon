define i32 @foo(i32 %a, i32 %b, i32 %sum) nounwind {
  br label %1

; <label>:1         		; preds = %7, %0
  %i.0 = phi i32 [ %8, %7 ], [ 0, %0 ]
  %.0 = phi i32 [ %6, %7 ], [ %sum, %0 ]
  %2 = add i32 %a, %b
  %3 = icmp slt i32 %i.0, %2
  br i1 %3, label %4, label %9

; <label>:4		; preds = %1
  %5 = add i32 %a, %b
  %6 = add i32 %.0, %5
  br label %7

; <label>:7		; preds = %4
  %8 = add i32 %i.0, 1
  br label %1

; <label>:9		; preds = %1
  ret i32 %.0
}