define void @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca i32, align 4
  %e = alloca i32, align 4
  %i = alloca i32, align 4
  %f = alloca [10 x i32], align 4
  %g = alloca [10 x i32], align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = load i32* %1, align 4
  %4 = load i32* %2, align 4
  %5 = add nsw i32 %3, %4
  store i32 %5, i32* %c, align 4
  %6 = load i32* %1, align 4
  %7 = load i32* %c, align 4
  %8 = mul nsw i32 %6, %7
  store i32 %8, i32* %d, align 4
  %9 = load i32* %d, align 4
  %10 = load i32* %d, align 4
  %11 = mul nsw i32 %9, %10
  store i32 %11, i32* %e, align 4
  store i32 1, i32* %i, align 4
  br label %12

; <label>:12                                      ; preds = %39, %0
  %13 = load i32* %i, align 4
  %14 = icmp slt i32 %13, 10
  br i1 %14, label %15, label %42

; <label>:15                                      ; preds = %12
  %16 = load i32* %1, align 4
  %17 = load i32* %2, align 4
  %18 = add nsw i32 %16, %17
  %19 = load i32* %i, align 4
  %20 = getelementptr inbounds [10 x i32]* %f, i32 0, i32 %19
  store i32 %18, i32* %20
  %21 = load i32* %c, align 4
  %22 = mul nsw i32 %21, 2
  store i32 %22, i32* %c, align 4
  %23 = load i32* %c, align 4
  %24 = load i32* %d, align 4
  %25 = icmp sgt i32 %23, %24
  br i1 %25, label %26, label %32

; <label>:26                                      ; preds = %15
  %27 = load i32* %c, align 4
  %28 = load i32* %1, align 4
  %29 = mul nsw i32 %27, %28
  %30 = load i32* %i, align 4
  %31 = getelementptr inbounds [10 x i32]* %g, i32 0, i32 %30
  store i32 %29, i32* %31
  br label %38

; <label>:32                                      ; preds = %15
  %33 = load i32* %d, align 4
  %34 = load i32* %d, align 4
  %35 = mul nsw i32 %33, %34
  %36 = load i32* %i, align 4
  %37 = getelementptr inbounds [10 x i32]* %g, i32 0, i32 %36
  store i32 %35, i32* %37
  br label %38

; <label>:38                                      ; preds = %32, %26
  br label %39

; <label>:39                                      ; preds = %38
  %40 = load i32* %i, align 4
  %41 = add nsw i32 %40, 1
  store i32 %41, i32* %i, align 4
  br label %12

; <label>:42                                      ; preds = %12
  ret void
}