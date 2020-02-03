define i32 @nestLoop(i32 %a, i32 %b) nounwind {
  %1 = add i32 %a, 10
  %2 = add i32 %a, 10
  %3 = icmp slt i32 %a, 20
  %4 = icmp slt i32 %b, 20
  br label %5

; <label>:5		; preds = %10, %0
  br label %6

; <label>:6        		; preds = %8, %5
  %x.0 = phi i32 [ %7, %8 ], [ %1, %5 ]
  %7 = add i32 %x.0, %2
  br label %8

; <label>:8   		; preds = %6
  br i1 %3, label %6, label %9

; <label>:9		; preds = %8
  br label %10

; <label>:10   		; preds = %9
  br i1 %4, label %5, label %11

; <label>:11		; preds = %10
  ret i32 %7
}