define void @foo() nounwind {
  %a = alloca i32, align 4
  store i32 10, i32* %a, align 4
  store i32 0, i32* %a, align 4
  br label %1

; <label>:1 		; preds = %0, %22
  %2 = load i32, i32* %a, align 4
  %3 = icmp slt i32 %2, 10
  br i1 %3, label %4, label %25

; <label>:4      		; preds = %1
  %5 = load i32, i32* %a, align 4
  %6 = icmp slt i32 %5, 10
  br i1 %6, label %7, label %8

; <label>:7		; preds = %4
  br label %25

; <label>:8      		; preds = %4
  %9 = load i32, i32* %a, align 4
  %10 = icmp sgt i32 %9, 10
  br i1 %10, label %11, label %12

; <label>:11		; preds = %8
  br label %22

; <label>:12      		; preds = %8
  %13 = load i32, i32* %a, align 4
  %14 = icmp eq i32 %13, 10
  br i1 %14, label %15, label %16

; <label>:15		; preds = %12
  br label %25

; <label>:16     		; preds = %12
  %17 = load i32, i32* %a, align 4
  %18 = add i32 %17, 1
  store i32 %18, i32* %a, align 4
  br label %19

; <label>:19		; preds = %16
  br label %20

; <label>:20		; preds = %19
  br label %21

; <label>:21		; preds = %20
  br label %22

; <label>:22		; preds = %11, %21
  %23 = load i32, i32* %a, align 4
  %24 = add i32 %23, 1
  store i32 %24, i32* %a, align 4
  br label %1

; <label>:25		; preds = %1, %7, %15
  ret void
}
