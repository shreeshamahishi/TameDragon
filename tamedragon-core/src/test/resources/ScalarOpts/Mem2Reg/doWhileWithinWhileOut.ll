define i32 @foo(i32 %a, i32 %b) nounwind {
  br label %1

; <label>:1                                       ; preds = %12, %0
  %k.0 = phi i32 [ 0, %0 ], [ %8, %12 ]
  %i.0 = phi i32 [ 0, %0 ], [ %13, %12 ]
  %2 = icmp slt i32 %i.0, 10
  br i1 %2, label %3, label %14

; <label>:3                                       ; preds = %1
  %4 = icmp slt i32 %i.0, 2
  br i1 %4, label %5, label %6

; <label>:5                                       ; preds = %3
  br label %14

; <label>:6                                       ; preds = %3
  br label %7

; <label>:7                                       ; preds = %9, %6
  %k.1 = phi i32 [ %k.0, %6 ], [ %8, %9 ]
  %8 = add i32 %k.1, 1
  br label %9

; <label>:9                                       ; preds = %7
  %10 = icmp slt i32 %8, 10
  br i1 %10, label %7, label %11

; <label>:11                                      ; preds = %9
  br label %12

; <label>:12                                      ; preds = %11
  %13 = add i32 %i.0, 1
  br label %1

; <label>:14                                      ; preds = %5, %1
  ret i32 %k.0
}
