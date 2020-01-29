define i32 @nestes3loop(i32 %a, i32 %b, i32 %c) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %x = alloca i32, align 4
  %y = alloca i32, align 4
  %z = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 %c, i32* %3, align 4
  store i32 0, i32* %x, align 4
  store i32 0, i32* %y, align 4
  store i32 0, i32* %z, align 4
  br label %4

; <label>:4		; preds = %0, %26
  br label %5

; <label>:5		; preds = %4, %19
  br label %6

; <label>:6  		; preds = %5, %13
  %7 = load i32, i32* %2, align 4
  %8 = add i32 %7, 50
  store i32 %8, i32* %y, align 4
  %9 = load i32, i32* %3, align 4
  %10 = add i32 %9, 50
  store i32 %10, i32* %z, align 4
  %11 = load i32, i32* %1, align 4
  %12 = add i32 %11, 50
  store i32 %12, i32* %x, align 4
  br label %13

; <label>:13      		; preds = %6
  %14 = load i32, i32* %3, align 4
  %15 = icmp slt i32 %14, 15
  br i1 %15, label %6, label %16

; <label>:16     		; preds = %13
  %17 = load i32, i32* %2, align 4
  %18 = add i32 %17, 1
  store i32 %18, i32* %2, align 4
  br label %19

; <label>:19     		; preds = %16
  %20 = load i32, i32* %2, align 4
  %21 = icmp slt i32 %20, 10
  br i1 %21, label %5, label %22

; <label>:22     		; preds = %19
  %23 = load i32, i32* %1, align 4
  %24 = icmp slt i32 %23, 5
  br i1 %24, label %25, label %26

; <label>:25		; preds = %22
  br label %29

; <label>:26     		; preds = %22
  %27 = load i32, i32* %1, align 4
  %28 = add i32 %27, 1
  store i32 %28, i32* %1, align 4
  br label %4

; <label>:29     		; preds = %25
  %30 = load i32, i32* %x, align 4
  %31 = load i32, i32* %y, align 4
  %32 = add i32 %30, %31
  %33 = load i32, i32* %z, align 4
  %34 = add i32 %32, %33
  ret i32 %34
}
