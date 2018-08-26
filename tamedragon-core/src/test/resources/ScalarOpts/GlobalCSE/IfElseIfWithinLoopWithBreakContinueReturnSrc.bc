define void @foo() nounwind {
  br label %1

; <label>:1        		; preds = %17, %0
  %a.0 = phi i32 [ %18, %17 ], [ 0, %0 ]
  %2 = icmp slt i32 %a.0, 10
  br i1 %2, label %3, label %19

; <label>:3   		; preds = %1
  %4 = icmp slt i32 %a.0, 10
  br i1 %4, label %5, label %6

; <label>:5		; preds = %3
  br label %19

; <label>:6   		; preds = %3
  %7 = icmp sgt i32 %a.0, 10
  br i1 %7, label %8, label %9

; <label>:8		; preds = %6
  br label %17

; <label>:9      		; preds = %6
  %10 = icmp eq i32 %a.0, 10
  br i1 %10, label %11, label %12

; <label>:11		; preds = %9
  br label %19

; <label>:12		; preds = %9
  %13 = add i32 %a.0, 1
  br label %14

; <label>:14		; preds = %12
  br label %15

; <label>:15		; preds = %14
  br label %16

; <label>:16		; preds = %15
  br label %17

; <label>:17          		; preds = %16, %8
  %a.1 = phi i32 [ %13, %16 ], [ %a.0, %8 ]
  %18 = add i32 %a.1, 1
  br label %1

; <label>:19		; preds = %11, %5, %1
  ret void
}