define i32 @foo(i32 %a, i32 %b, i32 %sum, i32 %sum2) nounwind {
  %1 = add i32 %a, %b
  %2 = add i32 %a, %b
  %3 = add i32 %a, %b
  %4 = add i32 %1, 1
  %5 = sub i32 %2, 1
  %6 = icmp sgt i32 %3, %b
  br i1 %6, label %7, label %13

; <label>:7		; preds = %0
  %8 = add i32 %3, %b
  %9 = add i32 %3, %b
  %10 = add i32 %3, %b
  %11 = add i32 %8, 1
  %12 = sub i32 %9, 1
  br label %19

; <label>:13		; preds = %0
  %14 = add i32 %3, %b
  %15 = add i32 %3, %b
  %16 = add i32 %3, %b
  %17 = add i32 %14, 1
  %18 = sub i32 %15, 1
  br label %19

; <label>:19        		; preds = %13, %7
  %.0 = phi i32 [ %17, %13 ], [ %12, %7 ]
  ret i32 %.0
}