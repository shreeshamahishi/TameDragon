define i32 @addingZero(i32 %p) nounwind {
  %1 = icmp eq i32 10, 10
  br i1 %1, label %2, label %3

; <label>:2		; preds = %0
  br label %3

; <label>:3		; preds = %2, %0
  ret i32 %p
}