define void @printnum(i32 %begin) nounwind {
  %1 = icmp slt i32 %begin, 9
  br i1 %1, label %2, label %4

; <label>:2                                       ; preds = %0
  %3 = add i32 %begin, 1
  call void @printnum(i32 %3)
  br label %4

; <label>:4                                       ; preds = %2, %0
  ret void
}