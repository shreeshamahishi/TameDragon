define i32 @foo(i32 %a, i32 %b, i32 %c, i32 %d, i32 %e) nounwind {
  %1 = icmp sgt i32 %d, %e
  br i1 %1, label %2, label %4

; <label>:2		; preds = %0
  %3 = add i32 5, 10
  br label %6

; <label>:4		; preds = %0
  %5 = sub i32 10, 5
  br label %6

; <label>:6       		; preds = %2, %4
  %.0 = phi i32 [ %3, %2 ], [ %5, %4 ]
  %7 = icmp sgt i32 %d, 10
  br i1 %7, label %8, label %11

; <label>:8		; preds = %6
  %9 = add i32 %.0, 5
  %10 = add i32 %9, 10
  br label %11

; <label>:11        		; preds = %6, %8
  %.1 = phi i32 [ %.0, %6 ], [ %10, %8 ]
  %12 = sub i32 %.1, 10
  br label %13

; <label>:13        		; preds = %11, %17
  %.02 = phi i32 [ %c, %11 ], [ %16, %17 ]
  %.01 = phi i32 [ 20, %11 ], [ %18, %17 ]
  %14 = icmp slt i32 %.01, 30
  br i1 %14, label %15, label %19

; <label>:15		; preds = %13
  %16 = add i32 %.02, 1
  br label %17

; <label>:17		; preds = %15
  %18 = add i32 %.01, 1
  br label %13

; <label>:19    		; preds = %13
  %20 = icmp slt i32 %.02, 10
  br i1 %20, label %21, label %22

; <label>:21		; preds = %19
  br label %22

; <label>:22         		; preds = %19, %21
  %.13 = phi i32 [ %.02, %19 ], [ 10, %21 ]
  %23 = icmp slt i32 %12, 40
  br i1 %23, label %24, label %25

; <label>:24		; preds = %22
  br label %25

; <label>:25       		; preds = %22, %24
  %.2 = phi i32 [ %12, %22 ], [ 20, %24 ]
  %26 = add i32 %.2, %.01
  %27 = add i32 %26, %.13
  ret i32 %27
}
