define void @foo(i32 %m, i32* %result) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32*, align 8
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  %c = alloca i32, align 4
  %x = alloca i32, align 4
  %y = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  store i32* %result, i32** %2, align 8
  store i32 40, i32* %c, align 4
  store i32 12, i32* %x, align 4
  store i32 23, i32* %y, align 4
  store i32 3, i32* %a, align 4
  br label %3

; <label>:3 		; preds = %0, %37
  %4 = load i32, i32* %a, align 4
  %5 = load i32, i32* %1, align 4
  %6 = icmp slt i32 %4, %5
  br i1 %6, label %7, label %40

; <label>:7        		; preds = %3
  %8 = load i32*, i32** %2, align 8
  %9 = load i32, i32* %a, align 4
  %10 = load i32, i32* %8, align 4
  %11 = add i32 %10, %9
  store i32 %11, i32* %8, align 4
  br label %12

; <label>:12 		; preds = %7, %31
  %13 = load i32, i32* %x, align 4
  %14 = icmp ne i32 %13, 0
  br i1 %14, label %15, label %36

; <label>:15       		; preds = %12
  %16 = load i32*, i32** %2, align 8
  %17 = load i32, i32* %b, align 4
  %18 = load i32, i32* %16, align 4
  %19 = add i32 %17, %18
  store i32 %19, i32* %b, align 4
  store i32 1, i32* %b, align 4
  br label %20

; <label>:20		; preds = %15, %28
  %21 = load i32, i32* %b, align 4
  %22 = load i32, i32* %1, align 4
  %23 = icmp slt i32 %21, %22
  br i1 %23, label %24, label %31

; <label>:24     		; preds = %20
  %25 = load i32, i32* %y, align 4
  %26 = load i32, i32* %c, align 4
  %27 = add i32 %25, %26
  store i32 %27, i32* %y, align 4
  br label %28

; <label>:28     		; preds = %24
  %29 = load i32, i32* %b, align 4
  %30 = add i32 %29, 1
  store i32 %30, i32* %b, align 4
  br label %20

; <label>:31       		; preds = %20
  %32 = load i32*, i32** %2, align 8
  %33 = load i32, i32* %y, align 4
  %34 = load i32, i32* %32, align 4
  %35 = add i32 %34, %33
  store i32 %35, i32* %32, align 4
  br label %12

; <label>:36		; preds = %12
  br label %37

; <label>:37     		; preds = %36
  %38 = load i32, i32* %a, align 4
  %39 = add i32 %38, 1
  store i32 %39, i32* %a, align 4
  br label %3

; <label>:40		; preds = %3
  ret void
}
