define void @foo(i32 %p1, i32 %flag) nounwind {
  br label %1

; <label>:1            		; preds = %14, %0
  %i.0 = phi i32 [ %15, %14 ], [ undef, %0 ]
  %.0 = phi i32 [ %.2, %14 ], [ %p1, %0 ]
  %2 = icmp slt i32 %i.0, 10
  br i1 %2, label %3, label %16

; <label>:3   		; preds = %1
  %4 = icmp ne i32 %flag, 0
  br i1 %4, label %5, label %7

; <label>:5		; preds = %3
  %6 = add i32 %.0, 1
  br label %14

; <label>:7    		; preds = %3
  %8 = icmp slt i32 %flag, 2
  br i1 %8, label %9, label %11

; <label>:9		; preds = %7
  %10 = sub i32 %.0, 1
  br label %13

; <label>:11		; preds = %7
  %12 = add i32 %.0, 2
  br label %13

; <label>:13        		; preds = %11, %9
  %.1 = phi i32 [ %12, %11 ], [ %10, %9 ]
  br label %14

; <label>:14       		; preds = %13, %5
  %.2 = phi i32 [ %.1, %13 ], [ %6, %5 ]
  %15 = add i32 %i.0, 1
  br label %1

; <label>:16		; preds = %1
  ret void
}