define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = load i32, i32* %1, align 4
  %4 = load i32, i32* %2, align 4
  %5 = icmp sgt i32 %3, %4
  br i1 %5, label %6, label %10

; <label>:6      		; preds = %0
  %7 = load i32, i32* %1, align 4
  %8 = load i32, i32* %2, align 4
  %9 = add i32 %7, %8
  br label %14

; <label>:10      		; preds = %0
  %11 = load i32, i32* %1, align 4
  %12 = load i32, i32* %2, align 4
  %13 = sub i32 %11, %12
  br label %14

; <label>:14       		; preds = %6, %10
  %15 = phi i32 [ %9, %6 ], [ %13, %10 ]
  store i32 %15, i32* %1, align 4
  %16 = load i32, i32* %1, align 4
  ret i32 %16
}
