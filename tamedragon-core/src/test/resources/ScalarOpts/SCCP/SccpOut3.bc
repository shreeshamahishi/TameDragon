define i32 @bar(i32 %a, i32 %b) nounwind uwtable {
  br i1 false, label %1, label %4

; <label>:1                                       ; preds = %0
  br i1 undef, label %2, label %3

; <label>:2                                       ; preds = %1
  br label %3

; <label>:3                                       ; preds = %2, %1
  br label %11

; <label>:4                                       ; preds = %0
  %5 = add i32 %a, 2
  %6 = add i32 %b, 1
  %7 = icmp sgt i32 %5, %6
  br i1 %7, label %8, label %9

; <label>:8                                       ; preds = %4
  br label %10

; <label>:9                                       ; preds = %4
  br label %10

; <label>:10                                      ; preds = %9, %8
  %d.1 = phi i32 [ 3, %8 ], [ 4, %9 ]
  br label %11

; <label>:11                                      ; preds = %10, %3
  %d.2 = phi i32 [ undef, %3 ], [ %d.1, %10 ]
  ret i32 %d.2
}
