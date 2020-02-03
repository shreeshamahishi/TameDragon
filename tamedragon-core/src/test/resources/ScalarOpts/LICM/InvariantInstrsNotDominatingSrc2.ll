define i32 @foobar(i32 %a, i32 %b, i32 %c) nounwind uwtable {
  br label %1

; <label>:1                                       ; preds = %10, %0
  %i.0 = phi i32 [ 0, %0 ], [ %11, %10 ]
  %k.0 = phi i32 [ 1, %0 ], [ %k.1, %10 ]
  %result.0 = phi i32 [ undef, %0 ], [ %result.1, %10 ]
  %2 = icmp slt i32 %i.0, %a
  br i1 %2, label %3, label %12

; <label>:3                                       ; preds = %1
  %4 = icmp slt i32 %i.0, %b
  br i1 %4, label %5, label %7

; <label>:5                                       ; preds = %3
  %6 = add i32 %k.0, %c
  br label %10

; <label>:7                                       ; preds = %3
  %8 = add i32 3, %c
  %9 = add i32 %result.0, %8
  br label %10

; <label>:10                                      ; preds = %7, %5
  %k.1 = phi i32 [ %6, %5 ], [ %k.0, %7 ]
  %result.1 = phi i32 [ %result.0, %5 ], [ %9, %7 ]
  %11 = add i32 %i.0, 1
  br label %1

; <label>:12                                      ; preds = %1
  %13 = add i32 %k.0, %c
  %14 = add i32 %result.0, %13
  ret i32 %14
}
