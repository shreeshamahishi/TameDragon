define void @foo(i32 %p1, i32 %p2, i32 %p3, i32 %flag) nounwind {
  %1 = icmp ne i32 %flag, 0
  br i1 %1, label %split1, label %split2

; <label>:split1		; preds = %0
  br label %4

; <label>:split2		; preds = %0
  br label %14

; <label>:4     		; preds = %split1, %12
  %i.0 = phi i32 [ %13, %12 ], [ 0, %0 ]
  %.02 = phi i32 [ %11, %12 ], [ %p3, %0 ]
  %.01 = phi i32 [ %.1, %12 ], [ %p2, %0 ]
  %.0 = phi i32 [ %7, %12 ], [ %p1, %0 ]
  %5 = icmp slt i32 %i.0, 10
  br i1 %5, label %6, label %29

; <label>:6      		; preds = %4
  %7 = add i32 %.0, 1
  br i1 true, label %8, label %10

; <label>:8		; preds = %6
  %9 = add i32 %.01, 1
  br label %10

; <label>:10        		; preds = %8, %6
  %.1 = phi i32 [ %9, %8 ], [ %.01, %6 ]
  %11 = add i32 %.02, 1
  br label %12

; <label>:12		; preds = %10
  %13 = add i32 %i.0, 1
  br label %4

; <label>:14   		; preds = %20, %split2
  %15 = phi i32 [ %21, %20 ], [ 0, %0 ]
  %16 = phi i32 [ %24, %20 ], [ %p3, %0 ]
  %17 = phi i32 [ %23, %20 ], [ %p2, %0 ]
  %18 = phi i32 [ %28, %20 ], [ %p1, %0 ]
  %19 = icmp slt i32 %15, 10
  br i1 %19, label %27, label %29

; <label>:20		; preds = %22
  %21 = add i32 %15, 1
  br label %14

; <label>:22        		; preds = %25, %27
  %23 = phi i32 [ %26, %25 ], [ %17, %27 ]
  %24 = add i32 %16, 1
  br label %20

; <label>:25		; preds = %27
  %26 = add i32 %17, 1
  br label %22

; <label>:27      		; preds = %14
  %28 = add i32 %18, 1
  br i1 false, label %25, label %22

; <label>:29		; preds = %14, %4
  ret void
}