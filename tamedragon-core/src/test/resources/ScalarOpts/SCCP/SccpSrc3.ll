define i32 @bar(i32 %a, i32 %b) nounwind uwtable {
  %1 = icmp sgt i32 1, 2
  br i1 %1, label %2, label %8

; <label>:2                                       ; preds = %0
  %3 = add i32 %a, %b
  %4 = icmp ne i32 %3, 0
  br i1 %4, label %5, label %7

; <label>:5                                       ; preds = %2
  %6 = add i32 1, 10
  br label %7

; <label>:7                                       ; preds = %5, %2
  %d.0 = phi i32 [ %6, %5 ], [ 1, %2 ]
  br label %15

; <label>:8                                       ; preds = %0
  %9 = add i32 %a, 2
  %10 = add i32 %b, 1
  %11 = icmp sgt i32 %9, %10
  br i1 %11, label %12, label %13

; <label>:12                          	            ; preds = %8
  br label %14

; <label>:13                                      ; preds = %8
  br label %14

; <label>:14                                      ; preds = %13, %12
  %d.1 = phi i32 [ 3, %12 ], [ 4, %13 ]
  br label %15

; <label>:15                                      ; preds = %14, %7
  %d.2 = phi i32 [ %d.0, %7 ], [ %d.1, %14 ]
  ret i32 %d.2
}
