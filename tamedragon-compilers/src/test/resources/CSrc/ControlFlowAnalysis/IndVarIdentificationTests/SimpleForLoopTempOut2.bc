define i32 @foo(i32 %a) nounwind uwtable {
  %arr = alloca [3 x i32], align 4
  %1 = getelementptr inbounds [3 x i32]* %arr, i32 0, i64 0
  store i32 1, i32* %1, align 4
  %2 = getelementptr inbounds [3 x i32]* %arr, i32 0, i64 1
  store i32 2, i32* %2, align 4
  %3 = getelementptr inbounds [3 x i32]* %arr, i32 0, i64 2
  store i32 3, i32* %3, align 4
  br label %4

; <label>:4                                       ; preds = %10, %0
  %lsr.iv3 = phi i32 [ %lsr.iv.next, %10 ], [ -1, %0 ]
  %lsr.iv = phi [3 x i32]* [ %5, %10 ], [ %arr, %0 ]
  %result.0 = phi i32 [ %9, %10 ], [ undef, %0 ]
  %lsr.iv2 = bitcast [3 x i32]* %lsr.iv to i32*
  %lsr.iv1 = bitcast [3 x i32]* %lsr.iv to i8*
  %uglygep = getelementptr i8* %lsr.iv1, i64 ptrtoint (i32* getelementptr (i32* null, i32 1) to i64)
  %5 = bitcast i8* %uglygep to [3 x i32]*
  %lsr.iv.next = add i32 %lsr.iv3, 1
  %6 = icmp slt i32 %lsr.iv.next, %a
  br i1 %6, label %7, label %11

; <label>:7                                       ; preds = %4
  %8 = load i32* %lsr.iv2, align 4
  %9 = add i32 %result.0, %8
  br label %10

; <label>:10                                      ; preds = %7
  br label %4

; <label>:11                                      ; preds = %4
  %result.0.lcssa = phi i32 [ %result.0, %4 ]
  ret i32 %result.0.lcssa
}
