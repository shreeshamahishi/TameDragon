@g = common global i32 0, align 4

define i32 @foo(i32 %m) nounwind {
  %1 = icmp eq i32 %m, 10
  br i1 %1, label %2, label %5

; <label>:2      		; preds = %0
  %3 = load i32, i32* @g, align 4
  %4 = add i32 %3, 3
  br label %8

; <label>:5      		; preds = %0
  %6 = load i32, i32* @g, align 4
  %7 = add i32 %6, 3
  br label %8

; <label>:8           		; preds = %2, %5
  %b.0 = phi i32 [ undef, %2 ], [ %7, %5 ]
  %a.0 = phi i32 [ %4, %2 ], [ undef, %5 ]
  %9 = icmp ne i32 %m, 0
  br i1 %9, label %10, label %11

; <label>:10		; preds = %8
  br label %12

; <label>:11		; preds = %8
  br label %12

; <label>:12          		; preds = %10, %11
  %.0 = phi i32 [ %a.0, %10 ], [ %b.0, %11 ]
  ret i32 %.0
}
