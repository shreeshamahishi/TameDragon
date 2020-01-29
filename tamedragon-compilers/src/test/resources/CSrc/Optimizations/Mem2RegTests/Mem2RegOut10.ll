define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = icmp ne i32 %a, 0
  br i1 %1, label %4, label %2

; <label>:2		; preds = %0
  %3 = icmp ne i32 %b, 0
  br label %4

; <label>:4       		; preds = %0, %2
  %5 = phi i1 [ true, %0 ], [ %3, %2 ]
  %6 = zext i1 %5 to i32
  %7 = mul i32 %6, 20
  %8 = add i32 %6, 10
  %9 = icmp sgt i32 %8, 100
  br i1 %9, label %10, label %11

; <label>:10		; preds = %4
  br label %12

; <label>:11		; preds = %4
  br label %12

; <label>:12     		; preds = %10, %11
  %d.0 = phi i32 [ 1, %10 ], [ 2, %11 ]
  ret i32 %d.0
}
