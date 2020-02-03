define i32 @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  %3 = load i32, i32* %1, align 4
  %4 = icmp ne i32 %3, 0
  br i1 %4, label %8, label %5

; <label>:5		; preds = %0
  %6 = load i32, i32* %2, align 4
  %7 = icmp ne i32 %6, 0
  br label %8

; <label>:8      		; preds = %5, %0
  %9 = phi i1 [ true, %0 ], [ %7, %5 ]
  %10 = zext i1 %9 to i32
  store i32 %10, i32* %c, align 4
  %11 = load i32, i32* %c, align 4
  %12 = mul i32 %11, 20
  store i32 %12, i32* %d, align 4
  %13 = load i32, i32* %c, align 4
  %14 = add i32 %13, 10
  store i32 %14, i32* %c, align 4
  %15 = load i32, i32* %c, align 4
  %16 = icmp sgt i32 %15, 100
  br i1 %16, label %17, label %18

; <label>:17  		; preds = %8
  store i32 1, i32* %d, align 4
  br label %19

; <label>:18  		; preds = %8
  store i32 2, i32* %d, align 4
  br label %19

; <label>:19		; preds = %18, %17
  %20 = load i32, i32* %d, align 4
  ret i32 %20
}