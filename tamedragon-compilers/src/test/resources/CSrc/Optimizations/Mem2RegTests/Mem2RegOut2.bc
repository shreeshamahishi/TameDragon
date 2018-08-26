define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = icmp slt i32 %b, %a
  br i1 %1, label %2, label %4

; <label>:2                                       ; preds = %0
  %3 = sub i32 %b, 1
  br label %4

; <label>:4                                       ; preds = %2, %0
  %c.0 = phi i32 [ %3, %2 ], [ 0, %0 ]
  ret i32 %c.0
}
