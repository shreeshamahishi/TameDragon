define i32 @foo() nounwind {
  br label %1

; <label>:1         		; preds = %0, %7
  %k.0 = phi i32 [ 0, %0 ], [ %k.1, %7 ]
  %2 = icmp slt i32 %k.0, 100
  br i1 %2, label %3, label %8

; <label>:3     		; preds = %1
  br i1 true, label %4, label %6

; <label>:4		; preds = %3
  %5 = add i32 %k.0, 1
  br label %7

; <label>:6		; preds = %3
  br label %7

; <label>:7           		; preds = %4, %6
  %k.1 = phi i32 [ %5, %4 ], [ undef, %6 ]
  br label %1

; <label>:8		; preds = %1
  ret i32 1
}
