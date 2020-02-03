define i32 @nestLoopInvOuter(i32 %a, i32 %b) nounwind {
  br label %1

; <label>:1           		; preds = %9, %0
  %y.0 = phi i32 [ %5, %9 ], [ 0, %0 ]
  %x.0 = phi i32 [ %3, %9 ], [ undef, %0 ]
  %2 = add i32 %a, 10
  %3 = add i32 %x.0, %2
  br label %4

; <label>:4          		; preds = %6, %1
  %y.1 = phi i32 [ %5, %6 ], [ %y.0, %1 ]
  %5 = add i32 %y.1, 1
  br label %6

; <label>:6   		; preds = %4
  %7 = icmp slt i32 %5, 20
  br i1 %7, label %4, label %8

; <label>:8		; preds = %6
  br label %9

; <label>:9     		; preds = %8
  %10 = icmp slt i32 %b, 20
  br i1 %10, label %1, label %11

; <label>:11		; preds = %9
  %12 = add i32 %3, %5
  ret i32 %12
}
