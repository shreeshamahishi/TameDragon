define void @foo(i32 %a, i32 %b) nounwind {
  br label %1

; <label>:1                  		; preds = %3, %9, %0
  %i.0 = phi i32 [ %10, %9 ], [ %i.0, %3 ], [ 0, %0 ]
  %2 = icmp slt i32 %i.0, 10
  br i1 %2, label %3, label %11

; <label>:3   		; preds = %1
  %4 = icmp eq i32 %i.0, 2
  br i1 %4, label %1, label %5

; <label>:5    		; preds = %3
  %6 = icmp eq i32 %i.0, 1
  br i1 %6, label %11, label %7

; <label>:7    		; preds = %5
  %8 = icmp eq i32 %i.0, 6
  br i1 %8, label %11, label %9

; <label>:9		; preds = %7
  %10 = add i32 %i.0, 1
  br label %1

; <label>:11		; preds = %7, %5, %1
  ret void
}
