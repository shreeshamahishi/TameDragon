@gptr = common global i32* null, align 8
@MAX = global i32 100, align 4
@INTM = global i32 50, align 4

define i32 @bar(i32* %a, i32 %value) nounwind {
  %1 = alloca i32*, align 8
  %2 = alloca i32, align 4
  %ptr = alloca i32*, align 8
  %x = alloca i32, align 4
  store i32* %a, i32** %1, align 8
  store i32 %value, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = load i32, i32* @MAX, align 4
  %5 = icmp slt i32 %3, %4
  br i1 %5, label %6, label %8

; <label>:6           		; preds = %0
  %7 = load i32*, i32** @gptr, align 8
  store i32* %7, i32** %ptr, align 8
  br label %10

; <label>:8         		; preds = %0
  %9 = load i32*, i32** %1, align 8
  store i32* %9, i32** %ptr, align 8
  br label %10

; <label>:10      		; preds = %6, %8
  %11 = load i32*, i32** %ptr, align 8
  %12 = load i32, i32* %2, align 4
  %13 = load i32, i32* %11, align 4
  %14 = mul i32 %13, %12
  store i32 %14, i32* %x, align 4
  %15 = load i32, i32* %x, align 4
  ret i32 %15
}
