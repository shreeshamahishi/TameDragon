define i32 @foo(i32 %a, i32 %b) nounwind {
  br label %1

; <label>:1         		; preds = %0, %14
  %i.0 = phi i32 [ 0, %0 ], [ %15, %14 ]
  %d.0 = phi i32 [ 98, %0 ], [ %13, %14 ]
  %2 = icmp slt i32 %i.0, %a
  br i1 %2, label %3, label %16

; <label>:3   		; preds = %1
  %4 = add i32 %i.0, 43
  %5 = icmp sgt i32 %b, %4
  br i1 %5, label %6, label %9

; <label>:6		; preds = %3
  %7 = add i32 %a, %i.0
  %8 = add i32 %d.0, %7
  br label %12

; <label>:9		; preds = %3
  %10 = mul i32 %b, %i.0
  %11 = add i32 %d.0, %10
  br label %12

; <label>:12        		; preds = %6, %9
  %d.1 = phi i32 [ %8, %6 ], [ %11, %9 ]
  %13 = add i32 %d.1, 3
  br label %14

; <label>:14		; preds = %12
  %15 = add i32 %i.0, 1
  br label %1

; <label>:16		; preds = %1
  %17 = sub i32 %d.0, 4
  ret i32 %17
}
