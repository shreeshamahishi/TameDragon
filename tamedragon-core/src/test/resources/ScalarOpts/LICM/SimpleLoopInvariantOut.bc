define i32 @foo(i32 %a) nounwind uwtable {
  br label %1

; <label>:1                                       ; preds = %5, %0
  %result.0 = phi i32 [ %4, %5 ], [ undef, %0 ]
  %m.0 = phi i32 [ %6, %5 ], [ 0, %0 ]
  %2 = icmp slt i32 %m.0, %a
  br i1 %2, label %3, label %7

; <label>:3                                       ; preds = %1
  %4 = add i32 %result.0, 102
  br label %5

; <label>:5                                       ; preds = %3
  %6 = add i32 %m.0, 1
  br label %1

; <label>:7                                       ; preds = %1
  ret i32 %result.0
}
