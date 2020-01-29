define void @foo() nounwind {
  %1 = icmp slt i32 10, 10
  br i1 %1, label %2, label %3

; <label>:2		; preds = %0
  br label %8

; <label>:3   		; preds = %0
  %4 = icmp sgt i32 10, 10
  br i1 %4, label %5, label %6

; <label>:5		; preds = %3
  br label %7

; <label>:6		; preds = %3
  br label %7

; <label>:7		; preds = %5, %6
  br label %8

; <label>:8		; preds = %2, %7
  ret void
}
