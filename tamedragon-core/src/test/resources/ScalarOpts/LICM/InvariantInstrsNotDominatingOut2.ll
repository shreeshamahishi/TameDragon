define i32 @foobar(i32 %a, i32 %b, i32 %c) nounwind uwtable {
  %1 = add i32 3, %c
  br label %2

; <label>:2                                       ; preds = %10, %0
  %i.0 = phi i32 [ 0, %0 ], [ %11, %10 ]
  %k.0 = phi i32 [ 1, %0 ], [ %k.1, %10 ]
  %result.0 = phi i32 [ undef, %0 ], [ %result.1, %10 ]
  %3 = icmp slt i32 %i.0, %a
  br i1 %3, label %4, label %12

; <label>:4                                       ; preds = %2
  %5 = icmp slt i32 %i.0, %b
  br i1 %5, label %6, label %8

; <label>:6                                       ; preds = %4
  %7 = add i32 %k.0, %c
  br label %10

; <label>:8                                       ; preds = %4
  %9 = add i32 %result.0, %1
  br label %10

; <label>:10                                      ; preds = %8, %6
  %k.1 = phi i32 [ %7, %6 ], [ %k.0, %8 ]
  %result.1 = phi i32 [ %result.0, %6 ], [ %9, %8 ]
  %11 = add i32 %i.0, 1
  br label %2

; <label>:12                                      ; preds = %2
  %13 = add i32 %k.0, %c
  %14 = add i32 %result.0, %13
  ret i32 %14
}
