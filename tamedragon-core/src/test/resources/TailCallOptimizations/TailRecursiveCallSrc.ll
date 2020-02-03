define void @fibonacci(i32 %first, i32 %second) nounwind {
  %1 = icmp sgt i32 %second, 100
  br i1 %1, label %2, label %3

; <label>:2                                       ; preds = %0
  br label %5

; <label>:3                                       ; preds = %0
  %4 = add i32 %first, %second
  call void @fibonacci(i32 %second, i32 %4)
  br label %5

; <label>:5                                       ; preds = %3, %2
  ret void
}