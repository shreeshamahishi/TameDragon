define i32 @bar(i32 %max_val, i32 %j, i32 %addr) nounwind uwtable {
  br label %1

; <label>:1             		; preds = %14, %0
  %result.0 = phi i32 [ %13, %14 ], [ 0, %0 ]
  %index.0 = phi i32 [ %15, %14 ], [ 0, %0 ]
  %2 = icmp slt i32 %index.0, %max_val
  br i1 %2, label %3, label %16

; <label>:3   		; preds = %1
  %4 = mul i32 100, %index.0
  %5 = add i32 %4, %j
  %6 = sub i32 %5, 101
  %7 = mul i32 4, %6
  %8 = add i32 %7, %addr
  %9 = sub i32 %4, %5
  %10 = add i32 %9, %6
  %11 = mul i32 %7, %8
  %12 = add i32 %10, %11
  %13 = add i32 %result.0, %12
  br label %14

; <label>:14		; preds = %3
  %15 = add i32 %index.0, 1
  br label %1

  ; <label>:16		; preds = %1
  ret i32 %result.0
}
