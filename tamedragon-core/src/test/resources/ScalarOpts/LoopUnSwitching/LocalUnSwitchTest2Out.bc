define void @foo(i32 %p1, i32 %p2, i32 %p3, i32 %p4, i32 %flag) nounwind {
  %1 = icmp ne i32 %flag, 0
  br i1 %1, label %split1, label %split2

; <label>:split1		; preds = %0
  br label %4

; <label>:split2		; preds = %0
  br label %20

; <label>:4     		; preds = %split1, %18
  %i.0 = phi i32 [ 0, %0 ], [ %19, %18 ]
  %.03 = phi i32 [ %p4, %0 ], [ %.2, %18 ]
  %.02 = phi i32 [ %p3, %0 ], [ %17, %18 ]
  %.01 = phi i32 [ %p2, %0 ], [ %.1, %18 ]
  %.0 = phi i32 [ %p1, %0 ], [ %7, %18 ]
  %5 = icmp slt i32 %i.0, 10
  br i1 %5, label %6, label %44

; <label>:6      		; preds = %4
  %7 = add i32 %.0, 1
  br i1 true, label %8, label %16

; <label>:8      		; preds = %6
  %9 = add i32 %.01, 1
  %10 = icmp slt i32 %i.0, 6
  br i1 %10, label %11, label %13

; <label>:11		; preds = %8
  %12 = add i32 %.03, 1
  br label %15

; <label>:13		; preds = %8
  %14 = sub i32 %.03, 1
  br label %15

; <label>:15         		; preds = %13, %11
  %.14 = phi i32 [ %12, %11 ], [ %14, %13 ]
  br label %16

; <label>:16          		; preds = %15, %6
  %.2 = phi i32 [ %.14, %15 ], [ %.03, %6 ]
  %.1 = phi i32 [ %9, %15 ], [ %.01, %6 ]
  %17 = add i32 %.02, 1
  br label %18

; <label>:18		; preds = %16
  %19 = add i32 %i.0, 1
  br label %4

; <label>:20   		; preds = %27, %split2
  %21 = phi i32 [ 0, %0 ], [ %28, %27 ]
  %22 = phi i32 [ %p4, %0 ], [ %30, %27 ]
  %23 = phi i32 [ %p3, %0 ], [ %32, %27 ]
  %24 = phi i32 [ %p2, %0 ], [ %31, %27 ]
  %25 = phi i32 [ %p1, %0 ], [ %36, %27 ]
  %26 = icmp slt i32 %21, 10
  br i1 %26, label %35, label %44

; <label>:27		; preds = %29
  %28 = add i32 %21, 1
  br label %20

; <label>:29        		; preds = %33, %35
  %30 = phi i32 [ %34, %33 ], [ %22, %35 ]
  %31 = phi i32 [ %42, %33 ], [ %24, %35 ]
  %32 = add i32 %23, 1
  br label %27

; <label>:33        		; preds = %39, %37
  %34 = phi i32 [ %40, %39 ], [ %38, %37 ]
  br label %29

; <label>:35      		; preds = %20
  %36 = add i32 %25, 1
  br i1 false, label %41, label %29

; <label>:37		; preds = %41
  %38 = sub i32 %22, 1
  br label %33

; <label>:39		; preds = %41
  %40 = add i32 %22, 1
  br label %33

; <label>:41    		; preds = %35
  %42 = add i32 %24, 1
  %43 = icmp slt i32 %21, 6
  br i1 %43, label %39, label %37

; <label>:44		; preds = %20, %4
  ret void
}