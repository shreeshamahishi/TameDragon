define i32 @main() nounwind {
  %array = alloca [100 x i32], align 16
  %1 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 0
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 1
  store i32 1, i32* %2, align 4
  %3 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 2
  store i32 2, i32* %3, align 4
  %4 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 3
  store i32 3, i32* %4, align 4
  %5 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 4
  store i32 4, i32* %5, align 4
  %6 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 5
  store i32 5, i32* %6, align 4
  %7 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 6
  store i32 6, i32* %7, align 4
  %8 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 7
  store i32 7, i32* %8, align 4
  %9 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 8
  store i32 8, i32* %9, align 4
  %10 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 9
  store i32 9, i32* %10, align 4
  %11 = sub i32 10, 1
  %12 = add i32 0, %11
  %13 = sdiv i32 %12, 2
  br label %14

; <label>:14                		; preds = %0, %30
  %middle.0 = phi i32 [ %13, %0 ], [ %32, %30 ]
  %last.0 = phi i32 [ %11, %0 ], [ %last.1, %30 ]
  %first.0 = phi i32 [ 0, %0 ], [ %first.1, %30 ]
  %15 = icmp sle i32 %first.0, %last.0
  br i1 %15, label %16, label %33

; <label>:16                                                        		; preds = %14
  %17 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 %middle.0
  %18 = load i32, i32* %17, align 4
  %19 = icmp slt i32 %18, 6
  br i1 %19, label %20, label %22

; <label>:20		; preds = %16
  %21 = add i32 %middle.0, 1
  br label %30

; <label>:22                                                        		; preds = %16
  %23 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 %middle.0
  %24 = load i32, i32* %23, align 4
  %25 = icmp eq i32 %24, 6
  br i1 %25, label %26, label %27

; <label>:26		; preds = %22
  br label %33

; <label>:27		; preds = %22
  %28 = sub i32 %middle.0, 1
  br label %29

; <label>:29		; preds = %27
  br label %30

; <label>:30                  		; preds = %20, %29
  %last.1 = phi i32 [ %last.0, %20 ], [ %28, %29 ]
  %first.1 = phi i32 [ %21, %20 ], [ %first.0, %29 ]
  %31 = add i32 %first.1, %last.1
  %32 = sdiv i32 %31, 2
  br label %14

; <label>:33		; preds = %14, %26
  ret i32 0
}
