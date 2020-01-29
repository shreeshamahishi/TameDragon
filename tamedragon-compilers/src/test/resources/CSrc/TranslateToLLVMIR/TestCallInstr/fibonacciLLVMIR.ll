define i32 @Fibonacci(i32 %n) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  store i32 %n, i32* %1, align 4
  %3 = load i32, i32* %1, align 4
  %4 = icmp eq i32 %3, 0
  br i1 %4, label %5, label %6

; <label>:5    		; preds = %0
  store i32 0, i32* %2, align 4
  br label %18

; <label>:6      		; preds = %0
  %7 = load i32, i32* %1, align 4
  %8 = icmp eq i32 %7, 1
  br i1 %8, label %9, label %10

; <label>:9    		; preds = %6
  store i32 1, i32* %2, align 4
  br label %18

; <label>:10        		; preds = %6
  %11 = load i32, i32* %1, align 4
  %12 = sub i32 %11, 1
  %13 = call i32 @Fibonacci(i32 %12)
  %14 = load i32, i32* %1, align 4
  %15 = sub i32 %14, 2
  %16 = call i32 @Fibonacci(i32 %15)
  %17 = add i32 %13, %16
  store i32 %17, i32* %2, align 4
  br label %18

; <label>:18		; preds = %5, %9, %10
  %19 = load i32, i32* %2, align 4
  ret i32 %19
}

define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %num = alloca i32, align 4
  %i = alloca i32, align 4
  %ans = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 10, i32* %num, align 4
  store i32 0, i32* %i, align 4
  br label %2

; <label>:2   		; preds = %0, %10
  %3 = load i32, i32* %i, align 4
  %4 = load i32, i32* %num, align 4
  %5 = icmp slt i32 %3, %4
  br i1 %5, label %6, label %13

; <label>:6        		; preds = %2
  %7 = load i32, i32* %i, align 4
  %8 = call i32 @Fibonacci(i32 %7)
  store i32 %8, i32* %ans, align 4
  %9 = load i32, i32* %ans, align 4
  store i32 %9, i32* %1, align 4
  br label %14

; <label>:10        		; preds = 
  %11 = load i32, i32* %i, align 4
  %12 = add i32 %11, 1
  store i32 %12, i32* %i, align 4
  br label %2

; <label>:13   		; preds = %2
  store i32 0, i32* %1, align 4
  br label %14

; <label>:14 		; preds = %6, %13
  %15 = load i32, i32* %1, align 4
  ret i32 %15
}
