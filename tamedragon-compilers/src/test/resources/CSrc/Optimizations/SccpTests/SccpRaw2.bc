define i32 @foo(i32 %a, i32 %b) nounwind uwtable {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %c = alloca i32, align 4
  %d = alloca i32, align 4
  store i32 %a, i32* %1, align 4
  store i32 %b, i32* %2, align 4
  store i32 24, i32* %c, align 4
  %3 = load i32* %1, align 4
  %4 = load i32* %c, align 4
  %5 = add nsw i32 %3, %4
  %6 = load i32* %2, align 4
  %7 = icmp sgt i32 %5, %6
  br i1 %7, label %8, label %9

; <label>:8                                       ; preds = %0
  store i32 1, i32* %d, align 4
  br label %10

; <label>:9                                       ; preds = %0
  store i32 3, i32* %d, align 4
  br label %10

; <label>:10                                      ; preds = %9, %8
  %11 = load i32* %d, align 4
  ret i32 %11
}