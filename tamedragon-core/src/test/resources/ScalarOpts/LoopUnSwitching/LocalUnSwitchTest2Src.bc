define void @foo(i32 %p1, i32 %p2, i32 %p3, i32 %p4, i32 %flag) nounwind {
  br label %1

; <label>:1                                       ; preds = %16, %0
  %i.0 = phi i32 [ 0, %0 ], [ %17, %16 ]
  %.03 = phi i32 [ %p4, %0 ], [ %.2, %16 ]
  %.02 = phi i32 [ %p3, %0 ], [ %15, %16 ]
  %.01 = phi i32 [ %p2, %0 ], [ %.1, %16 ]
  %.0 = phi i32 [ %p1, %0 ], [ %4, %16 ]
  %2 = icmp slt i32 %i.0, 10
  br i1 %2, label %3, label %18

; <label>:3                                       ; preds = %1
  %4 = add i32 %.0, 1
  %5 = icmp ne i32 %flag, 0
  br i1 %5, label %6, label %14

; <label>:6                                       ; preds = %3
  %7 = add i32 %.01, 1
  %8 = icmp slt i32 %i.0, 6
  br i1 %8, label %9, label %11

; <label>:9                                       ; preds = %6
  %10 = add i32 %.03, 1
  br label %13

; <label>:11                                      ; preds = %6
  %12 = sub i32 %.03, 1
  br label %13

; <label>:13                                      ; preds = %11, %9
  %.14 = phi i32 [ %10, %9 ], [ %12, %11 ]
  br label %14

; <label>:14                                      ; preds = %13, %3
  %.2 = phi i32 [ %.14, %13 ], [ %.03, %3 ]
  %.1 = phi i32 [ %7, %13 ], [ %.01, %3 ]
  %15 = add i32 %.02, 1
  br label %16

; <label>:16                                      ; preds = %14
  %17 = add i32 %i.0, 1
  br label %1

; <label>:18                                      ; preds = %1
  ret void
}