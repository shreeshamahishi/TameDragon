define void @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %c = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = load i32, i32* %1, align 4
  %4 = load i32, i32* %2, align 4
  %5 = icmp slt i32 %3, %4
  br i1 %5, label %6, label %8

; <label>:6      		; preds = %0
  %7 = load i32, i32* %1, align 4
  store i32 %7, i32* %c, align 4
  br label %10

; <label>:8      		; preds = %0
  %9 = load i32, i32* %2, align 4
  store i32 %9, i32* %c, align 4
  br label %10

; <label>:10		; preds = %6, %8
  ret void
}
