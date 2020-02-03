define void @foo() nounwind {
  %1 = icmp slt i32 10, 10
  br i1 %1, label %2, label %4

; <label>:2		; preds = %0
  %3 = add i32 10, 1
  br label %11

; <label>:4   		; preds = %0
  %5 = icmp sgt i32 10, 10
  br i1 %5, label %6, label %8

; <label>:6		; preds = %4
  %7 = sub i32 10, 1
  br label %10

; <label>:8		; preds = %4
  %9 = add i32 10, 1
  br label %10

; <label>:10		; preds = %8, %6
  br label %11

; <label>:11		; preds = %10, %2
  ret void
}
