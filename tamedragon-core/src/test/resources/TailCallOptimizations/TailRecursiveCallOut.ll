define void @fibonacci(i32 %first, i32 %second) nounwind {
  br label %tailrecurse

;tailrecurse:                                      ; preds = %3, %0
  %first.tr = phi i32 [ %first, %0 ], [ %second.tr, %3 ]
  %second.tr = phi i32 [ %second, %0 ], [ %4, %3 ]
  %1 = icmp sgt i32 %second.tr, 100
  br i1 %1, label %2, label %3

; <label>:2                                       ; preds = %tailrecurse
  br label %5

; <label>:3                                       ; preds = %tailrecurse
  %4 = add i32 %first.tr, %second.tr
  br label %tailrecurse

; <label>:5                                       ; preds = %2
  ret void
}