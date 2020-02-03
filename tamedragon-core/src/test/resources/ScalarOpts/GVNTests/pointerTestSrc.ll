define i32 @foo(i32 %m) nounwind {
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  %1 = add i32 %m, 10
  store i32 %1, i32* %a, align 4
  %2 = add i32 %m, 10
  store i32 %2, i32* %b, align 4
  %3 = icmp ne i32 %m, 0
  br i1 %3, label %4, label %5

; <label>:4		; preds = %0
  br label %6

; <label>:5		; preds = %0
  br label %6

; <label>:6         		; preds = %5, %4
  %r.0 = phi i32* [ %b, %5 ], [ %a, %4 ]
  %7 = load i32, i32* %r.0, align 4
  ret i32 %7
}