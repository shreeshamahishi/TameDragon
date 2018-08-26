define i32 @fact(i32 %curNum, i32 %sum) nounwind {
  br label %tailrecurse

;tailrecurse:                                      ; preds = %3, %0
  %curNum.tr = phi i32 [ %curNum, %0 ], [ %4, %3 ]
  %sum.tr = phi i32 [ %sum, %0 ], [ %5, %3 ]
  %1 = icmp eq i32 %curNum.tr, 1
  br i1 %1, label %2, label %3

; <label>:2                                       ; preds = %tailrecurse
  br label %6

; <label>:3                                       ; preds = %tailrecurse
  %4 = sub nsw i32 %curNum.tr, 1
  %5 = mul nsw i32 %sum.tr, %curNum.tr
  br label %tailrecurse

; <label>:6                                       ; preds = %2
  ret i32 %sum.tr
}