define i32 @main() nounwind {
  %1 = alloca i32, align 4
  %n = alloca i32, align 4
  %val = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 1, i32* %val, align 4
  store i32 0, i32* %n, align 4
  br label %2

; <label>:2  		; preds = %0, %8
  %3 = load i32, i32* %n, align 4
  %4 = icmp sle i32 %3, 16
  br i1 %4, label %5, label %11

; <label>:5        		; preds = %2
  %6 = load i32, i32* %val, align 4
  %7 = mul i32 2, %6
  store i32 %7, i32* %val, align 4
  br label %8

; <label>:8      		; preds = %5
  %9 = load i32, i32* %n, align 4
  %10 = add i32 %9, 1
  store i32 %10, i32* %n, align 4
  br label %2

; <label>:11		; preds = %2
  ret i32 0
}
