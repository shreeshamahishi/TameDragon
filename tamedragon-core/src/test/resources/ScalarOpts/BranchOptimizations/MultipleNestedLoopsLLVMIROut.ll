define void @foo(i32 %m, i32* %result) nounwind {
  br label %1

; <label>:1             		; preds = %20, %0
  %y.0 = phi i32 [ %y.1, %20 ], [ 23, %0 ]
  %b.0 = phi i32 [ %b.1, %20 ], [ undef, %0 ]
  %a.0 = phi i32 [ %21, %20 ], [ 3, %0 ]
  %2 = icmp slt i32 %a.0, %m
  br i1 %2, label %3, label %22

; <label>:3          		; preds = %1
  %4 = load i32, i32* %result, align 4
  %5 = add i32 %4, %a.0
  store i32 %5, i32* %result, align 4
  br label %6

; <label>:6            		; preds = %17, %3
  %y.1 = phi i32 [ %y.2, %17 ], [ %y.0, %3 ]
  %b.1 = phi i32 [ %b.2, %17 ], [ %b.0, %3 ]
  %7 = icmp ne i32 12, 0
  br i1 %7, label %8, label %20

; <label>:8      		; preds = %6
  %9 = load i32, i32* %result, align 4
  %10 = add i32 %b.1, %9
  br label %11

; <label>:11          		; preds = %15, %8
  %y.2 = phi i32 [ %14, %15 ], [ %y.1, %8 ]
  %b.2 = phi i32 [ %16, %15 ], [ 1, %8 ]
  %12 = icmp slt i32 %b.2, %m
  br i1 %12, label %13, label %17

; <label>:13		; preds = %11
  %14 = add i32 %y.2, 40
  br label %15

; <label>:15		; preds = %13
  %16 = add i32 %b.2, 1
  br label %11

; <label>:17         		; preds = %11
  %18 = load i32, i32* %result, align 4
  %19 = add i32 %18, %y.2
  store i32 %19, i32* %result, align 4
  br label %6

; <label>:20		; preds = %6
  %21 = add i32 %a.0, 1
  br label %1

; <label>:22		; preds = %1
  ret void
}
