define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = load i32, i32* %1, align 4
  %4 = icmp ne i32 %3, 0
  br i1 %4, label %5, label %8

; <label>:5      		; preds = %0
  %6 = load i32, i32* %2, align 4
  %7 = icmp ne i32 %6, 0
  br label %8

; <label>:8        		; preds = %0, %5
  %9 = phi i1 [ 0, %0 ], [ %7, %5 ]
  br i1 %9, label %10, label %14

; <label>:10      		; preds = %8
  %11 = load i32, i32* %1, align 4
  %12 = load i32, i32* %2, align 4
  %13 = add i32 %11, %12
  store i32 %13, i32* %1, align 4
  br label %18

; <label>:14      		; preds = %8
  %15 = load i32, i32* %1, align 4
  %16 = load i32, i32* %2, align 4
  %17 = sub i32 %15, %16
  store i32 %17, i32* %1, align 4
  br label %18

; <label>:18		; preds = %10, %14
  %19 = load i32, i32* %1, align 4
  ret i32 %19
}
