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

; <label>:14                		; preds = %0, %28
  %middle.0 = phi i32 [ %13, %0 ], [ %30, %28 ]
  %last.0 = phi i32 [ %11, %0 ], [ %last.1, %28 ]
  %first.0 = phi i32 [ 0, %0 ], [ %first.1, %28 ]
  %15 = icmp sle i32 %first.0, %last.0
  br i1 %15, label %16, label %31

; <label>:16                                                        		; preds = %14
  %17 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 %middle.0
  %18 = load i32, i32* %17, align 4
  %19 = icmp slt i32 %18, 6
  br i1 %19, label %20, label %22

; <label>:20		; preds = %16
  %21 = add i32 %middle.0, 1
  br label %28

; <label>:22    		; preds = %16
  %23 = icmp eq i32 %18, 6
  br i1 %23, label %24, label %25

; <label>:24		; preds = %22
  br label %31

; <label>:25		; preds = %22
  %26 = sub i32 %middle.0, 1
  br label %27

; <label>:27		; preds = %25
  br label %28

; <label>:28                  		; preds = %20, %27
  %last.1 = phi i32 [ %last.0, %20 ], [ %26, %27 ]
  %first.1 = phi i32 [ %21, %20 ], [ %first.0, %27 ]
  %29 = add i32 %first.1, %last.1
  %30 = sdiv i32 %29, 2
  br label %14

; <label>:31		; preds = %14, %24
  ret i32 0
}