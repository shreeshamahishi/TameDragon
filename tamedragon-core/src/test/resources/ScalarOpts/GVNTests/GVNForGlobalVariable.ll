@g = common global i32 0, align 4

define i32 @foo(i32 %m) nounwind {
  %1 = icmp eq i32 %m, 10
  br i1 %1, label %2, label %7

; <label>:2     		; preds = %0
  %3 = load i32, i32* @g, align 4
  %4 = add i32 %3, 3
  %5 = load i32, i32* @g, align 4
  %6 = add i32 %5, 1
  store i32 %6, i32* @g, align 4
  br label %10

; <label>:7 		; preds = %0
  %8 = load i32, i32* @g, align 4
  %9 = add i32 %8, 3
  br label %10

; <label>:10          		; preds = %7, %2
  %b.0 = phi i32 [ %9, %7 ], [ undef, %2 ]
  %a.0 = phi i32 [ undef, %7 ], [ %4, %2 ]
  %11 = icmp ne i32 %m, 0
  br i1 %11, label %12, label %13

; <label>:12		; preds = %10
  br label %14

; <label>:13		; preds = %10
  br label %14

; <label>:14          		; preds = %13, %12
  %.0 = phi i32 [ %b.0, %13 ], [ %a.0, %12 ]
  ret i32 %.0
}
