define i32 @foobar(i32 %a, i32 %b, i32 %c) nounwind uwtable {
  br label %1

; <label>:1                                       ; preds = %13, %0
  %m.0 = phi i32 [ 0, %0 ], [ %14, %13 ]
  %result.0 = phi i32 [ undef, %0 ], [ %result.1, %13 ]
  %2 = icmp slt i32 %m.0, %a
  br i1 %2, label %3, label %15

; <label>:3                                       ; preds = %1
  %4 = icmp slt i32 %m.0, %c
  br i1 %4, label %5, label %8

; <label>:5                                       ; preds = %3
  %6 = add i32 %b, 78
  %7 = add i32 %result.0, %6
  br label %11

; <label>:8                                       ; preds = %3
  %9 = add i32 %b, %c
  %10 = add i32 %result.0, %9
  br label %11

; <label>:11                                      ; preds = %8, %5
  %result.1 = phi i32 [ %7, %5 ], [ %10, %8 ]
  %12 = add i32 %c, %b
  br label %13

; <label>:13                                      ; preds = %11
  %14 = add i32 %m.0, 1
  br label %1

; <label>:15                                      ; preds = %1
  ret i32 %result.0
}
