define void @foo(i32 %p1, i32 %flag) nounwind {
  %1 = icmp slt i32 %flag, 10
  br i1 %1, label %split1, label %split2

; <label>:split1		; preds = %0
  br label %4

; <label>:split2		; preds = %0
  br label %9

; <label>:4   		; preds = %split1, %7
  %i.0 = phi i32 [ %8, %7 ], [ 0, %0 ]
  %.0 = phi i32 [ %6, %7 ], [ %p1, %0 ]
  br i1 true, label %5, label %16

; <label>:5		; preds = %4
  %6 = add i32 %.0, 1
  br label %7

; <label>:7		; preds = %5
  %8 = add i32 %i.0, 1
  br label %4

; <label>:9    		; preds = %12, %split2
  %10 = phi i32 [ %13, %12 ], [ 0, %0 ]
  %11 = phi i32 [ %15, %12 ], [ %p1, %0 ]
  br i1 false, label %14, label %16

; <label>:12		; preds = %14
  %13 = add i32 %10, 1
  br label %9

; <label>:14		; preds = %9
  %15 = add i32 %11, 1
  br label %12

; <label>:16		; preds = %9, %4
  ret void
}