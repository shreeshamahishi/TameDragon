@MAX = global i32 300, align 4
@split = external global i32

define i32 @foo(i32 %m, i32* %arr) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32*, align 8
  %3 = alloca i32, align 4
  %p1 = alloca i32*, align 8
  %p2 = alloca i32*, align 8
  %ptr = alloca i32*, align 8
  %count = alloca i32, align 4
  %result = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  store i32* %arr, i32** %2, align 8
  store i32 0, i32* %count, align 4
  store i32 65, i32* %result, align 4
  %4 = load i32*, i32** %2, align 8
  store i32* %4, i32** %p1, align 8
  %5 = load i32*, i32** %2, align 8
  store i32* %5, i32** %p2, align 8
  br label %6

; <label>:6     		; preds = %0, %23
  %7 = load i32, i32* %count, align 4
  %8 = load i32, i32* %1, align 4
  %9 = icmp slt i32 %7, %8
  br i1 %9, label %10, label %30

; <label>:10          		; preds = %6
  %11 = load i32*, i32** %p1, align 8
  %12 = getelementptr inbounds i32, i32* %11, i32 1
  store i32* %12, i32** %p1, align 8
  %13 = load i32*, i32** %p2, align 8
  %14 = getelementptr inbounds i32, i32* %13, i32 1
  store i32* %14, i32** %p2, align 8
  %15 = load i32, i32* %count, align 4
  %16 = load i32, i32* @split, align 4
  %17 = icmp slt i32 %15, %16
  br i1 %17, label %18, label %21

; <label>:18                      		; preds = %10
  %19 = load i32*, i32** %p1, align 8
  %20 = getelementptr inbounds i32, i32* %19, i32 1
  store i32* %20, i32** %ptr, align 8
  br label %23

; <label>:21        		; preds = %10
  %22 = load i32*, i32** %p2, align 8
  store i32* %22, i32** %ptr, align 8
  br label %23

; <label>:23     		; preds = %18, %21
  %24 = load i32*, i32** %ptr, align 8
  %25 = load i32, i32* %24, align 4
  %26 = load i32, i32* %result, align 4
  %27 = add i32 %26, %25
  store i32 %27, i32* %result, align 4
  %28 = load i32, i32* %count, align 4
  %29 = add i32 %28, 1
  store i32 %29, i32* %count, align 4
  br label %6

; <label>:30      		; preds = %6
  %31 = load i32, i32* %3, align 4
  ret i32 %31
}
