define i32 @foobar(i32 %a, i32 %b, i32 %c) nounwind {
  %m = alloca [5 x i32], align 16
  br label %1

; <label>:1                           		; preds = %5, %0
  %i.0 = phi i32 [ %2, %5 ], [ undef, %0 ]
  %2 = add i32 %i.0, 1
  %3 = add i32 %a, %b
  %4 = getelementptr inbounds [5 x i32], [5 x i32]* %m, i32 0, i32 %2
  store i32 %3, i32* %4, align 4
  br label %5

; <label>:5   		; preds = %1
  %6 = icmp slt i32 %2, %a
  br i1 %6, label %1, label %7

; <label>:7		; preds = %5
  ret i32 %3
}