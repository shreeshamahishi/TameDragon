define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = add i32 %a, 24
  %2 = icmp sgt i32 %1, %b
  br i1 %2, label %3, label %4

; <label>:3		; preds = %0
  br label %5

; <label>:4		; preds = %0
  br label %5

; <label>:5      		; preds = %3, %4
  %d.0 = phi i32 [ 1, %3 ], [ 3, %4 ]
  ret i32 %d.0
}
