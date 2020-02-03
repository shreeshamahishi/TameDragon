define i32 @foobar(i32 %a, i32 %b, i32 %c) nounwind {
  %m = alloca [5 x i32], align 16
  %1 = add i32 %a, %b
  %2 = getelementptr inbounds [5 x i32], [5 x i32]* %m, i32 0, i32 5
  br label %3

; <label>:3                           		; preds = %6, %0
  %i.0 = phi i32 [ %4, %6 ], [ undef, %0 ]
  %4 = add i32 %i.0, 1
  %5 = getelementptr inbounds [5 x i32], [5 x i32]* %m, i32 0, i32 %4
  store i32 %1, i32* %5, align 4
  store i32 0, i32* %2, align 4
  br label %6

; <label>:6   		; preds = %3
  %7 = icmp slt i32 %4, %a
  br i1 %7, label %3, label %8

; <label>:8		; preds = %6
  ret i32 undef
}