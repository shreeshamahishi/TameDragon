define i32 @foobar(i32 %a, i32 %b, i32 %c) nounwind {
  %m = alloca [5 x i32], align 16
  br label %1

; <label>:1       		; preds = %6, %0
  %i.0 = phi i32 [ %7, %6 ], [ 0, %0 ]
  %t.0 = phi i32 [ %4, %6 ], [ 0, %0 ]
  %2 = icmp slt i32 %i.0, %a
  br i1 %2, label %3, label %8

; <label>:3     		; preds = %1
  %4 = add i32 %a, %b
  %5 = getelementptr inbounds [5 x i32], [5 x i32]* %m, i32 0, i32 %i.0
  store i32 %4, i32* %5, align 4
  br label %6

; <label>:6		; preds = %3
  %7 = add i32 %i.0, 1
  br label %1

; <label>:8		; preds = %1
  ret i32 %t.0
}