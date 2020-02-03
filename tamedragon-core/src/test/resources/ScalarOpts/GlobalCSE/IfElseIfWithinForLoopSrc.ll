define void @foo() nounwind {
  br label %1

; <label>:1        		; preds = %15, %0
  %a.0 = phi i32 [ %16, %15 ], [ 0, %0 ]
  %2 = icmp slt i32 %a.0, 10
  br i1 %2, label %3, label %17

; <label>:3   		; preds = %1
  %4 = icmp slt i32 %a.0, 10
  br i1 %4, label %5, label %7

; <label>:5		; preds = %3
  %6 = add i32 %a.0, 1
  br label %14

; <label>:7    		; preds = %3
  %8 = icmp sgt i32 %a.0, 10
  br i1 %8, label %9, label %11

; <label>:9		; preds = %7
  %10 = sub i32 %a.0, 1
  br label %13

; <label>:11		; preds = %7
  %12 = add i32 %a.0, 1
  br label %13

; <label>:13         		; preds = %11, %9
  %a.1 = phi i32 [ %12, %11 ], [ %10, %9 ]
  br label %14

; <label>:14         		; preds = %13, %5
  %a.2 = phi i32 [ %a.1, %13 ], [ %6, %5 ]
  br label %15

; <label>:15		; preds = %14
  %16 = add i32 %a.2, 1
  br label %1

; <label>:17		; preds = %1
  ret void
}