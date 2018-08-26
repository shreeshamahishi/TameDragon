define void @foo(i32 %p1, i32 %p2, i32 %p3, i32 %flag) nounwind {
  br label %1

; <label>:1          		; preds = %10, %0
  %i.0 = phi i32 [ %11, %10 ], [ 0, %0 ]
  %.02 = phi i32 [ %9, %10 ], [ %p3, %0 ]
  %.01 = phi i32 [ %.1, %10 ], [ %p2, %0 ]
  %.0 = phi i32 [ %4, %10 ], [ %p1, %0 ]
  %2 = icmp slt i32 %i.0, 10
  br i1 %2, label %3, label %12

; <label>:3   		; preds = %1
  %4 = add i32 %.0, 1
  %5 = icmp ne i32 %flag, 0
  br i1 %5, label %6, label %8

; <label>:6		; preds = %3
  %7 = add i32 %.01, 1
  br label %8

; <label>:8         		; preds = %6, %3
  %.1 = phi i32 [ %7, %6 ], [ %.01, %3 ]
  %9 = add i32 %.02, 1
  br label %10

; <label>:10		; preds = %8
  %11 = add i32 %i.0, 1
  br label %1

; <label>:12		; preds = %1
  ret void
}