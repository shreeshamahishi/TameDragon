define void @foo(i32 %a, i32 %b) nounwind {
  %f = alloca [10 x i32], align 4
  %g = alloca [10 x i32], align 4
  %1 = add nsw i32 %a, %b
  %2 = mul nsw i32 %a, %1
  %3 = mul nsw i32 %2, %2
  br label %4

; <label>:4                                       ; preds = %18, %0
  %i.0 = phi i32 [ 1, %0 ], [ %19, %18 ]
  %c.0 = phi i32 [ %1, %0 ], [ %9, %18 ]
  %5 = icmp slt i32 %i.0, 10
  br i1 %5, label %6, label %20

; <label>:6                                       ; preds = %4
  %7 = add nsw i32 %a, %b
  %8 = getelementptr inbounds [10 x i32], [10 x i32]* %f, i32 0, i32 %i.0
  store i32 %7, i32* %8
  %9 = mul nsw i32 %c.0, 2
  %10 = icmp sgt i32 %9, %2
  br i1 %10, label %11, label %14

; <label>:11                                      ; preds = %6
  %12 = mul nsw i32 %9, %a
  %13 = getelementptr inbounds [10 x i32], [10 x i32]* %g, i32 0, i32 %i.0
  store i32 %12, i32* %13
  br label %17

; <label>:14                                      ; preds = %6
  %15 = mul nsw i32 %2, %2
  %16 = getelementptr inbounds [10 x i32], [10 x i32]* %g, i32 0, i32 %i.0
  store i32 %15, i32* %16
  br label %17

; <label>:17                                      ; preds = %14, %11
  br label %18

; <label>:18                                      ; preds = %17
  %19 = add nsw i32 %i.0, 1
  br label %4

; <label>:20                                      ; preds = %4
  ret void
}
