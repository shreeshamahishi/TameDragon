define void @foo(i32 %p1, i32 %flag) nounwind {
  br label %1

; <label>:1            		; preds = %17, %0
  %i.0 = phi i32 [ %18, %17 ], [ undef, %0 ]
  %.0 = phi i32 [ %.2, %17 ], [ %p1, %0 ]
  %2 = icmp slt i32 %i.0, 10
  br i1 %2, label %3, label %19

; <label>:3   		; preds = %1
  %4 = icmp ne i32 %flag, 0
  br i1 %4, label %5, label %7

; <label>:5		; preds = %3
  %6 = add i32 %.0, 1
  br label %7

; <label>:7        		; preds = %5, %3
  %.1 = phi i32 [ %6, %5 ], [ %.0, %3 ]
  br label %8

; <label>:8         		; preds = %15, %7
  %j.0 = phi i32 [ %16, %15 ], [ 0, %7 ]
  %.2 = phi i32 [ %.3, %15 ], [ %.1, %7 ]
  %9 = icmp slt i32 %j.0, 10
  br i1 %9, label %10, label %17

; <label>:10     		; preds = %8
  %11 = icmp slt i32 %flag, 6
  br i1 %11, label %12, label %14

; <label>:12		; preds = %10
  %13 = add i32 %.2, 1
  br label %14

; <label>:14        		; preds = %12, %10
  %.3 = phi i32 [ %13, %12 ], [ %.2, %10 ]
  br label %15

; <label>:15		; preds = %14
  %16 = add i32 %j.0, 1
  br label %8

; <label>:17		; preds = %8
  %18 = add i32 %i.0, 1
  br label %1

; <label>:19		; preds = %1
  ret void
}