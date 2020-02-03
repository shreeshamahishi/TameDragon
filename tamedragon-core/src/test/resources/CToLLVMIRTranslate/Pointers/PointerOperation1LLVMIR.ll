define i32 @foo(i32 %m) nounwind {
  %1 = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  %c = alloca i32, align 4
  %p = alloca i32*, align 8
  %q = alloca i32*, align 8
  %r = alloca i32*, align 8
  store i32 %m, i32* %1, align 4
  %2 = load i32, i32* %1, align 4
  %3 = add i32 %2, 10
  store i32 %3, i32* %a, align 4
  %4 = load i32, i32* %1, align 4
  %5 = add i32 %4, 10
  store i32 %5, i32* %b, align 4
  store i32* %a, i32** %p, align 8
  store i32* %b, i32** %q, align 8
  %6 = load i32, i32* %1, align 4
  %7 = icmp ne i32 %6, 0
  br i1 %7, label %8, label %10

; <label>:8        		; preds = %0
  %9 = load i32*, i32** %p, align 8
  store i32* %9, i32** %r, align 8
  br label %12

; <label>:10        		; preds = %0
  %11 = load i32*, i32** %q, align 8
  store i32* %11, i32** %r, align 8
  br label %12

; <label>:12   		; preds = %8, %10
  %13 = load i32*, i32** %r, align 8
  %14 = load i32, i32* %13, align 4
  store i32 %14, i32* %c, align 4
  %15 = load i32, i32* %c, align 4
  ret i32 %15
}
