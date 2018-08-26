define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = icmp slt i32 %a, %b
  br i1 %1, label %2, label %6

; <label>:2                                       ; preds = %0
  %3 = add i32 %a, 1
  %4 = add i32 %3, 2
  %5 = add i32 %b, 1
  br label %10

; <label>:6                                       ; preds = %0
  %7 = add i32 %a, 1
  %8 = add i32 %7, 3
  %9 = add i32 %b, 1
  br label %10

; <label>:10                                      ; preds = %6, %2
  %.0 = phi i32 [ %5, %2 ], [ %9, %6 ]
  ret i32 %.0
}