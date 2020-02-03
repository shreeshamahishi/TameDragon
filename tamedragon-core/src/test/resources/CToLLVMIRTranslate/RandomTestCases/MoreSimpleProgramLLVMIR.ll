define i32 @getint() nounwind {
  %a = alloca i32, align 4
  store i32 10, i32* %a, align 4
  %1 = load i32, i32* %a, align 4
  ret i32 %1
}

define i32 @getmax(i32 %a, i32 %b, i32 %c) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %m = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 %c, i32* %3, align 4
  %4 = load i32, i32* %1, align 4
  store i32 %4, i32* %m, align 4
  %5 = load i32, i32* %m, align 4
  %6 = load i32, i32* %2, align 4
  %7 = icmp slt i32 %5, %6
  br i1 %7, label %8, label %10

; <label>:8      		; preds = %0
  %9 = load i32, i32* %2, align 4
  store i32 %9, i32* %m, align 4
  br label %10

; <label>:10  		; preds = %0, %8
  %11 = load i32, i32* %m, align 4
  %12 = load i32, i32* %3, align 4
  %13 = icmp slt i32 %11, %12
  br i1 %13, label %14, label %16

; <label>:14     		; preds = %10
  %15 = load i32, i32* %3, align 4
  store i32 %15, i32* %m, align 4
  br label %16

; <label>:16		; preds = %10, %14
  %17 = load i32, i32* %m, align 4
  ret i32 %17
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %x = alloca i32, align 4
  %y = alloca i32, align 4
  %z = alloca i32, align 4
  store i32 0, i32* %1, align 4
  %2 = call i32 @getint()
  store i32 %2, i32* %x, align 4
  %3 = call i32 @getint()
  store i32 %3, i32* %y, align 4
  %4 = call i32 @getint()
  store i32 %4, i32* %z, align 4
  %5 = load i32, i32* %x, align 4
  %6 = load i32, i32* %y, align 4
  %7 = load i32, i32* %z, align 4
  %8 = call i32 @getmax(i32 %5, i32 %6, i32 %7)
  %9 = load i32, i32* %1, align 4
  ret i32 %9
}
