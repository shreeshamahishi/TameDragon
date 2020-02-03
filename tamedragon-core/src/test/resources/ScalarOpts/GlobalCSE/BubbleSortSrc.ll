define void @bubble(i32* %a, i32 %n) nounwind {
  %1 = sub i32 %n, 2
  br label %2

; <label>:2         		; preds = %27, %0
  %i.0 = phi i32 [ %28, %27 ], [ %1, %0 ]
  %3 = icmp sge i32 %i.0, 0
  br i1 %3, label %4, label %29

; <label>:4		; preds = %2
  br label %5

; <label>:5        		; preds = %24, %4
  %j.0 = phi i32 [ %25, %24 ], [ 0, %4 ]
  %6 = icmp sle i32 %j.0, %i.0
  br i1 %6, label %7, label %26

; <label>:7                    		; preds = %5
  %8 = getelementptr inbounds i32, i32* %a, i32 %j.0
  %9 = add i32 %j.0, 1
  %10 = getelementptr inbounds i32, i32* %a, i32 %9
  %11 = load i32, i32* %8, align 4
  %12 = load i32, i32* %10, align 4
  %13 = icmp sgt i32 %11, %12
  br i1 %13, label %14, label %23

; <label>:14                    		; preds = %7
  %15 = getelementptr inbounds i32, i32* %a, i32 %j.0
  %16 = load i32, i32* %15, align 4
  %17 = add i32 %j.0, 1
  %18 = getelementptr inbounds i32, i32* %a, i32 %17
  %19 = getelementptr inbounds i32, i32* %a, i32 %j.0
  %20 = load i32, i32* %18, align 4
  store i32 %20, i32* %19, align 4
  %21 = add i32 %j.0, 1
  %22 = getelementptr inbounds i32, i32* %a, i32 %21
  store i32 %16, i32* %22, align 4
  br label %23

; <label>:23		; preds = %14, %7
  br label %24

; <label>:24		; preds = %23
  %25 = add i32 %j.0, 1
  br label %5

; <label>:26		; preds = %5
  br label %27

; <label>:27		; preds = %26
  %28 = sub i32 %i.0, 1
  br label %2

; <label>:29		; preds = %2
  ret void
}