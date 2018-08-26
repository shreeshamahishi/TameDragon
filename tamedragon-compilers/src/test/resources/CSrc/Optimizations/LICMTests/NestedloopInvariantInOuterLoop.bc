define i32 @nestLoopInvOuter(i32 %a, i32 %b) nounwind {
  %1 = add i32 %a, 10
  %2 = icmp slt i32 %b, 20
  br label %3

; <label>:3           		; preds = %10, %0
  %y.0 = phi i32 [ %6, %10 ], [ 0, %0 ]
  %x.0 = phi i32 [ %4, %10 ], [ undef, %0 ]
  %4 = add i32 %x.0, %1
  br label %5

; <label>:5          		; preds = %7, %3
  %y.1 = phi i32 [ %6, %7 ], [ %y.0, %3 ]
  %6 = add i32 %y.1, 1
  br label %7

; <label>:7   		; preds = %5
  %8 = icmp slt i32 %6, 20
  br i1 %8, label %5, label %9

; <label>:9		; preds = %7
  br label %10

; <label>:10   		; preds = %9
  br i1 %2, label %3, label %11

; <label>:11		; preds = %10
  %12 = add i32 %4, %6
  ret i32 %12
}