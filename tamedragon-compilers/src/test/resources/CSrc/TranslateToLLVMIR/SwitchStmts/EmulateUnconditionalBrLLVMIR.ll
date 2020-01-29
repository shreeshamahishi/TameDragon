define void @foo() nounwind {
  %a = alloca i32, align 4
  store i32 10, i32* %a, align 4
  %1 = load i32, i32* %a, align 4
  switch i32 %1, label %2 []

; <label>:2		; preds = %0
  ret void
}
