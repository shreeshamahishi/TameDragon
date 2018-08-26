define i32 @nestLoop(i32 %a, i32 %b) nounwind {
  br label %1

; <label>:1       		; preds = %10, %0
  %.0 = phi i32 [ %9, %10 ], [ %a, %0 ]
  %2 = add i32 %.0, 10
  br label %3

; <label>:3        		; preds = %6, %1
  %x.0 = phi i32 [ %5, %6 ], [ %2, %1 ]
  %4 = add i32 %.0, 10
  %5 = add i32 %x.0, %4
  br label %6

; <label>:6   		; preds = %3
  %7 = icmp slt i32 %.0, 20
  br i1 %7, label %3, label %8

; <label>:8		; preds = %6
  %9 = add i32 %.0, 1
  br label %10

; <label>:10    		; preds = %8
  %11 = icmp slt i32 %b, 20
  br i1 %11, label %1, label %12

; <label>:12		; preds = %10
  %13 = add i32 %5, 0
  ret i32 %13
}