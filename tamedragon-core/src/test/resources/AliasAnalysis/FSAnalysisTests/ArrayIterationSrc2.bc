define i32 @foo(i32 %MAX, i32* %arr) nounwind {
  br label %1

; <label>:1             		; preds = %3, %0
  %result.0 = phi i32 [ %15, %3 ], [ 3, %0 ]
  %count.0 = phi i32 [ %9, %3 ], [ 0, %0 ]
  %p3.0 = phi i32* [ %8, %3 ], [ %arr, %0 ]
  %p2.0 = phi i32* [ %5, %3 ], [ %arr, %0 ]
  %p1.0 = phi i32* [ %4, %3 ], [ %arr, %0 ]
  %2 = icmp slt i32 %count.0, %MAX
  br i1 %2, label %3, label %16

; <label>:3                    		; preds = %1
  %4 = getelementptr inbounds i32* %p1.0, i32 1
  %5 = getelementptr inbounds i32* %p2.0, i32 1
  %6 = load i32* %p3.0, align 4
  %7 = add i32 %6, 2
  %8 = inttoptr i32 %7 to i32*
  %9 = add i32 %count.0, 1
  %10 = load i32* %4, align 4
  %11 = load i32* %5, align 4
  %12 = add i32 %10, %11
  %13 = load i32* %8, align 4
  %14 = add i32 %12, %13
  %15 = add i32 %result.0, %14
  br label %1

; <label>:16     		; preds = %1
  %17 = load i32* %4, align 4
  %18 = icmp slt i32 %17, 20
  br i1 %18, label %19, label %21

; <label>:19		; preds = %16
  %20 = add i32 %result.0, 1
  br label %21

; <label>:21                    		; preds = %19, %16
  %result.1 = phi i32 [ %20, %19 ], [ %result.0, %16 ]
  %22 = load i32* %5, align 4
  %23 = icmp slt i32 %22, 30
  br i1 %23, label %24, label %26

; <label>:24		; preds = %21
  %25 = add i32 %result.1, 2
  br label %26

; <label>:26                    		; preds = %24, %21
  %result.2 = phi i32 [ %25, %24 ], [ %result.1, %21 ]
  %27 = load i32* %8, align 4
  %28 = icmp slt i32 %27, 40
  br i1 %28, label %29, label %31

; <label>:29		; preds = %26
  %30 = add i32 %result.2, 3
  br label %31

; <label>:31                    		; preds = %29, %26
  %result.3 = phi i32 [ %30, %29 ], [ %result.2, %26 ]
  ret i32 %result.3
}