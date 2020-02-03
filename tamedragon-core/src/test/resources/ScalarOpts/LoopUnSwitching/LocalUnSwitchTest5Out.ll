define void @foo(i32 %p1, i32 %p2, i32 %p3, i32 %p4, i32 %flag) nounwind {
  %1 = icmp ne i32 %flag, 0
  %2 = icmp slt i32 %flag, 6
  br label %3

; <label>:3          		; preds = %32, %0
  %i.0 = phi i32 [ %33, %32 ], [ 0, %0 ]
  %.02 = phi i32 [ %31, %32 ], [ %p3, %0 ]
  %.01 = phi i32 [ %.3, %32 ], [ %p2, %0 ]
  %.0 = phi i32 [ %6, %32 ], [ %p1, %0 ]
  %4 = icmp slt i32 %i.0, 10
  br i1 %4, label %5, label %34

; <label>:5    		; preds = %3
  %6 = add i32 %.0, 1
  br i1 %1, label %7, label %30

; <label>:7             		; preds = %5
  br i1 %2, label %split1, label %split2

; <label>:split1		; preds = %7
  br label %10

; <label>:split2		; preds = %7
  br label %18

; <label>:10    		; preds = %split1, %16
  %j.0 = phi i32 [ %17, %16 ], [ 0, %7 ]
  %.1 = phi i32 [ %.2, %16 ], [ %.01, %7 ]
  %11 = icmp slt i32 %j.0, 10
  br i1 %11, label %12, label %29

; <label>:12     		; preds = %10
  br i1 true, label %13, label %15

; <label>:13		; preds = %12
  %14 = add i32 %.1, 1
  br label %15

; <label>:15        		; preds = %13, %12
  %.2 = phi i32 [ %14, %13 ], [ %.1, %12 ]
  br label %16

; <label>:16		; preds = %15
  %17 = add i32 %j.0, 1
  br label %10

; <label>:18    		; preds = %22, %split2
  %19 = phi i32 [ %23, %22 ], [ 0, %7 ]
  %20 = phi i32 [ %25, %22 ], [ %.01, %7 ]
  %21 = icmp slt i32 %19, 10
  br i1 %21, label %28, label %29

; <label>:22		; preds = %24
  %23 = add i32 %19, 1
  br label %18

; <label>:24        		; preds = %26, %28
  %25 = phi i32 [ %27, %26 ], [ %20, %28 ]
  br label %22

; <label>:26		; preds = %28
  %27 = add i32 %20, 1
  br label %24

; <label>:28      		; preds = %18
  br i1 false, label %26, label %24

; <label>:29		; preds = %18, %10
  br label %30

; <label>:30         		; preds = %29, %5
  %.3 = phi i32 [ %.1, %29 ], [ %.01, %5 ]
  %31 = add i32 %.02, 1
  br label %32

; <label>:32		; preds = %30
  %33 = add i32 %i.0, 1
  br label %3

; <label>:34		; preds = %3
  ret void
}