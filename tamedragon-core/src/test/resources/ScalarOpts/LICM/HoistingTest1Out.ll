define i32 @foobar(i32 %a, i32 %b, i32 %c) nounwind {
  %m = alloca [5 x i32], align 16
  %1 = add i32 %a, %b
  br label %2

; <label>:2                           		; preds = %5, %0
  %i.0 = phi i32 [ %3, %5 ], [ undef, %0 ]
  %3 = add i32 %i.0, 1
  %4 = getelementptr inbounds [5 x i32], [5 x i32]* %m, i32 0, i32 %3
  store i32 %1, i32* %4, align 4
  br label %5

; <label>:5   		; preds = %2
  %6 = icmp slt i32 %3, %a
  br i1 %6, label %2, label %7

; <label>:7		; preds = %5
  ret i32 %1
}