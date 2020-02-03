define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = icmp sgt i32 %a, %b
  br i1 %1, label %2, label %11

; <label>:2                                       ; preds = %0
  %3 = add i32 9, 9
  %4 = icmp sgt i32 %a, %3
  br i1 %4, label %5, label %7

; <label>:5                                       ; preds = %2
  %6 = add i32 %3, 56
  br label %9

; <label>:7                                       ; preds = %2
  %8 = add i32 9, 21
  br label %9

; <label>:9                                       ; preds = %7, %5
  %d.0 = phi i32 [ %6, %5 ], [ %8, %7 ]
  %10 = add i32 %a, %d.0
  br label %13

; <label>:11                                      ; preds = %0
  %12 = add i32 %a, %b
  br label %13

; <label>:13                                      ; preds = %11, %9
  %d.1 = phi i32 [ %10, %9 ], [ %12, %11 ]
  ret i32 %d.1
}
