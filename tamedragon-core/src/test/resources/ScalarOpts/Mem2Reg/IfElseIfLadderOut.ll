define i32 @foo(i32 %m) nounwind uwtable {
  %1 = sub i32 10, 5
  %2 = sub i32 10, 5
  %3 = add i32 %1, 10
  %4 = add i32 10, %2
  %5 = icmp slt i32 %1, %2
  br i1 %5, label %6, label %7

; <label>:6                                       ; preds = %0
  br label %20

; <label>:7                                       ; preds = %0
  %8 = icmp eq i32 %2, %1
  br i1 %8, label %9, label %10

; <label>:9                                       ; preds = %7
  br label %20

; <label>:10                                      ; preds = %7
  %11 = icmp ne i32 10, 10
  br i1 %11, label %12, label %13

; <label>:12                                      ; preds = %10
  br label %20

; <label>:13                                      ; preds = %10
  %14 = icmp sge i32 10, 10
  br i1 %14, label %15, label %16

; <label>:15                                      ; preds = %13
  br label %20

; <label>:16                                      ; preds = %13
  br label %17

; <label>:17                                      ; preds = %16
  br label %18

; <label>:18                                      ; preds = %17
  br label %19

; <label>:19                                      ; preds = %18
  br label %20

; <label>:20                                      ; preds = %19, %15, %12, %9, %6
  %.0 = phi i32 [ %1, %6 ], [ %2, %9 ], [ %3, %12 ], [ %2, %15 ], [ 10, %19 ]
  ret i32 %.0
}
