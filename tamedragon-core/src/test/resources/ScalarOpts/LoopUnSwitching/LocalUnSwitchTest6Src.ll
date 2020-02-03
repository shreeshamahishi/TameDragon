define void @foo(i32 %p1, i32 %flag) nounwind {
  br label %1

; <label>:1        		; preds = %5, %0
  %i.0 = phi i32 [ %6, %5 ], [ 0, %0 ]
  %.0 = phi i32 [ %4, %5 ], [ %p1, %0 ]
  %2 = icmp slt i32 %flag, 10
  br i1 %2, label %3, label %7

; <label>:3		; preds = %1
  %4 = add i32 %.0, 1
  br label %5

; <label>:5		; preds = %3
  %6 = add i32 %i.0, 1
  br label %1

; <label>:7		; preds = %1
  ret void
}