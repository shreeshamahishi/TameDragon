define void @foo(i32 %p1, i32 %flag) nounwind {
  %1 = icmp ne i32 %flag, 0
  %2 = icmp slt i32 %flag, 6
  br label %3

; <label>:3            		; preds = %30, %0
  %i.0 = phi i32 [ %31, %30 ], [ undef, %0 ]
  %.0 = phi i32 [ %.2, %30 ], [ %p1, %0 ]
  %4 = icmp slt i32 %i.0, 10
  br i1 %4, label %5, label %32

; <label>:5   		; preds = %3
  br i1 %1, label %6, label %8

; <label>:6		; preds = %5
  %7 = add i32 %.0, 1
  br label %8

; <label>:8         		; preds = %6, %5
  %.1 = phi i32 [ %7, %6 ], [ %.0, %5 ]
  br i1 %2, label %split1, label %split2

; <label>:split1		; preds = %8
  br label %11

; <label>:split2		; preds = %8
  br label %19

; <label>:11   		; preds = %split1, %17
  %j.0 = phi i32 [ %18, %17 ], [ 0, %8 ]
  %.2 = phi i32 [ %.3, %17 ], [ %.1, %8 ]
  %12 = icmp slt i32 %j.0, 10
  br i1 %12, label %13, label %30

; <label>:13     		; preds = %11
  br i1 true, label %14, label %16

; <label>:14		; preds = %13
  %15 = add i32 %.2, 1
  br label %16

; <label>:16        		; preds = %14, %13
  %.3 = phi i32 [ %15, %14 ], [ %.2, %13 ]
  br label %17

; <label>:17		; preds = %16
  %18 = add i32 %j.0, 1
  br label %11

; <label>:19   		; preds = %23, %split2
  %20 = phi i32 [ %24, %23 ], [ 0, %8 ]
  %21 = phi i32 [ %26, %23 ], [ %.1, %8 ]
  %22 = icmp slt i32 %20, 10
  br i1 %22, label %29, label %30

; <label>:23		; preds = %25
  %24 = add i32 %20, 1
  br label %19

; <label>:25        		; preds = %27, %29
  %26 = phi i32 [ %28, %27 ], [ %21, %29 ]
  br label %23

; <label>:27		; preds = %29
  %28 = add i32 %21, 1
  br label %25

; <label>:29      		; preds = %19
  br i1 false, label %27, label %25

; <label>:30		; preds = %19, %11
  %31 = add i32 %i.0, 1
  br label %3

; <label>:32		; preds = %3
  ret void
}