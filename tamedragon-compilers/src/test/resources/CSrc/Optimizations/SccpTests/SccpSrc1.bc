define i32 @foo() nounwind uwtable {
  br label %1

; <label>:1         		; preds = %9, %0
  %k.0 = phi i32 [ %k.1, %9 ], [ 0, %0 ]
  %j.0 = phi i32 [ %j.1, %9 ], [ 1, %0 ]
  %2 = icmp slt i32 %k.0, 100
  br i1 %2, label %3, label %10

; <label>:3   		; preds = %1
  %4 = icmp slt i32 %j.0, 20
  br i1 %4, label %5, label %7

; <label>:5		; preds = %3
  %6 = add i32 %k.0, 1
  br label %9

; <label>:7		; preds = %3
  %8 = add i32 %k.0, 2
  br label %9

; <label>:9         		; preds = %7, %5
  %k.1 = phi i32 [ %8, %7 ], [ %6, %5 ]
  %j.1 = phi i32 [ %k.0, %7 ], [ 1, %5 ]
  br label %1

; <label>:10		; preds = %1
  ret i32 %j.0
}