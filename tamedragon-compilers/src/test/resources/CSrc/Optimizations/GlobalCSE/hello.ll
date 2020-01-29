define void @foo(i32 %a, i32 %b) nounwind {
  %f = alloca [10 x i32], align 16
  %g = alloca [10 x i32], align 16
  %1 = add i32 %a, %b
  %2 = mul i32 %a, %1
  br label %3

; <label>:3        		; preds = %0, %16
  %i.0 = phi i32 [ 1, %0 ], [ %17, %16 ]
  %c.0 = phi i32 [ %1, %0 ], [ %7, %16 ]
  %4 = icmp slt i32 %i.0, 10
  br i1 %4, label %5, label %18

; <label>:5                                              		; preds = %3
  %6 = getelementptr inbounds [10 x i32], [10 x i32]* %f, i32 0, i32 %i.0
  store i32 %1, i32* %6, align 4
  %7 = mul i32 %c.0, 2
  %8 = icmp sgt i32 %7, %2
  br i1 %8, label %9, label %12

; <label>:9       		; preds = %5
  %10 = mul i32 %7, %a
  %11 = getelementptr inbounds [10 x i32], [10 x i32]* %g, i32 0, i32 %i.0
  store i32 %10, i32* %11, align 4
  br label %15

; <label>:12      		; preds = %5
  %13 = mul i32 %2, %2
  %14 = getelementptr inbounds [10 x i32], [10 x i32]* %g, i32 0, i32 %i.0
  store i32 %13, i32* %14, align 4
  br label %15

; <label>:15		; preds = %9, %12
  br label %16

; <label>:16		; preds = %15
  %17 = add i32 %i.0, 1
  br label %3

; <label>:18		; preds = %3
  ret void
}
