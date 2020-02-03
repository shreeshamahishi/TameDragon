define void @foo() nounwind {
  br label %1

; <label>:1        		; preds = %11, %0
  %a.0 = phi i32 [ %12, %11 ], [ 0, %0 ]
  %2 = icmp slt i32 %a.0, 10
  br i1 %2, label %3, label %13

; <label>:3    		; preds = %1
  %4 = icmp slt i32 %a.0, 10
  br i1 %4, label %13, label %5

; <label>:5    		; preds = %3
  %6 = icmp sgt i32 %a.0, 10
  br i1 %6, label %11, label %7

; <label>:7    		; preds = %5
  %8 = icmp eq i32 %a.0, 10
  br i1 %8, label %13, label %9

; <label>:9		; preds = %7
  %10 = add i32 %a.0, 1
  br label %11

; <label>:11          		; preds = %9, %5
  %a.1 = phi i32 [ %10, %9 ], [ %a.0, %5 ]
  %12 = add i32 %a.1, 1
  br label %1

; <label>:13		; preds = %7, %3, %1
  ret void
}