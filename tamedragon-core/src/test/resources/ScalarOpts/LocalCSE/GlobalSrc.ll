@a = global i32 10, align 4

define i32 @foo(i32 %flag) nounwind {
  %1 = load i32, i32* @a, align 4
  %2 = add i32 %1, 1
  %3 = load i32, i32* @a, align 4
  %4 = add i32 %3, 1
  %5 = icmp ne i32 %flag, 0
  br i1 %5, label %6, label %7

; <label>:6		; preds = %0
  br label %8

; <label>:7		; preds = %0
  br label %8

; <label>:8       		; preds = %7, %6
  %.0 = phi i32 [ %4, %7 ], [ %2, %6 ]
  ret i32 %.0
}