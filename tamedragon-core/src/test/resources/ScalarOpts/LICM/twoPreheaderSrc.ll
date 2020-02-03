define i32 @twopreheader(i32 %a, i32 %b, i32 %c) nounwind {
  %1 = icmp slt i32 %a, %b
  br i1 %1, label %2, label %3

; <label>:2                                       ; preds = %0
  br label %5

; <label>:3                                       ; preds = %0
  %4 = add i32 0, 1
  br label %5

; <label>:5                                       ; preds = %3, %2
  %.0 = phi i32 [ %b, %2 ], [ %b, %3 ], [ %7, %8 ]
  %6 = add i32 %a, 50
  %7 = add i32 %.0, %6
  br label %8

; <label>:8                                       ; preds = %6
  %9 = icmp slt i32 %7, 20
  br i1 %9, label %5, label %10

; <label>:10                                      ; preds = %9
  %11 = add i32 %a, %7
  ret i32 %11
}