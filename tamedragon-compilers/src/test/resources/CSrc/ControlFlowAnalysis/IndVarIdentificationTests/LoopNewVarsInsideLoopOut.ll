define i32 @bar(i32 %max_val, i32 %j, i32 %addr) nounwind {
  %1 = icmp sgt i32 %max_val, 0
  %smax = select i1 %1, i32 %max_val, i32 0
  %2 = add i32 %j, -101
  %3 = mul i32 %j, 4
  %4 = add i32 %addr, %3
  %5 = add i32 %4, -404
  %6 = mul i32 %2, %5
  %7 = mul i32 %6, 4
  %8 = add i32 %7, -101
  %9 = mul i32 %j, 3200
  %10 = mul i32 %addr, 400
  %11 = add i32 %9, %10
  %12 = add i32 %11, -163100
  %13 = zext i32 %smax to i33
  %14 = add i32 %smax, -1
  %15 = zext i32 %14 to i33
  %16 = mul i33 %13, %15
  %17 = lshr i33 %16, 1
  %18 = trunc i33 %17 to i32
  %19 = mul i32 %12, %18
  %20 = add i32 %smax, -2
  %21 = zext i32 %20 to i33
  %22 = mul i33 %16, %21
  %23 = lshr i33 %22, 1
  %24 = trunc i33 %23 to i32
  %25 = mul i32 %24, 1431762432
  br label %26

; <label>:26                                      ; preds = %29, %0
  %index.0 = phi i32 [ %30, %29 ], [ 0, %0 ]
  %27 = icmp slt i32 %index.0, %max_val
  br i1 %27, label %28, label %31

; <label>:28                                      ; preds = %26
  br label %29

; <label>:29                                      ; preds = %28
  %30 = add i32 %index.0, 1
  br label %26

; <label>:31                                      ; preds = %26
  %32 = mul i32 %smax, %8
  %33 = add i32 %32, %19
  %34 = add i32 %33, %25
  ret i32 %34
}
