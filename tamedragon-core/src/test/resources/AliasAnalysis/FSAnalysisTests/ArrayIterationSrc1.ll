@MAX = global i32 300, align 4
@split1 = external global i32
@split2 = external global i32

define i32 @foo(i32 %m, i32* %arr) nounwind {
  br label %1

; <label>:1              		; preds = %20, %0
  %result.0 = phi i32 [ %28, %20 ], [ 65, %0 ]
  %count.0 = phi i32 [ %29, %20 ], [ 0, %0 ]
  %p3.0 = phi i32* [ %6, %20 ], [ %arr, %0 ]
  %p2.0 = phi i32* [ %5, %20 ], [ %arr, %0 ]
  %p1.0 = phi i32* [ %4, %20 ], [ %arr, %0 ]
  %2 = icmp slt i32 %count.0, %m
  br i1 %2, label %3, label %30

; <label>:3                    		; preds = %1
  %4 = getelementptr inbounds i32* %p1.0, i32 2
  %5 = getelementptr inbounds i32* %p2.0, i32 1
  %6 = getelementptr inbounds i32* %p3.0, i32 1
  %7 = load i32* @split1, align 4
  %8 = icmp slt i32 %count.0, %7
  br i1 %8, label %9, label %10

; <label>:9		; preds = %3
  br label %10

; <label>:10  		; preds = %9, %3
  %11 = load i32* @split1, align 4
  %12 = icmp sge i32 %count.0, %11
  br i1 %12, label %13, label %16

; <label>:13     		; preds = %10
  %14 = load i32* @split2, align 4
  %15 = icmp slt i32 %count.0, %14
  br label %16

; <label>:16         		; preds = %13, %10
  %17 = phi i1 [ false, %10 ], [ %15, %13 ]
  br i1 %17, label %18, label %19

; <label>:18		; preds = %16
  br label %20

; <label>:19		; preds = %16
  br label %20

; <label>:20          		; preds = %19, %18
  %ptr.0 = phi i32* [ %6, %19 ], [ %5, %18 ]
  %21 = load i32* %ptr.0, align 4
  %22 = load i32* %4, align 4
  %23 = add i32 %21, %22
  %24 = load i32* %5, align 4
  %25 = add i32 %23, %24
  %26 = load i32* %6, align 4
  %27 = add i32 %25, %26
  %28 = add i32 %result.0, %27
  %29 = add i32 %count.0, 1
  br label %1

; <label>:30		; preds = %1
  ret i32 undef
}
