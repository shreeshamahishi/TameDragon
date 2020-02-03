define void @foo(i32 %p1, i32 %p2, i32 %p3, i32 %p4, i32 %flag) nounwind {
  br label %1

; <label>:1                                       ; preds = %21, %0
  %i.0 = phi i32 [ 0, %0 ], [ %22, %21 ]
  %.03 = phi i32 [ %p4, %0 ], [ %.3, %21 ]
  %.02 = phi i32 [ %p3, %0 ], [ %20, %21 ]
  %.01 = phi i32 [ %p2, %0 ], [ %.1, %21 ]
  %.0 = phi i32 [ %p1, %0 ], [ %4, %21 ]
  %2 = icmp slt i32 %i.0, 10
  br i1 %2, label %3, label %23

; <label>:3                                       ; preds = %1
  %4 = add i32 %.0, 1
  %5 = icmp ne i32 %flag, 0
  br i1 %5, label %6, label %19

; <label>:6                                       ; preds = %3
  %7 = add i32 %.01, 1
  %8 = icmp slt i32 %flag, 6
  br i1 %8, label %9, label %11

; <label>:9                                       ; preds = %6
  %10 = add i32 %.03, 1
  br label %18

; <label>:11                                      ; preds = %6
  %12 = icmp sgt i32 %flag, 7
  br i1 %12, label %13, label %15

; <label>:13                                      ; preds = %11
  %14 = sub i32 %.03, 1
  br label %17

; <label>:15                                      ; preds = %11
  %16 = add i32 %.03, 1
  br label %17

; <label>:17                                      ; preds = %15, %13
  %.14 = phi i32 [ %14, %13 ], [ %16, %15 ]
  br label %18

; <label>:18                                      ; preds = %17, %9
  %.2 = phi i32 [ %10, %9 ], [ %.14, %17 ]
  br label %19

; <label>:19                                      ; preds = %18, %3
  %.3 = phi i32 [ %.2, %18 ], [ %.03, %3 ]
  %.1 = phi i32 [ %7, %18 ], [ %.01, %3 ]
  %20 = add i32 %.02, 1
  br label %21

; <label>:21                                      ; preds = %19
  %22 = add i32 %i.0, 1
  br label %1

; <label>:23                                      ; preds = %1
  ret void
}