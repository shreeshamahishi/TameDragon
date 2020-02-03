define i32 @nestLoop(i32 %a, i32 %b) nounwind {
  br label %1

; <label>:1		; preds = %9, %0
  %2 = add i32 %a, 10
  br label %3

; <label>:3        		; preds = %6, %1
  %x.0 = phi i32 [ %5, %6 ], [ %2, %1 ]
  %4 = add i32 %a, 10
  %5 = add i32 %x.0, %4
  br label %6

; <label>:6   		; preds = %3
  %7 = icmp slt i32 %a, 20
  br i1 %7, label %3, label %8

; <label>:8		; preds = %6
  br label %9

; <label>:9     		; preds = %8
  %10 = icmp slt i32 %b, 20
  br i1 %10, label %1, label %11

; <label>:11		; preds = %9
  ret i32 %5
}