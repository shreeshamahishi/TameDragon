define void @foo() nounwind {
  %i = alloca i32, align 4
  %C = alloca [2 x i8], align 1
  %D = alloca [2 x i8], align 1
  %A = alloca [10 x i8], align 1
  store i32 0, i32* %i, align 4
  br label %1

; <label>:1 		; preds = %0, %14
  %2 = load i32, i32* %i, align 4
  %3 = icmp ne i32 %2, 10
  br i1 %3, label %4, label %17

; <label>:4                                        		; preds = %1
  %5 = load i32, i32* %i, align 4
  %6 = getelementptr inbounds [10 x i8], [10 x i8]* %A, i32 0, i32 %5
  %7 = getelementptr inbounds [2 x i8], [2 x i8]* %C, i32 0, i32 0
  %8 = load i8, i8* %6, align 1
  store i8 %8, i8* %7, align 1
  %9 = load i32, i32* %i, align 4
  %10 = sub i32 9, %9
  %11 = getelementptr inbounds [10 x i8], [10 x i8]* %A, i32 0, i32 %10
  %12 = getelementptr inbounds [2 x i8], [2 x i8]* %C, i32 0, i32 1
  %13 = load i8, i8* %11, align 1
  store i8 %13, i8* %12, align 1
  br label %14

; <label>:14      		; preds = %4
  %15 = load i32, i32* %i, align 4
  %16 = add i32 %15, 1
  store i32 %16, i32* %i, align 4
  br label %1

; <label>:17   		; preds = %1
  store i32 0, i32* %i, align 4
  br label %18

; <label>:18		; preds = %17, %34
  %19 = load i32, i32* %i, align 4
  %20 = icmp ne i32 %19, 10
  br i1 %20, label %21, label %37

; <label>:21                                      		; preds = %18
  %22 = load i32, i32* %i, align 4
  %23 = getelementptr inbounds [10 x i8], [10 x i8]* %A, i32 0, i32 %22
  %24 = getelementptr inbounds [2 x i8], [2 x i8]* %D, i32 0, i32 0
  %25 = bitcast i8* %24 to i16*
  %26 = getelementptr inbounds i16, i16* %25, i32 0
  %27 = load i8, i8* %23, align 1
  %28 = sext i8 %27 to i16
  store i16 %28, i16* %26, align 2
  %29 = load i32, i32* %i, align 4
  %30 = sub i32 9, %29
  %31 = getelementptr inbounds [10 x i8], [10 x i8]* %A, i32 0, i32 %30
  %32 = getelementptr inbounds [2 x i8], [2 x i8]* %D, i32 0, i32 1
  %33 = load i8, i8* %31, align 1
  store i8 %33, i8* %32, align 1
  br label %34

; <label>:34     		; preds = %21
  %35 = load i32, i32* %i, align 4
  %36 = add i32 %35, 1
  store i32 %36, i32* %i, align 4
  br label %18

; <label>:37		; preds = %18
  ret void
}
