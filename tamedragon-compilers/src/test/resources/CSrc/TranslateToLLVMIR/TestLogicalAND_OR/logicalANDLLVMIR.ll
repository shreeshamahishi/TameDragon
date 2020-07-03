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
  %10 = load i32, i32* %1, align 4
  ret i32 %10
}
