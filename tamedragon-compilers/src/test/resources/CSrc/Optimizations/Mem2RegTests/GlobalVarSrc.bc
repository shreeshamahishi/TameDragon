@g = common global i32 0, align 4
define i32 @foo(i32 %m) nounwind uwtable {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %a = alloca i32, align 4
  %b = alloca i32, align 4
  store i32 %m, i32* %1, align 4
  %3 = load i32* %1, align 4
  %4 = icmp eq i32 %3, 10
  br i1 %4, label %5, label %10

; <label>:5     		; preds = %0
  %6 = load i32* @g, align 4
  %7 = add i32 %6, 3
  store i32 %7, i32* %a, align 4
  %8 = load i32* @g, align 4
  %9 = add i32 %8, 1
  store i32 %9, i32* @g, align 4
  br label %13

; <label>:10     		; preds = %0
  %11 = load i32* @g, align 4
  %12 = add i32 %11, 3
  store i32 %12, i32* %b, align 4
  br label %13

; <label>:13		; preds = %10, %5
  %14 = load i32* %1, align 4
  %15 = icmp ne i32 %14, 0
  br i1 %15, label %16, label %18

; <label>:16    		; preds = %13
  %17 = load i32* %a, align 4
  store i32 %17, i32* %2, align 4
  br label %20

; <label>:18    		; preds = %13
  %19 = load i32* %b, align 4
  store i32 %19, i32* %2, align 4
  br label %20

; <label>:20		; preds = %18, %16
  %21 = load i32* %2, align 4
  ret i32 %21
}