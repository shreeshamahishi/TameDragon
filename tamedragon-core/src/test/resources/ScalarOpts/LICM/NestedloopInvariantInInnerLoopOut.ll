define i32 @nestLoop(i32 %a, i32 %b) nounwind {
  %1 = icmp slt i32 %b, 20
  br label %2

; <label>:2        		; preds = %11, %0
  %.0 = phi i32 [ %10, %11 ], [ %a, %0 ]
  %3 = add i32 %.0, 10
  %4 = add i32 %.0, 10
  %5 = icmp slt i32 %.0, 20
  br label %6

; <label>:6        		; preds = %8, %2
  %x.0 = phi i32 [ %7, %8 ], [ %3, %2 ]
  %7 = add i32 %x.0, %4
  br label %8

; <label>:8   		; preds = %6
  br i1 %5, label %6, label %9

; <label>:9		; preds = %8
  %10 = add i32 %.0, 1
  br label %11

; <label>:11   		; preds = %9
  br i1 %1, label %2, label %12

; <label>:12		; preds = %11
  %13 = add i32 %7, 0
  ret i32 %13
}