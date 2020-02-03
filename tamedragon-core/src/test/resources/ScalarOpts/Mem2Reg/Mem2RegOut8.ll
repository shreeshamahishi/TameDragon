define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = add i32 %a, 3
  %2 = add i32 %b, 6
  %3 = icmp sgt i32 %1, %2
  br i1 %3, label %4, label %11

; <label>:4                                       ; preds = %0
  %5 = mul i32 21, %a
  %6 = add i32 3, 4
  %7 = add i32 %5, 3
  %8 = add i32 %6, 4
  %9 = add i32 %6, %7
  %10 = add i32 %9, %8
  br label %16

; <label>:11                                      ; preds = %0
  %12 = mul i32 68, %b
  %13 = add i32 45, %b
  %14 = add i32 %13, 3
  %15 = add i32 %12, %14
  br label %16

; <label>:16                                      ; preds = %11, %4
  %d.0 = phi i32 [ %10, %4 ], [ %15, %11 ]
  %.0 = phi i32 [ %6, %4 ], [ %13, %11 ]
  %17 = add i32 %d.0, %.0
  ret i32 %d.0
}
