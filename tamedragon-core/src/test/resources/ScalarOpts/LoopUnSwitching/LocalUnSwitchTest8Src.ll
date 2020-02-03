define void @foo(i32 %p1, i32 %flag) nounwind {
  br label %1

; <label>:1           		; preds = %7, %0
  %i.0 = phi i32 [ %8, %7 ], [ undef, %0 ]
  %.0 = phi i32 [ %.1, %7 ], [ %p1, %0 ]
  %2 = icmp slt i32 %i.0, 10
  br i1 %2, label %3, label %9

; <label>:3   		; preds = %1
  %4 = icmp ne i32 %flag, 0
  br i1 %4, label %5, label %7

; <label>:5		; preds = %3
  %6 = add i32 %.0, 1
  br label %7

; <label>:7        		; preds = %5, %3
  %.1 = phi i32 [ %6, %5 ], [ %.0, %3 ]
  %8 = add i32 %i.0, 1
  br label %1

; <label>:9		; preds = %1
  ret void
}