define i32 @foo(i32 %m, i32 %n) nounwind {
  %1 = add i32 %m, %n
  %2 = icmp sgt i32 %1, 20
  br i1 %2, label %3, label %4

; <label>:3                                       ; preds = %0
  br label %12

; <label>:4                                       ; preds = %0
  br label %5

; <label>:5                                       ; preds = %9, %4
  %x.0 = phi i32 [ 21, %4 ], [ %8, %9 ]
  %j.0 = phi i32 [ 0, %4 ], [ %10, %9 ]
  %6 = icmp slt i32 %j.0, 30
  br i1 %6, label %7, label %11

; <label>:7                                       ; preds = %5
  %8 = add i32 %x.0, 2
  br label %9

; <label>:9                                       ; preds = %7
  %10 = add i32 %j.0, 1
  br label %5

; <label>:11                                      ; preds = %5
  br label %12

; <label>:12                                      ; preds = %11, %3
  %x.1 = phi i32 [ 21, %3 ], [ %x.0, %11 ]
  ret i32 %x.1
}