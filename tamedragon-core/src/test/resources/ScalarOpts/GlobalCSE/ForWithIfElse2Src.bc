define i32 @foo(i32 %m, i32 %n) nounwind {
  %1 = add i32 %m, %n
  %2 = icmp sgt i32 %1, 20
  br i1 %2, label %3, label %5

; <label>:3		; preds = %0
  %4 = add i32 34, 10
  br label %13

; <label>:5		; preds = %0
  br label %6

; <label>:6        		; preds = %10, %5
  %x.0 = phi i32 [ %9, %10 ], [ 21, %5 ]
  %j.0 = phi i32 [ %11, %10 ], [ 0, %5 ]
  %7 = icmp slt i32 %j.0, 30
  br i1 %7, label %8, label %12

; <label>:8		; preds = %6
  %9 = add i32 %x.0, 2
  br label %10

; <label>:10		; preds = %8
  %11 = add i32 %j.0, 1
  br label %6

; <label>:12		; preds = %6
  br label %13

; <label>:13         		; preds = %12, %3
  %x.1 = phi i32 [ %x.0, %12 ], [ 21, %3 ]
  ret i32 %x.1
}