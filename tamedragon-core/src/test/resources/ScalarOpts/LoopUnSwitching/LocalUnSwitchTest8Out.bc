define void @foo(i32 %p1, i32 %flag) nounwind {
  %1 = icmp ne i32 %flag, 0
  br i1 %1, label %split1, label %split2

; <label>:split1		; preds = %0
  br label %4

; <label>:split2		; preds = %0
  br label %11

; <label>:4       		; preds = %split1, %9
  %i.0 = phi i32 [ %10, %9 ], [ undef, %0 ]
  %.0 = phi i32 [ %.1, %9 ], [ %p1, %0 ]
  %5 = icmp slt i32 %i.0, 10
  br i1 %5, label %6, label %21

; <label>:6     		; preds = %4
  br i1 true, label %7, label %9

; <label>:7		; preds = %6
  %8 = add i32 %.0, 1
  br label %9

; <label>:9        		; preds = %7, %6
  %.1 = phi i32 [ %8, %7 ], [ %.0, %6 ]
  %10 = add i32 %i.0, 1
  br label %4

; <label>:11     		; preds = %15, %split2
  %12 = phi i32 [ %17, %15 ], [ undef, %0 ]
  %13 = phi i32 [ %16, %15 ], [ %p1, %0 ]
  %14 = icmp slt i32 %12, 10
  br i1 %14, label %20, label %21

; <label>:15        		; preds = %20, %18
  %16 = phi i32 [ %19, %18 ], [ %13, %20 ]
  %17 = add i32 %12, 1
  br label %11

; <label>:18		; preds = %20
  %19 = add i32 %13, 1
  br label %15

; <label>:20      		; preds = %11
  br i1 false, label %18, label %15

; <label>:21		; preds = %11, %4
  ret void
}