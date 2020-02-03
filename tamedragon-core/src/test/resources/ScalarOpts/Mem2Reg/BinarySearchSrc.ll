define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %c = alloca i32, align 4
  %first = alloca i32, align 4
  %last = alloca i32, align 4
  %middle = alloca i32, align 4
  %n = alloca i32, align 4
  %search = alloca i32, align 4
  %array = alloca [100 x i32], align 16
  store i32 0, i32* %1, align 4
  store i32 10, i32* %n, align 4
  %2 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 0
  store i32 0, i32* %2, align 4
  %3 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 1
  store i32 1, i32* %3, align 4
  %4 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 2
  store i32 2, i32* %4, align 4
  %5 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 3
  store i32 3, i32* %5, align 4
  %6 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 4
  store i32 4, i32* %6, align 4
  %7 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 5
  store i32 5, i32* %7, align 4
  %8 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 6
  store i32 6, i32* %8, align 4
  %9 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 7
  store i32 7, i32* %9, align 4
  %10 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 8
  store i32 8, i32* %10, align 4
  %11 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 9
  store i32 9, i32* %11, align 4
  store i32 6, i32* %search, align 4
  store i32 0, i32* %first, align 4
  %12 = load i32, i32* %n, align 4
  %13 = sub i32 %12, 1
  store i32 %13, i32* %last, align 4
  %14 = load i32, i32* %first, align 4
  %15 = load i32, i32* %last, align 4
  %16 = add i32 %14, %15
  %17 = sdiv i32 %16, 2
  store i32 %17, i32* %middle, align 4
  br label %18

; <label>:18		; preds = %42, %0
  %19 = load i32, i32* %first, align 4
  %20 = load i32, i32* %last, align 4
  %21 = icmp sle i32 %19, %20
  br i1 %21, label %22, label %47

; <label>:22                                     		; preds = %18
  %23 = load i32, i32* %middle, align 4
  %24 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 %23
  %25 = load i32, i32* %search, align 4
  %26 = load i32, i32* %24, align 4
  %27 = icmp slt i32 %26, %25
  br i1 %27, label %28, label %31

; <label>:28        		; preds = %22
  %29 = load i32, i32* %middle, align 4
  %30 = add i32 %29, 1
  store i32 %30, i32* %first, align 4
  br label %42

; <label>:31                                     		; preds = %22
  %32 = load i32, i32* %middle, align 4
  %33 = getelementptr inbounds [100 x i32], [100 x i32]* %array, i32 0, i32 %32
  %34 = load i32, i32* %search, align 4
  %35 = load i32, i32* %33, align 4
  %36 = icmp eq i32 %35, %34
  br i1 %36, label %37, label %38

; <label>:37		; preds = %31
  br label %47

; <label>:38       		; preds = %31
  %39 = load i32, i32* %middle, align 4
  %40 = sub i32 %39, 1
  store i32 %40, i32* %last, align 4
  br label %41

; <label>:41		; preds = %38
  br label %42

; <label>:42    		; preds = %41, %28
  %43 = load i32, i32* %first, align 4
  %44 = load i32, i32* %last, align 4
  %45 = add i32 %43, %44
  %46 = sdiv i32 %45, 2
  store i32 %46, i32* %middle, align 4
  br label %18

; <label>:47		; preds = %37, %18
  ret i32 0
}