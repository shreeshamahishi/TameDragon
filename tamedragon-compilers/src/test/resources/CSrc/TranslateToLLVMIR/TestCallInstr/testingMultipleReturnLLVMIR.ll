define void @foo() nounwind {
  %a = alloca i32, align 4
  store i32 10, i32* %a, align 4
  %1 = load i32, i32* %a, align 4
  %2 = icmp slt i32 %1, 10
  br i1 %2, label %3, label %4

; <label>:3		; preds = %0
  br label %5

; <label>:4		; preds = %0
  br label %5

; <label>:5		; preds = %3, %4
  ret void
}
