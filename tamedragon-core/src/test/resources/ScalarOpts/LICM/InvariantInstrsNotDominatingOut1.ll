define i32 @foobar(i32 %a, i32 %b, i32 %c) nounwind uwtable {
  %1 = add i32 %b, 78
  %2 = add i32 %b, %c
  br label %3

; <label>:3                                       ; preds = %12, %0
  %m.0 = phi i32 [ 0, %0 ], [ %13, %12 ]
  %result.0 = phi i32 [ undef, %0 ], [ %result.1, %12 ]
  %4 = icmp slt i32 %m.0, %a
  br i1 %4, label %5, label %14

; <label>:5                                       ; preds = %3
  %6 = icmp slt i32 %m.0, %c
  br i1 %6, label %7, label %9

; <label>:7                                       ; preds = %5
  %8 = add i32 %result.0, %1
  br label %11

; <label>:9                                       ; preds = %5
  %10 = add i32 %result.0, %2
  br label %11

; <label>:11                                      ; preds = %9, %7
  %result.1 = phi i32 [ %8, %7 ], [ %10, %9 ]
  br label %12

; <label>:12                                      ; preds = %11
  %13 = add i32 %m.0, 1
  br label %3

; <label>:14                                      ; preds = %3
  ret i32 %result.0
}
