define i32 @foo(i32 %a, i32 %b) nounwind {
  br label %1

; <label>:1        		; preds = %9, %0
  %k.0 = phi i32 [ %6, %9 ], [ 0, %0 ]
  %i.0 = phi i32 [ %10, %9 ], [ 0, %0 ]
  %2 = icmp slt i32 %i.0, 10
  br i1 %2, label %3, label %11

; <label>:3    		; preds = %1
  %4 = icmp slt i32 %i.0, 2
  br i1 %4, label %11, label %5

; <label>:5          		; preds = %3, %7
  %k.1 = phi i32 [ %6, %7 ], [ %k.0, %3 ]
  %6 = add i32 %k.1, 1
  br label %7

; <label>:7   		; preds = %5
  %8 = icmp slt i32 %6, 10
  br i1 %8, label %5, label %9

; <label>:9		; preds = %7
  %10 = add i32 %i.0, 1
  br label %1

; <label>:11		; preds = %3, %1
  ret i32 %k.0
}