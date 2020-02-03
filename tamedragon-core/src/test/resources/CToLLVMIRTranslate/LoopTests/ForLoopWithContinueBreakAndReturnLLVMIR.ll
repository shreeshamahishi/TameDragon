define void @foo(i32 %a, i32 %b) nounwind {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %i = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 0, i32* %i, align 4
  br label %3

; <label>:3		; preds = %0, %9, %19
  %4 = load i32, i32* %i, align 4
  %5 = icmp slt i32 %4, 10
  br i1 %5, label %6, label %22

; <label>:6      		; preds = %3
  %7 = load i32, i32* %i, align 4
  %8 = icmp eq i32 %7, 2
  br i1 %8, label %9, label %10

; <label>:9		; preds = %6
  br label %3

; <label>:10      		; preds = %6
  %11 = load i32, i32* %i, align 4
  %12 = icmp eq i32 %11, 1
  br i1 %12, label %13, label %14

; <label>:13		; preds = %10
  br label %22

; <label>:14		; preds = %10
  br label %15

; <label>:15     		; preds = %14
  %16 = load i32, i32* %i, align 4
  %17 = icmp eq i32 %16, 6
  br i1 %17, label %18, label %19

; <label>:18		; preds = %15
  br label %23

; <label>:19     		; preds = %15
  %20 = load i32, i32* %i, align 4
  %21 = add i32 %20, 1
  store i32 %21, i32* %i, align 4
  br label %3

; <label>:22		; preds = %3, %13
  br label %23

; <label>:23		; preds = %18, %22
  ret void
}
