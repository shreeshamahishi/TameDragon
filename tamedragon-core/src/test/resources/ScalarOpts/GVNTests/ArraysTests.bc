define i32* @fun(i32* %m, i32 %x) nounwind {
  %1 = icmp ne i32 %x, 0
  br i1 %1, label %2, label %3

; <label>:2		; preds = %0
  br label %4

; <label>:3		; preds = %0
  br label %4

; <label>:4         		; preds = %3, %2
  %c.0 = phi i32* [ %m, %3 ], [ %m, %2 ]
  ret i32* %c.0
}
