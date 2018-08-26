define i32 @nestes3loop(i32 %a, i32 %b, i32 %c) nounwind {
  br label %1

; <label>:1         		; preds = %16, %0
  %.01 = phi i32 [ %10, %16 ], [ %b, %0 ]
  %.0 = phi i32 [ %17, %16 ], [ %a, %0 ]
  br label %2

; <label>:2          		; preds = %11, %1
  %.1 = phi i32 [ %10, %11 ], [ %.01, %1 ]
  br label %3

; <label>:3		; preds = %7, %2
  %4 = add i32 %.1, 50
  %5 = add i32 %c, 50
  %6 = add i32 %.0, 50
  br label %7

; <label>:7   		; preds = %3
  %8 = icmp slt i32 %c, 15
  br i1 %8, label %3, label %9

; <label>:9		; preds = %7
  %10 = add i32 %.1, 1
  br label %11

; <label>:11    		; preds = %9
  %12 = icmp slt i32 %10, 10
  br i1 %12, label %2, label %13

; <label>:13    		; preds = %11
  %14 = icmp slt i32 %.0, 5
  br i1 %14, label %15, label %16

; <label>:15		; preds = %13
  br label %18

; <label>:16		; preds = %13
  %17 = add i32 %.0, 1
  br label %1

; <label>:18		; preds = %15
  %19 = add i32 %6, %4
  %20 = add i32 %19, %5
  ret i32 %20
}