define i32 @foo(i32 %a) nounwind uwtable {
  %arr = alloca [3 x i32], align 4
  %1 = getelementptr inbounds [3 x i32]* %arr, i32 0, i64 0
  store i32 1, i32* %1, align 4
  %2 = getelementptr inbounds [3 x i32]* %arr, i32 0, i64 1
  store i32 2, i32* %2, align 4
  %3 = getelementptr inbounds [3 x i32]* %arr, i32 0, i64 2
  store i32 3, i32* %3, align 4
  br label %4

; <label>:4                                       ; preds = %11, %0
  %indvars.iv = phi i64 [ %indvars.iv.next, %11 ], [ 0, %0 ]
  %result.0 = phi i32 [ %10, %11 ], [ undef, %0 ]
  %5 = trunc i64 %indvars.iv to i32
  %6 = icmp slt i32 %5, %a
  br i1 %6, label %7, label %12

; <label>:7                                       ; preds = %4
  %8 = getelementptr inbounds [3 x i32]* %arr, i32 0, i64 %indvars.iv
  %9 = load i32* %8, align 4
  %10 = add i32 %result.0, %9
  br label %11

; <label>:11                                      ; preds = %7
  %indvars.iv.next = add i64 %indvars.iv, 1
  br label %4

; <label>:12                                      ; preds = %4
  %result.0.lcssa = phi i32 [ %result.0, %4 ]
  ret i32 %result.0.lcssa
}
