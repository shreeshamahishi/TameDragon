define i32 @foo(i32 %m) nounwind uwtable {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca i32, align 4
  %e = alloca i32, align 4
  %f = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  store i32 10, i32* %a, align 4
  store i32 10, i32* %b, align 4
  %3 = load i32* %a, align 4
  %4 = sub i32 %3, 5
  store i32 %4, i32* %c, align 4
  %5 = load i32* %b, align 4
  %6 = sub i32 %5, 5
  store i32 %6, i32* %d, align 4
  %7 = load i32* %c, align 4
  %8 = load i32* %a, align 4
  %9 = add i32 %7, %8
  store i32 %9, i32* %e, align 4
  %10 = load i32* %a, align 4
  %11 = load i32* %d, align 4
  %12 = add i32 %10, %11
  store i32 %12, i32* %f, align 4
  %13 = load i32* %c, align 4
  %14 = load i32* %d, align 4
  %15 = icmp slt i32 %13, %14
  br i1 %15, label %16, label %18

; <label>:16     		; preds = %0
  %17 = load i32* %c, align 4
  store i32 %17, i32* %2, align 4
  br label %41

; <label>:18     		; preds = %0
  %19 = load i32* %d, align 4
  %20 = load i32* %c, align 4
  %21 = icmp eq i32 %19, %20
  br i1 %21, label %22, label %24

; <label>:22    		; preds = %18
  %23 = load i32* %d, align 4
  store i32 %23, i32* %2, align 4
  br label %41

; <label>:24    		; preds = %18
  %25 = load i32* %a, align 4
  %26 = load i32* %b, align 4
  %27 = icmp ne i32 %25, %26
  br i1 %27, label %28, label %30

; <label>:28    		; preds = %24
  %29 = load i32* %e, align 4
  store i32 %29, i32* %2, align 4
  br label %41

; <label>:30    		; preds = %24
  %31 = load i32* %a, align 4
  %32 = load i32* %b, align 4
  %33 = icmp sge i32 %31, %32
  br i1 %33, label %34, label %36

; <label>:34    		; preds = %30
  %35 = load i32* %d, align 4
  store i32 %35, i32* %2, align 4
  br label %41

; <label>:36		; preds = %30
  br label %37

; <label>:37		; preds = %36
  br label %38

; <label>:38		; preds = %37
  br label %39

; <label>:39    		; preds = %38
  %40 = load i32* %a, align 4
  store i32 %40, i32* %2, align 4
  br label %41

; <label>:41		; preds = %39, %34, %28, %22, %16
  %42 = load i32* %2, align 4
  ret i32 %42
}