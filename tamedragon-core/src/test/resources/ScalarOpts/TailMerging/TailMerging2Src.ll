define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = icmp slt i32 %a, %b
  br i1 %1, label %2, label %5

; <label>:2                                       ; preds = %0
  %3 = add i32 %a, 1
  %4 = add i32 %b, 1
  br label %8

; <label>:5                                       ; preds = %0
  %6 = add i32 %a, 1
  %7 = add i32 %b, 1
  br label %8

; <label>:8                                       ; preds = %5, %2
  %.0 = phi i32 [ %4, %2 ], [ %7, %5 ]
  ret i32 %.0
}