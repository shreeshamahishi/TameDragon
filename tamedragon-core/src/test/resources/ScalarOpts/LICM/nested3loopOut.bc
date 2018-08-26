define i32 @nestes3loop(i32 %a, i32 %b, i32 %c) nounwind {
  %1 = icmp slt i32 %c, 15
  br label %2

; <label>:2        		; preds = %16, %0
  %.01 = phi i32 [ %7, %16 ], [ %b, %0 ]
  %.0 = phi i32 [ %17, %16 ], [ %a, %0 ]
  br label %3

; <label>:3         		; preds = %8, %2
  %.1 = phi i32 [ %7, %8 ], [ %.01, %2 ]
  br label %4

; <label>:4		; preds = %5, %3
  br label %5

; <label>:5   		; preds = %4
  br i1 %1, label %4, label %6

; <label>:6		; preds = %5
  %7 = add i32 %.1, 1
  br label %8

; <label>:8    		; preds = %6
  %9 = icmp slt i32 %7, 10
  br i1 %9, label %3, label %10

; <label>:10     		; preds = %8
  %11 = icmp slt i32 %.0, 5
  br i1 %11, label %12, label %16

; <label>:12		; preds = %10
  %13 = add i32 %c, 50
  %14 = add i32 %.0, 50
  %15 = add i32 %.1, 50
  br label %18

; <label>:16		; preds = %10
  %17 = add i32 %.0, 1
  br label %2

; <label>:18		; preds = %12
  %19 = add i32 %14, %15
  %20 = add i32 %19, %13
  ret i32 %20
}