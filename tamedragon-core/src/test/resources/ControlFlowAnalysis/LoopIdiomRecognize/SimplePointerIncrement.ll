define i32 @foo(i32* %arg1, i32 %MAX) nounwind {
  br label %1

; <label>:1              		; preds = %3, %0
  %result.0 = phi i32 [ %5, %3 ], [ 0, %0 ]
  %count.0 = phi i32 [ %7, %3 ], [ 0, %0 ]
  %ptr.0 = phi i32* [ %6, %3 ], [ %arg1, %0 ]
  %2 = icmp slt i32 %count.0, %MAX
  br i1 %2, label %3, label %8

; <label>:3                     		; preds = %1
  %4 = load i32, i32* %ptr.0, align 4
  %5 = add i32 %result.0, %4
  %6 = getelementptr inbounds i32, i32* %ptr.0, i32 1
  %7 = add i32 %count.0, 1
  br label %1

; <label>:8		; preds = %1
  ret i32 %result.0
}