define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca i32, align 4
  %e = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 3, i32* %c, align 4
  store i32 6, i32* %d, align 4
  %3 = load i32, i32* %1, align 4
  %4 = load i32, i32* %c, align 4
  %5 = add  i32 %3, %4
  %6 = load i32, i32* %2, align 4
  %7 = load i32, i32* %d, align 4
  %8 = add  i32 %6, %7
  %9 = icmp sgt i32 %5, %8
  br i1 %9, label %10, label %24

; <label>:10                                      ; preds = %0
  %11 = load i32, i32* %1, align 4
  %12 = mul  i32 21, %11
  store i32 %12, i32* %d, align 4
  %13 = load i32, i32* %c, align 4
  %14 = add  i32 %13, 4
  store i32 %14, i32* %1, align 4
  %15 = load i32, i32* %d, align 4
  %16 = add  i32 %15, 3
  store i32 %16, i32* %2, align 4
  %17 = load i32, i32* %1, align 4
  %18 = add  i32 %17, 4
  store i32 %18, i32* %c, align 4
  %19 = load i32, i32* %1, align 4
  %20 = load i32, i32* %2, align 4
  %21 = add  i32 %19, %20
  %22 = load i32, i32* %c, align 4
  %23 = add  i32 %21, %22
  store i32 %23, i32* %d, align 4
  br label %34

; <label>:24                                      ; preds = %0
  %25 = load i32, i32* %2, align 4
  %26 = mul  i32 68, %25
  store i32 %26, i32* %d, align 4
  %27 = load i32, i32* %2, align 4
  %28 = add  i32 45, %27
  store i32 %28, i32* %1, align 4
  %29 = load i32, i32* %1, align 4
  %30 = load i32, i32* %c, align 4
  %31 = add  i32 %29, %30
  %32 = load i32, i32* %d, align 4
  %33 = add  i32 %32, %31
  store i32 %33, i32* %d, align 4
  br label %34

; <label>:34                                      ; preds = %24, %10
  %35 = load i32, i32* %d, align 4
  %36 = load i32, i32* %1, align 4
  %37 = add  i32 %35, %36
  store i32 %37, i32* %e, align 4
  %38 = load i32, i32* %d, align 4
  ret i32 %38
}