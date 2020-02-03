define void @bubble(i32* %a, i32 %n) nounwind {
  %1 = sub i32 %n, 2
  br label %2

; <label>:2         		; preds = %0, %18
  %i.0 = phi i32 [ %1, %0 ], [ %19, %18 ]
  %3 = icmp sge i32 %i.0, 0
  br i1 %3, label %4, label %20

; <label>:4		; preds = %2
  br label %5

; <label>:5       		; preds = %4, %16
  %j.0 = phi i32 [ 0, %4 ], [ %9, %16 ]
  %6 = icmp sle i32 %j.0, %i.0
  br i1 %6, label %7, label %17

; <label>:7                         		; preds = %5
  %8 = getelementptr inbounds i32, i32* %a, i32 %j.0
  %9 = add i32 %j.0, 1
  %10 = getelementptr inbounds i32, i32* %a, i32 %9
  %11 = load i32, i32* %8, align 4
  %12 = load i32, i32* %10, align 4
  %13 = icmp sgt i32 %11, %12
  br i1 %13, label %14, label %15

; <label>:14      		; preds = %7
  store i32 %12, i32* %8, align 4
  store i32 %11, i32* %10, align 4
  br label %15

; <label>:15		; preds = %7, %14
  br label %16

; <label>:16		; preds = %15
  br label %5

; <label>:17		; preds = %5
  br label %18

; <label>:18		; preds = %17
  %19 = sub i32 %i.0, 1
  br label %2

; <label>:20		; preds = %2
  ret void
}