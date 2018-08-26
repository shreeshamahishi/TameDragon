define void @foo() nounwind {
  br label %1

; <label>:1                                       ; preds = %14, %0
  %a.0 = phi i32 [ 0, %0 ], [ %15, %14 ]
  %2 = icmp slt i32 %a.0, 10
  br i1 %2, label %3, label %16

; <label>:3                                       ; preds = %1
  br i1 %2, label %4, label %6

; <label>:4                                       ; preds = %3
  %5 = add i32 %a.0, 1
  br label %13

; <label>:6                                       ; preds = %3
  %7 = icmp sgt i32 %a.0, 10
  br i1 %7, label %8, label %10

; <label>:8                                       ; preds = %6
  %9 = sub i32 %a.0, 1
  br label %12

; <label>:10                                      ; preds = %6
  %11 = add i32 %a.0, 1
  br label %12

; <label>:12                                      ; preds = %10, %8
  %a.1 = phi i32 [ %9, %8 ], [ %11, %10 ]
  br label %13

; <label>:13                                      ; preds = %12, %4
  %a.2 = phi i32 [ %5, %4 ], [ %a.1, %12 ]
  br label %14

; <label>:14                                      ; preds = %13
  %15 = add i32 %a.2, 1
  br label %1

; <label>:16                                      ; preds = %1
  ret void
}