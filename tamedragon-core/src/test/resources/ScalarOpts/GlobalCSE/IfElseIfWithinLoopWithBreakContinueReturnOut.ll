define void @foo() nounwind {
  br label %1

; <label>:1                                       ; preds = %16, %0
  %a.0 = phi i32 [ 0, %0 ], [ %17, %16 ]
  %2 = icmp slt i32 %a.0, 10
  br i1 %2, label %3, label %18

; <label>:3                                       ; preds = %1
  br i1 %2, label %4, label %5

; <label>:4                                       ; preds = %3
  br label %18

; <label>:5                                       ; preds = %3
  %6 = icmp sgt i32 %a.0, 10
  br i1 %6, label %7, label %8

; <label>:7                                       ; preds = %5
  br label %16

; <label>:8                                       ; preds = %5
  %9 = icmp eq i32 %a.0, 10
  br i1 %9, label %10, label %11

; <label>:10                                      ; preds = %8
  br label %18

; <label>:11                                      ; preds = %8
  %12 = add i32 %a.0, 1
  br label %13

; <label>:13                                      ; preds = %11
  br label %14

; <label>:14                                      ; preds = %13
  br label %15

; <label>:15                                      ; preds = %14
  br label %16

; <label>:16                                      ; preds = %15, %7
  %a.1 = phi i32 [ %a.0, %7 ], [ %12, %15 ]
  %17 = add i32 %a.1, 1
  br label %1

; <label>:18                                      ; preds = %10, %4, %1
  ret void
}