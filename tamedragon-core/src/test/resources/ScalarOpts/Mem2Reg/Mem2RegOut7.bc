define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = icmp sgt i32 %a, %b
  br i1 %1, label %2, label %4

; <label>:2                                       ; preds = %0
  %3 = add i32 32, undef
  br label %6

; <label>:4                                       ; preds = %0
  %5 = add i32 32, %b
  br label %6

; <label>:6                                       ; preds = %4, %2
  %d.0 = phi i32 [ %3, %2 ], [ %5, %4 ]
  %7 = add i32 %a, %b
  %8 = add i32 %7, undef
  %9 = add i32 %d.0, %8
  ret i32 %9
}
