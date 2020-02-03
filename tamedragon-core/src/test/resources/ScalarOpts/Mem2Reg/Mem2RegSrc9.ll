define i32 @foo(i32 %a, i32 %b, i32 %c, i32 %d, i32 %e) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  %m = alloca i32, align 4
  %n = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 %c, i32* %3, align 4
  store i32 %d, i32* %4, align 4
  store i32 %e, i32* %5, align 4
  store i32 5, i32* %m, align 4
  store i32 10, i32* %n, align 4
  %6 = load i32, i32* %4, align 4
  %7 = load i32, i32* %5, align 4
  %8 = icmp sgt i32 %6, %7
  br i1 %8, label %9, label %13

; <label>:9                                       ; preds = %0
  %10 = load i32, i32* %m, align 4
  %11 = load i32, i32* %n, align 4
  %12 = add i32 %10, %11
  store i32 %12, i32* %1, align 4
  br label %17

; <label>:13                                      ; preds = %0
  %14 = load i32, i32* %n, align 4
  %15 = load i32, i32* %m, align 4
  %16 = sub i32 %14, %15
  store i32 %16, i32* %1, align 4
  br label %17

; <label>:17                                      ; preds = %13, %9
  %18 = load i32, i32* %4, align 4
  %19 = icmp sgt i32 %18, 10
  br i1 %19, label %20, label %26

; <label>:20                                      ; preds = %17
  %21 = load i32, i32* %1, align 4
  %22 = load i32, i32* %m, align 4
  %23 = add i32 %21, %22
  %24 = load i32, i32* %n, align 4
  %25 = add i32 %23, %24
  store i32 %25, i32* %1, align 4
  br label %26

; <label>:26                                      ; preds = %20, %17
  %27 = load i32, i32* %1, align 4
  %28 = sub i32 %27, 10
  store i32 %28, i32* %1, align 4
  store i32 20, i32* %2, align 4
  br label %29

; <label>:29                                      ; preds = %35, %26
  %30 = load i32, i32* %2, align 4
  %31 = icmp slt i32 %30, 30
  br i1 %31, label %32, label %38

; <label>:32                                      ; preds = %29
  %33 = load i32, i32* %3, align 4
  %34 = add i32 %33, 1
  store i32 %34, i32* %3, align 4
  br label %35

; <label>:35                                      ; preds = %32
  %36 = load i32, i32* %2, align 4
  %37 = add i32 %36, 1
  store i32 %37, i32* %2, align 4
  br label %29

; <label>:38                                      ; preds = %29
  %39 = load i32, i32* %3, align 4
  %40 = icmp slt i32 %39, 10
  br i1 %40, label %41, label %42

; <label>:41                                      ; preds = %38
  store i32 10, i32* %3, align 4
  br label %42

; <label>:42                                      ; preds = %41, %38
  %43 = load i32, i32* %1, align 4
  %44 = icmp slt i32 %43, 40
  br i1 %44, label %45, label %46

; <label>:45                                      ; preds = %42
  store i32 20, i32* %1, align 4
  br label %46

; <label>:46                                      ; preds = %45, %42
  %47 = load i32, i32* %1, align 4
  %48 = load i32, i32* %2, align 4
  %49 = add i32 %47, %48
  %50 = load i32, i32* %3, align 4
  %51 = add i32 %49, %50
  ret i32 %51
}