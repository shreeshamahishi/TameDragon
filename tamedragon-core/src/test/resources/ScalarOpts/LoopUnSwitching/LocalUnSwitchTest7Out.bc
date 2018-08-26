define void @foo(i32 %p1, i32 %flag) nounwind {
  br label %1

; <label>:1        		; preds = %9, %0
  %i.0 = phi i32 [ %10, %9 ], [ 0, %0 ]
  %.0 = phi i32 [ %8, %9 ], [ %p1, %0 ]
  %2 = icmp slt i32 %i.0, 10
  br i1 %2, label %3, label %5

; <label>:3  		; preds = %1
  %4 = icmp slt i32 %flag, 10
  br label %5

; <label>:5        		; preds = %3, %1
  %6 = phi i1 [ false, %1 ], [ %4, %3 ]
  br i1 %6, label %7, label %11

; <label>:7		; preds = %5
  %8 = add i32 %.0, 1
  br label %9

; <label>:9		; preds = %7
  %10 = add i32 %i.0, 1
  br label %1

; <label>:11		; preds = %5
  ret void
}