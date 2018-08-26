define void @doll(i32 %size) nounwind {
  %1 = icmp eq i32 %size, 0
  br i1 %1, label %2, label %3

; <label>:2                                       ; preds = %0
  br label %5

; <label>:3                                       ; preds = %0
  %4 = sub i32 %size, 1
  call void @doll(i32 %4)
  br label %5

; <label>:5                                       ; preds = %3, %2
  ret void
}