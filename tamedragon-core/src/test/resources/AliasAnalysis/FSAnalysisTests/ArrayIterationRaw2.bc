define i32 @foo(i32 %MAX, i32* %arr) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32*, align 8
  %ptr = alloca i32*, align 8
  %p1 = alloca i32*, align 8
  %p2 = alloca i32*, align 8
  %p3 = alloca i32*, align 8
  %count = alloca i32, align 4
  %result = alloca i32, align 4
  store i32 %MAX, i32* %1, align 4
  store i32* %arr, i32** %2, align 8
  %3 = load i32** %2, align 8
  store i32* %3, i32** %p1, align 8
  %4 = load i32** %2, align 8
  store i32* %4, i32** %p2, align 8
  %5 = load i32** %2, align 8
  store i32* %5, i32** %p3, align 8
  store i32 0, i32* %count, align 4
  store i32 3, i32* %result, align 4
  br label %6

; <label>:6		; preds = %10, %0
  %7 = load i32* %count, align 4
  %8 = load i32* %1, align 4
  %9 = icmp slt i32 %7, %8
  br i1 %9, label %10, label %31

; <label>:10          		; preds = %6
  %11 = load i32** %p1, align 8
  %12 = getelementptr inbounds i32* %11, i32 1
  store i32* %12, i32** %p1, align 8
  %13 = load i32** %p2, align 8
  %14 = getelementptr inbounds i32* %13, i32 1
  store i32* %14, i32** %p2, align 8
  %15 = load i32** %p3, align 8
  %16 = load i32* %15, align 4
  %17 = add i32 %16, 2
  %18 = inttoptr i32 %17 to i32*
  store i32* %18, i32** %p3, align 8
  %19 = load i32* %count, align 4
  %20 = add i32 %19, 1
  store i32 %20, i32* %count, align 4
  %21 = load i32** %p3, align 8
  %22 = load i32** %p1, align 8
  %23 = load i32** %p2, align 8
  %24 = load i32* %22, align 4
  %25 = load i32* %23, align 4
  %26 = add i32 %24, %25
  %27 = load i32* %21, align 4
  %28 = add i32 %26, %27
  %29 = load i32* %result, align 4
  %30 = add i32 %29, %28
  store i32 %30, i32* %result, align 4
  br label %6

; <label>:31     		; preds = %6
  %32 = load i32* %22, align 4
  %33 = icmp slt i32 %32, 20
  br i1 %33, label %34, label %37

; <label>:34         		; preds = %31
  %35 = load i32* %result, align 4
  %36 = add i32 %35, 1
  store i32 %36, i32* %result, align 4
  br label %37

; <label>:37		; preds = %34, %31
  %38 = load i32* %23, align 4
  %39 = icmp slt i32 %38, 30
  br i1 %39, label %40, label %43

; <label>:40         		; preds = %37
  %41 = load i32* %result, align 4
  %42 = add i32 %41, 2
  store i32 %42, i32* %result, align 4
  br label %43

; <label>:43		; preds = %40, %37
  %44 = load i32* %21, align 4
  %45 = icmp slt i32 %44, 40
  br i1 %45, label %46, label %49

; <label>:46         		; preds = %43
  %47 = load i32* %result, align 4
  %48 = add i32 %47, 3
  store i32 %48, i32* %result, align 4
  br label %49

; <label>:49		; preds = %46, %43
  %50 = load i32* %result, align 4
  ret i32 %50
}