define i32 @fact(i32 %curNum, i32 %sum) nounwind {
  %1 = icmp eq i32 %curNum, 1
  br i1 %1, label %2, label %3

; <label>:2                                       ; preds = %0
  br label %7

; <label>:3                                       ; preds = %0
  %4 = sub nsw i32 %curNum, 1
  %5 = mul nsw i32 %sum, %curNum
  %6 = call i32 @fact(i32 %4, i32 %5)
  br label %7

; <label>:7                                       ; preds = %3, %2
  %8 = phi i32 [ %sum, %2 ], [ %6, %3 ]
  ret i32 %8
}