define void @foo(i32 %p1, i32 %p2, i32 %p3, i32 %p4, i32 %flag) nounwind {
  br label %1

; <label>:1          		; preds = %19, %0
  %i.0 = phi i32 [ %20, %19 ], [ 0, %0 ]
  %.02 = phi i32 [ %18, %19 ], [ %p3, %0 ]
  %.01 = phi i32 [ %.3, %19 ], [ %p2, %0 ]
  %.0 = phi i32 [ %4, %19 ], [ %p1, %0 ]
  %2 = icmp slt i32 %i.0, 10
  br i1 %2, label %3, label %21

; <label>:3    		; preds = %1
  %4 = add i32 %.0, 1
  %5 = icmp ne i32 %flag, 0
  br i1 %5, label %6, label %17

; <label>:6		; preds = %3
  br label %7

; <label>:7          		; preds = %14, %6
  %j.0 = phi i32 [ %15, %14 ], [ 0, %6 ]
  %.1 = phi i32 [ %.2, %14 ], [ %.01, %6 ]
  %8 = icmp slt i32 %j.0, 10
  br i1 %8, label %9, label %16

; <label>:9      		; preds = %7
  %10 = icmp slt i32 %flag, 6
  br i1 %10, label %11, label %13

; <label>:11		; preds = %9
  %12 = add i32 %.1, 1
  br label %13

; <label>:13        		; preds = %11, %9
  %.2 = phi i32 [ %12, %11 ], [ %.1, %9 ]
  br label %14

; <label>:14		; preds = %13
  %15 = add i32 %j.0, 1
  br label %7

; <label>:16		; preds = %7
  br label %17

; <label>:17         		; preds = %16, %3
  %.3 = phi i32 [ %.1, %16 ], [ %.01, %3 ]
  %18 = add i32 %.02, 1
  br label %19

; <label>:19		; preds = %17
  %20 = add i32 %i.0, 1
  br label %1

; <label>:21		; preds = %1
  ret void
}