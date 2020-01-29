define i32 @bar(i32 %a, i32 %b) nounwind uwtable {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %m = alloca i32, align 4
  %n = alloca i32, align 4
  %d = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 1, i32* %m, align 4
  store i32 2, i32* %n, align 4
  store i32 1, i32* %d, align 4
  %3 = load i32* %m, align 4
  %4 = load i32* %n, align 4
  %5 = icmp sgt i32 %3, %4
  br i1 %5, label %6, label %15

; <label>:6      		; preds = %0
  %7 = load i32* %1, align 4
  %8 = load i32* %2, align 4
  %9 = add i32 %7, %8
  %10 = icmp ne i32 %9, 0
  br i1 %10, label %11, label %14

; <label>:11     		; preds = %6
  %12 = load i32* %d, align 4
  %13 = add i32 %12, 10
  store i32 %13, i32* %d, align 4
  br label %14

; <label>:14		; preds = %11, %6
  br label %26

; <label>:15     		; preds = %0
  %16 = load i32* %1, align 4
  %17 = load i32* %n, align 4
  %18 = add i32 %16, %17
  %19 = load i32* %2, align 4
  %20 = load i32* %m, align 4
  %21 = add i32 %19, %20
  %22 = icmp sgt i32 %18, %21
  br i1 %22, label %23, label %24

; <label>:23  		; preds = %15
  store i32 3, i32* %d, align 4
  br label %25

; <label>:24  		; preds = %15
  store i32 4, i32* %d, align 4
  br label %25

; <label>:25		; preds = %24, %23
  br label %26

; <label>:26		; preds = %25, %14
  %27 = load i32* %d, align 4
  ret i32 %27
}