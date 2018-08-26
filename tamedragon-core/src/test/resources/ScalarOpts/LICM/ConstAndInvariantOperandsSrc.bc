define i32 @foobar(i32 %a, i32 %b) nounwind uwtable {
  br label %1

; <label>:1                		; preds = %6, %0
  %result.0 = phi i32 [ %5, %6 ], [ undef, %0 ]
  %m.0 = phi i32 [ %7, %6 ], [ 0, %0 ]
  %2 = icmp slt i32 %m.0, %a
  br i1 %2, label %3, label %8

; <label>:3 		; preds = %1
  %4 = add i32 %b, 78
  %5 = add i32 %result.0, %4
  br label %6

; <label>:6		; preds = %3
  %7 = add i32 %m.0, 1
  br label %1

; <label>:8		; preds = %1
  ret i32 %result.0
}