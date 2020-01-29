define i32 @foo(i32 %a) nounwind uwtable {
  %arr = alloca [3 x i32], align 4
  %1 = getelementptr inbounds [3 x i32]* %arr, i32 0, i64 0
  store i32 1, i32* %1, align 4
  %2 = getelementptr inbounds [3 x i32]* %arr, i32 0, i64 1
  store i32 2, i32* %2, align 4
  %3 = getelementptr inbounds [3 x i32]* %arr, i32 0, i64 2
  store i32 3, i32* %3, align 4
  br label %4

; <label>:4                 		; preds = %11, %0
  %result.0 = phi i32 [ %10, %11 ], [ undef, %0 ]
  %b.0 = phi i32 [ %12, %11 ], [ 0, %0 ]
  %5 = icmp slt i32 %b.0, %a
  br i1 %5, label %6, label %13

; <label>:6  		; preds = %4
  %7 = sext i32 %b.0 to i64
  %8 = getelementptr inbounds [3 x i32]* %arr, i32 0, i64 %7
  %9 = load i32* %8, align 4
  %10 = add i32 %result.0, %9
  br label %11

; <label>:11		; preds = %6
  %12 = add i32 %b.0, 1
  br label %4

; <label>:13		; preds = %4
  ret i32 %result.0
}